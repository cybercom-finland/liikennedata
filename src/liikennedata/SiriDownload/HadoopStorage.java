package liikennedata.SiriDownload;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HadoopStorage {
	
	private final String uri = "hdfs://10.33.24.20:9000";
	private final String file = "in.txt";
	
	public void saveData(final String contents) throws IOException {
		try (PrintWriter out = new PrintWriter(new BufferedWriter(
				new FileWriter(SiriDownload.filePath + SiriDownload.outputFileName, true)))) {
			out.println(contents);
		}
	}
	
//	public void saveData(final String contents) throws IOException {
//		Configuration conf = new Configuration();
//		 conf.set("yarn.resourcemanager.address","localhost:9005");
//		 conf.set("fs.defaultFS","hdfs://localhost:9000");
//		 conf.set("mapreduce.framework.name", "yarn");
//		 conf.set("yarn.resourcemanager.scheduler.address",
//		 "localhost:8030");
//		FileSystem fs = FileSystem.get(URI.create(""), conf);
//		Path homeDir=fs.getHomeDirectory();
//       //Print the home directory
//       System.out.println("Home folder: " +homeDir); 
//		boolean flag = Boolean.getBoolean(fs.getConf().get("dfs.support.append"));
//
//		System.out.println("dfs.support.append is set to be " + flag);
//
//		if (flag) {
//			FSDataOutputStream fsout = fs.append(new Path(file));
//			PrintWriter writer = new PrintWriter(fsout);
//			writer.append(contents);
//			writer.println();
//			writer.close();
//			fsout.close();
//		} else {
//			System.err.println("please set the dfs.support.append to be true");
//		}
//
//		fs.close();
//	}
	

}
