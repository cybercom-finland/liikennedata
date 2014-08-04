package liikennedata.SiriDownload;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

/**
 * Main class for accessing Siri data.
 * Starts downloading the Siri data.
 * 
 * @author lapel1
 *
 */
public class SiriMain {

	/**
	 * Main entry point to the program
	 * @param args
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException,
			ClassNotFoundException, InterruptedException {

		SiriDownload dl = new SiriDownload();
		dl.download();

		System.exit(0); // Called when the execution ends. For some reason the
						// process stays in background without this.
	}
}
