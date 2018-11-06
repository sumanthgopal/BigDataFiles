package hw4part5q1;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.reduce.IntSumReducer;

public class Hw4Part5ComFileIFDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "CombineIF");
		job.setJarByClass(Hw4Part5ComFileIFDriver.class);

		// Specify Input Path

		job.setInputFormatClass(CustomInputFormat.class);

		job.setOutputFormatClass(TextOutputFormat.class);
		job.setMapperClass(Hw4Part5ComFileIFMapper.class);

		job.setCombinerClass(IntSumReducer.class);
	    job.setReducerClass(IntSumReducer.class);

		job.setNumReduceTasks(1);

		// Specify Key Value
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		Path outputPath = new Path(args[1]);
		FileSystem hdfs = FileSystem.get(conf);

		if (hdfs.exists(outputPath)) {
			hdfs.delete(outputPath, true);
		}
		FileOutputFormat.setOutputPath(job, outputPath);
		System.exit(job.waitForCompletion(true) ? 0 : 1);

	}

}
