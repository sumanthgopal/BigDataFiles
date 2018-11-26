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

import com.edu.mapper.MovieLensMapper;
import com.edu.reducer.MovieLensReducer;
import com.me.writable.MedStdDevWritable;

public class MovieLensDriver {

	public static void main(String[] args)throws IOException, ClassNotFoundException, InterruptedException {
		
		Path inputPath = new Path(args[0]);
		Path outputDir = new Path(args[1]);
		
		Configuration conf = new Configuration();
		
		Job job = Job.getInstance(conf);
		
		job.setJarByClass(MovieLensDriver.class);
		job.setMapperClass(MovieLensMapper.class);
		job.setReducerClass(MovieLensReducer.class);
		job.setNumReduceTasks(1);
		
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(DoubleWritable.class);
		
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(MedStdDevWritable.class);
		
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
