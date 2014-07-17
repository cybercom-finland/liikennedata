package liikennedata;

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

import liikennedata.SiriObjects.SiriRoot;

import com.google.gson.Gson;

public class SiriDownload {
	
	private final String filePath = "../";
	private final String outputFileName = "siridata.txt";
	private final String controlFileName = "control.txt";
	private final String siriUrl = "http://data.itsfactory.fi/siriaccess/vm/json";
	private final int sleepTimeMs = 300;
	
	private final ExecutorService pool = Executors.newFixedThreadPool(10);
	
	/**
	 * Starts downloading Siri data to a file specified in code.
	 * Download can be interrupted by adding value "0" in file control.txt
	 * @throws InterruptedException
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ExecutionException
	 */
	public void download() throws InterruptedException, MalformedURLException, IOException, ExecutionException {
		
		Number previousTimeStamp = null;
		Number stamp = null;		
		
		File f = new File(filePath + controlFileName);
		if (!f.exists()) {
			System.err.println("No control file " + controlFileName + " found, aborting");
			return;
		}
		
		while (true) {
			if (shouldAbort()) {
				System.out.println("Control set to inactive, stopping download");
				break;
			}
			
			final Future<String> contentsFuture = startDownloading(new URL(siriUrl));
			final String contents = contentsFuture.get();
			
			Gson gson = new Gson();
			SiriRoot siri = gson.fromJson(contents, SiriRoot.class);
			stamp = siri.getSiri().getServiceDelivery().getResponseTimestamp();
				
			// Only write data if the timestamp has changed
			if (previousTimeStamp == null || stamp.doubleValue() != previousTimeStamp.doubleValue()) {

				try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filePath + outputFileName, true)))) {
				    out.print(contents);
				}catch (IOException e) {
				    
				}
				
				previousTimeStamp = stamp;
				Thread.sleep(sleepTimeMs);
			}
		}
	}

	/**
	 * Checks whether we should abort the download.
	 * The download should be aborted if the control file has its value set to 0
	 * @return
	 * @throws IOException
	 */
	private Boolean shouldAbort() throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(filePath + controlFileName), Charset.defaultCharset());
		for (int b = 0; b < lines.size(); b++) {
			if (!lines.get(b).startsWith("#")) { // # lines are comments
				if (lines.get(b).contains("0")) { // If line contains 0, then we should abort
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Starts downloading contents of the URL.
	 * Adapted from http://www.nurkiewicz.com/2013/02/javautilconcurrentfuture-basics.html
	 * @param url
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
	 * Converts an input stream into a string
	 * Adapted from http://stackoverflow.com/questions/309424/read-convert-an-inputstream-to-a-string
	 * @param is
	 * @return
	 * @throws IOException 
	 */
	private String convertStreamToString(java.io.InputStream is) throws IOException {
	    java.util.Scanner s = new java.util.Scanner(is, "UTF-8").useDelimiter("\\A");
	    String ret = s.hasNext() ? s.next() : "";
	    s.close();
	    is.close();
	    return ret;
	}
}
