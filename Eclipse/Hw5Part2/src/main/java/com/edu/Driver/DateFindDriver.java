package com.edu.Driver;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.edu.Mapper.DateFindMapper;
import com.edu.reducer.DateFindReducer;
import com.edu.writable.DateCustomWritable;

public class DateFindDriver {

	public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		try {
			Path inputPath = new Path(args[0]);
			Path outputPath = new Path(args[1]);
			Job job = Job.getInstance(conf,"Finding Date");
			job.setJarByClass(DateFindDriver.class);
			job.setInputFormatClass(TextInputFormat.class);
			job.setMapperClass(DateFindMapper.class);
			job.setReducerClass(DateFindReducer.class);
			job.setNumReduceTasks(1);
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(DateCustomWritable.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			FileInputFormat.addInputPath(job, inputPath);
			FileSystem hdfs = FileSystem.get(conf);
			if (hdfs.exists(outputPath)) {
				hdfs.delete(outputPath, true);
			}
			FileOutputFormat.setOutputPath(job, outputPath);
			System.exit(job.waitForCompletion(true) ? 0 : 1);
		} catch (IOException e) {
			System.out.println("Error during Job");
		}

	}

}
