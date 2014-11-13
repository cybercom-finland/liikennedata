package liikennedata.SiriDownload;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.log4j.Logger;

/**
 * Class for storing and (in the future, possibly) retrieving string data in Hadoop/HDFS.
 * 
 * @author lapel1
 *
 */
public class HadoopStorage {

	/**
	 * Whether to use HDFS or not.
	 * If true, stores data to HDFS.
	 * If false, stores data locally.
	 */
	private final boolean useHDFS = true;
	/**
	 * Filename to be used in HDFS for the data. File will be created if doesn't exist.
	 */
	private final String file = "siridata.txt";
	
	private Path path = new Path(file);
	
	private static Logger logger = Logger.getLogger(HadoopStorage.class);
	
	/**
	 * Buffer to use for writing
	 */
	private WriterBuffer buffer = new WriterBuffer();

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
		
		buffer.add(contents);
		ArrayList<String> bufferedData = buffer.getReadyBufferedContent();
		
		// Only store data if the buffer gave us some ready data. Buffer decides if we should store data or not.
		if (bufferedData.size() > 0) {
			
			if (!useHDFS) {
				try (PrintWriter out = new PrintWriter(new BufferedWriter(
				new FileWriter(SiriDownload.filePath
						+ file, true)))) {
					for (String str : bufferedData) {
						out.println(str);				
					}		
				}
			}
			
			else {
				try (FileSystem fs = DistributedFileSystem.get(conf)) {					
					// If the file doesn't exist, create it
					if (!fs.exists(path)) {
						try (FSDataOutputStream fsout2 = fs.create(path)) { }
					}
			
					try (FSDataOutputStream fsout = fs.append(path)) {
						try (PrintWriter writer = new PrintWriter(fsout)) {
							//logger.info("Starting append on thread: " + Thread.currentThread().getId() + " stamp: " + new SimpleDateFormat("yyyyddMM-HHmmss").format(new Date()));
							
							for (String str : bufferedData) {
								writer.println(str);
							}						
							
							//logger.info("Ending append on thread: " + Thread.currentThread().getId() + " stamp: " + new SimpleDateFormat("yyyyddMM-HHmmss").format(new Date()));
						}	catch (Exception e) {
							logger.error("writer error: " + e);
						}
					} catch (Exception e) {
						logger.error("FSData error: " + e);
					}
				} catch (Exception e) {
					logger.error("Stream error: " + e);
				}
			}
			
			buffer.emptyBuffer(); // We may have lost data (not saved it) due to exceptions.
		}
	}

}


