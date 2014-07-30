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

public class SiriMain {

	public static void main(String[] args) throws IOException,
			ClassNotFoundException, InterruptedException {

		SiriDownload dl = new SiriDownload();
		dl.download();

		System.exit(0); // Called when the execution ends. For some reason the
						// process stays in background without this.
	}
}
