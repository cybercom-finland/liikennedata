package liikennedata;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.*;

import liikennedata.SiriObjects.*;

import com.google.gson.Gson;

public class Test {

	public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException, ExecutionException  {

		final Future<String> contentsFuture = startDownloading(new URL("http://data.itsfactory.fi/siriaccess/vm/json"));
		final String contents = contentsFuture.get();
		
		Gson gson = new Gson();
		SiriRoot obj = gson.fromJson(contents, SiriRoot.class);
		System.out.println("END");
	}

	private final static ExecutorService pool = Executors.newFixedThreadPool(10);

	/**
	 * Starts downloading contents of the URL.
	 * Adapted from http://www.nurkiewicz.com/2013/02/javautilconcurrentfuture-basics.html
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static Future<String> startDownloading(final URL url) throws IOException {
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
	 */
	static String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is, "UTF-8").useDelimiter("\\A");
	    String ret = s.hasNext() ? s.next() : "";
	    s.close();
	    return ret;
	}
}


