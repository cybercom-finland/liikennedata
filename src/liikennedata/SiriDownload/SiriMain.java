package liikennedata.SiriDownload;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class SiriMain {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

		 SiriDownload dl = new SiriDownload();
		 dl.download();
		
//	    Configuration conf = new Configuration();
//	    String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
//	    if (otherArgs.length != 2) {
//	      System.err.println("Usage: wordcount <in> <out>");
//	      System.exit(2);
//	    }
//	    Job job = new Job(conf, "word count");
//	    job.setJarByClass(SiriMain.class);
//	    job.setMapperClass(TokenizerMapper.class);
//	    job.setCombinerClass(IntSumReducer.class);
//	    job.setReducerClass(IntSumReducer.class);
//	    job.setOutputKeyClass(Text.class);
//	    job.setOutputValueClass(IntWritable.class);
//	    FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
//	    FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
//	    System.exit(job.waitForCompletion(true) ? 0 : 1);

//		System.exit(0); // Called when the execution ends. For some reason the
//						// process stays in background without this.
	}

	public static class IntSumReducer extends
			Reducer<Text, IntWritable, Text, IntWritable> {
		private IntWritable result = new IntWritable();

		public void reduce(Text key, Iterable<IntWritable> values,
				Context context) throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			result.set(sum);
			context.write(key, result);
		}
	}

	public static class TokenizerMapper extends
			Mapper<Object, Text, Text, IntWritable> {

		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();

		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			StringTokenizer itr = new StringTokenizer(value.toString());
			while (itr.hasMoreTokens()) {
				word.set(itr.nextToken());
				context.write(word, one);
			}
		}
	}
}
