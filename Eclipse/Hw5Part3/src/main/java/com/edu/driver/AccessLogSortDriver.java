package com.edu.driver;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.edu.mapper.AccessLogSortMapper;
import com.edu.reducer.AccessLogSortReducer;
import com.edu.sort.AccessLogGroupingComparator;
import com.edu.sort.AccessLogPartitioner;
import com.edu.sort.AccessLogSecSort;
import com.edu.writable.AccessLogWritable;

public class AccessLogSortDriver {

	public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		try {
			Path inputPath = new Path(args[0]);
			Path outputPath = new Path(args[1]);
			Job job = Job.getInstance(conf, "AccessLogSorting");
			job.setJarByClass(AccessLogSortDriver.class);

			job.setPartitionerClass(AccessLogPartitioner.class);
			job.setGroupingComparatorClass(AccessLogGroupingComparator.class);
			job.setSortComparatorClass(AccessLogSecSort.class);
			
			job.setMapperClass(AccessLogSortMapper.class);
			job.setReducerClass(AccessLogSortReducer.class);
			
			job.setNumReduceTasks(1);

			job.setInputFormatClass(TextInputFormat.class);

			job.setMapOutputKeyClass(AccessLogWritable.class);
			job.setMapOutputValueClass(NullWritable.class);
			job.setOutputKeyClass(AccessLogWritable.class);
			job.setOutputValueClass(NullWritable.class);


			FileInputFormat.addInputPath(job, inputPath);

			FileSystem hdfs = FileSystem.get(conf);
			if (hdfs.exists(outputPath)) {
				hdfs.delete(outputPath, true);
			}
			FileOutputFormat.setOutputPath(job, outputPath);
			System.exit(job.waitForCompletion(true) ? 0 : 1);

		} catch (IOException e) {
			System.out.println("Error in Job Creation "+e);
		}

	}

}
