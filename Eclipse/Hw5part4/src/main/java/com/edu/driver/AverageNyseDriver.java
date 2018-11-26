package com.edu.driver;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import com.edu.mapper.AverageNyseMapper;
import com.me.reducer.AverageNyseReducer;

public class AverageNyseDriver {
	
	public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {
		
	
	Configuration conf = new Configuration();
	Job job = Job.getInstance(conf, "AverageNyse");
	job.setJarByClass(AverageNyseDriver.class);

	job.setInputFormatClass(TextInputFormat.class);

	job.setOutputFormatClass(TextOutputFormat.class);
	job.setMapperClass(AverageNyseMapper.class);
	job.setCombinerClass(AverageNyseReducer.class);
	job.setReducerClass(AverageNyseReducer.class);

	job.setNumReduceTasks(1);

	job.setMapOutputKeyClass(IntWritable.class);
	job.setMapOutputValueClass(DoubleWritable.class);
	job.setOutputKeyClass(IntWritable.class);
	job.setOutputValueClass(DoubleWritable.class);
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
