package liikennedata;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.PrivilegedExceptionAction;
import java.util.concurrent.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobID;
import org.apache.hadoop.mapred.JobStatus;
import org.apache.hadoop.security.UserGroupInformation;

import liikennedata.SiriObjects.*;

import com.google.gson.Gson;

public class Test {

	public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException, ExecutionException, URISyntaxException  {

		SiriDownload dl = new SiriDownload();
		dl.download();
		
		/*
		String uri = "hdfs://10.33.24.20:8042/";


	    System.out.println( "uri: " + uri );  
	    System.out.println("path: " + getClasspathString());
	    Configuration conf = new Configuration();

	    FileSystem fs = FileSystem.get( URI.create( uri ), conf );
	    fs.printStatistics();
	    System.out.println("over");
	    */

		
		/*
		UserGroupInformation ugi = UserGroupInformation
				.createRemoteUser("hduser");

		try {

			ugi.doAs(new PrivilegedExceptionAction<Void>() {

				public Void run() throws Exception {

					Configuration conf = new Configuration();
					// fs.default.name should match the corresponding value
					// in your core-site.xml in hadoop cluster
					conf.set("fs.defaultFS", "hdfs://10.33.24.20:9000");
					conf.set("hadoop.job.ugi", "hduser");
					// in case you are running mapreduce job , need to set
					// 'mapred.job.tracker' as you did
					conf.set("mapred.job.tracker", "10.33.24.20:8042");

					System.out.println("got configuration : " + conf);
					InetSocketAddress jobtracker = new InetSocketAddress(
							"10.33.24.20", 9005);
					JobClient jobClient = new JobClient(jobtracker, conf);
					JobStatus[] jobs = jobClient.jobsToComplete();

					for (int i = 0; i < jobs.length; i++) {
						JobStatus js = jobs[i];
						if (js.getRunState() == JobStatus.RUNNING) {
							JobID jobId = js.getJobID();
							System.out.println(jobId);
						}
					}

					return null;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		
		/*
		Configuration conf = new Configuration();
	    FileSystem fileSystem = FileSystem.get(new URI("hdfs://10.33.24.20:9000"),conf);
	    System.out.println("Hello");
	    
	    if(fileSystem instanceof DistributedFileSystem) {
	      System.out.println("HDFS is the underlying filesystem");
	    }
	    else {
	      System.out.println("Other type of file system "+fileSystem.getClass()));*/
	}
	
	public static String getClasspathString() {
	     StringBuffer classpath = new StringBuffer();
	     ClassLoader applicationClassLoader = Test.class.getClassLoader();
	     if (applicationClassLoader == null) {
	         applicationClassLoader = ClassLoader.getSystemClassLoader();
	     }
	     URL[] urls = ((URLClassLoader)applicationClassLoader).getURLs();
	      for(int i=0; i < urls.length; i++) {
	          classpath.append(urls[i].getFile()).append("\r\n");
	      }    
	     
	      return classpath.toString();
	  }

	

	

	
}


