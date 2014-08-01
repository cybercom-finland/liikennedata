package liikennedata.SiriDownload;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HadoopStorage {

	//private final String uri = "hdfs://10.33.24.20:9000";
	/***
	 * Filename to be used in HDFS for the data. File will be created if doesn't exist.
	 */
	private final String file = "siridata.txt";

	/**
	 * Save the given data to normal filesystem
	 * @param contents The data to be written
	 * @throws IOException
	 */
//	public void saveData(final String contents) throws IOException {
//		try (PrintWriter out = new PrintWriter(new BufferedWriter(
//				new FileWriter(SiriDownload.filePath
//						+ file, true)))) {
//			out.println(contents);
//		}
//	}

	/**
	 * Save the given data to hadoop HDFS.
	 * @param contents The data to be written
	 * @throws IOException
	 */
	public void saveData(final String contents) throws IOException {
		Configuration conf = new Configuration();
		// conf.set("yarn.resourcemanager.address","localhost:9005");
		// conf.set("fs.defaultFS","hdfs://localhost:9000");
		// conf.set("mapreduce.framework.name", "yarn");
		// conf.set("yarn.resourcemanager.scheduler.address", "localhost:8030");
		try (FileSystem fs = FileSystem.get(URI.create(""), conf)) {
			Path path = new Path(file);
			if (!fs.exists(path)) {
				try (FSDataOutputStream fsout2 = fs.create(path)) { }
			}
	
			try (FSDataOutputStream fsout = fs.append(path)) {
				try (PrintWriter writer = new PrintWriter(fsout)) {
					writer.append(contents);
					writer.println();
				}	
			}
		}
	}

}
