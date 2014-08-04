package liikennedata.SiriDownload;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import liikennedata.SiriDownload.SiriObjects.SiriRoot;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

/**
 * Downloads Siri data from website in an infinite loop, with a N-second polling interval.
 * The downloaded data is then stored with the HadoopStorage class.
 * @author lapel1
 *
 */
public class SiriDownload {

	/**
	 * The path where to save control file and errors file (in local filesystem)
	 */
	protected final static String filePath = "./";
	/**
	 * What is the name of the control file
	 */
	private final String controlFileName = "control.txt";
	/**
	 * URL to the Siri data
	 */
	private final String siriUrl = "http://data.itsfactory.fi/siriaccess/vm/json";
	/**
	 * How long to wait between two polls
	 */
	private final int sleepTimeMs = 1000; // 1 second
	/**
	 * How many errors we tolerate before we enter standby mode, where we start polling much less frequently
	 */
	private final int numberOfErrorsToAllowBeforeEnteringStandBy = 5;
	/**
	 * How long to wait between two polls if we have exceeded the tolerated error limit
	 */
	private final int sleepTimeBetweenErrors = 60000; // 1 minute

	/**
	 * A background thread for running (possibly) asynchronous tasks. The task to run is data downloading and it is run synchronously.
	 */
	private final ExecutorService pool = Executors.newFixedThreadPool(10);
	/**
	 * Logging class
	 */
	private static Logger logger = Logger.getLogger(SiriDownload.class);
	/**
	 * Reference to the Hadoop storage functionality
	 */
	private HadoopStorage storage = new HadoopStorage();

	/**
	 * Starts downloading Siri data to a file specified in code. Download can be
	 * interrupted by adding value "0" in file control.txt
	 * 
	 * @throws InterruptedException
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ExecutionException
	 */
	public void download() {

		Number previousTimeStamp = null;
		Number stamp = null;

		File f = new File(filePath + controlFileName);
		if (!f.exists()) {
			logger.warn("No control file " + controlFileName
					+ " found, aborting");
			return;
		}
		
		logger.info("Starting download!");

		int numOfErrors = 0;

		while (true) {
			try {
				if (shouldAbort()) {
					logger.info("Control set to inactive, stopping download");
					return;
				}

				final Future<String> contentsFuture = startDownloading(new URL(
						siriUrl));
				final String contents = contentsFuture.get();

				Gson gson = new Gson();
				SiriRoot siri = gson.fromJson(contents, SiriRoot.class);
				stamp = siri.getSiri().getServiceDelivery()
						.getResponseTimestamp();

				// Only write data if the timestamp has changed
				if (previousTimeStamp == null
						|| stamp.doubleValue() != previousTimeStamp
								.doubleValue()) {
								
					storage.saveData(contents);
					previousTimeStamp = stamp;
					numOfErrors = 0; // Reset errors upon successful save
				}
			} catch (Exception e) {
				logger.error(e);
				numOfErrors++;
				
			} finally {
				try {
					int sleepTime = sleepTimeMs;
					if (numOfErrors > numberOfErrorsToAllowBeforeEnteringStandBy) {
						// If we have exceeded the error threshold, stop trying so hard. Enter standby mode, where we just try every now and then. Never give up the hope!
						sleepTime = sleepTimeBetweenErrors;
					}
					
					Thread.sleep(sleepTime); // Sleep for a while
				} catch (InterruptedException e) {
					logger.error(e);
				} 
			}
		}

	}

	/**
	 * Checks whether we should abort the download. The download should be
	 * aborted if the control file has its value set to 0
	 * 
	 * @return
	 * @throws IOException
	 */
	private Boolean shouldAbort() throws IOException {
		List<String> lines = Files
				.readAllLines(Paths.get(filePath + controlFileName),
						Charset.defaultCharset());
		for (int b = 0; b < lines.size(); b++) {
			if (!lines.get(b).startsWith("#")) { // # lines are comments
				if (lines.get(b).contains("0")) { // If line contains 0, then we
													// should abort
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Starts downloading contents of the URL. Adapted from
	 * http://www.nurkiewicz.com/2013/02/javautilconcurrentfuture-basics.html
	 * 
	 * @param url The URL to download data from
	 * @return
	 * @throws IOException
	 */
	private Future<String> startDownloading(final URL url) throws IOException {
		return pool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				try (InputStream input = url.openStream()) {
					return convertStreamToString(input);
				}
			}
		});
	}

	/**
	 * Converts an input stream into a string Adapted from
	 * http://stackoverflow.com
	 * /questions/309424/read-convert-an-inputstream-to-a-string
	 * 
	 * @param is The input stream
	 * @return
	 * @throws IOException
	 */
	private String convertStreamToString(java.io.InputStream is)
			throws IOException {
		java.util.Scanner s = new java.util.Scanner(is, "UTF-8")
				.useDelimiter("\\A");
		String ret = s.hasNext() ? s.next() : "";
		s.close();
		is.close();
		return ret;
	}
}
