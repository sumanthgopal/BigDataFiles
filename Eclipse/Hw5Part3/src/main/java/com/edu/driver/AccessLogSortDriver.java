package com.edu.driver;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;

public class AccessLogSortDriver {

	public static void main(String[] args) {
		Configuration conf = new Configuration();
		try {
			Path inputPath = new Path(args[0]);
			Path outputPath = new Path(args[1]);
			Job job = Job.getInstance(conf,"AccessLogSorting");
		} catch (IOException e) {
			System.out.println("Error in Job Creation");
		}

	}

}
