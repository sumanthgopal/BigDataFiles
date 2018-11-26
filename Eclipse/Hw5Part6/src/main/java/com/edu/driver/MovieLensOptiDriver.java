package com.edu.driver;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SortedMapWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.edu.combiner.MovieLensCombiner;
import com.edu.mapper.MovieLensOptiMapper;
import com.edu.reducer.MovieLensOptiReducer;
import com.edu.writable.MedianStdDevWritable;

public class MovieLensOptiDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		Path inputPath = new Path(args[0]);
		Path outputDir = new Path(args[1]);
		
		Configuration conf = new Configuration();
		
		Job job = Job.getInstance(conf);
		
		job.setJarByClass(MovieLensOptiDriver.class);
		job.setMapperClass(MovieLensOptiMapper.class);
		job.setCombinerClass(MovieLensCombiner.class);
		job.setReducerClass(MovieLensOptiReducer.class);
		job.setNumReduceTasks(1);
		
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(SortedMapWritable.class);
		
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(MedianStdDevWritable.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		FileInputFormat.addInputPath(job, inputPath);
		
		FileSystem hdfs = FileSystem.get(conf);
		if(hdfs.exists(outputDir)) {
			hdfs.delete(outputDir);
		}
		
		FileOutputFormat.setOutputPath(job, outputDir);
		int code = job.waitForCompletion(true) ? 0 :1;
		System.exit(code);

	}

}
