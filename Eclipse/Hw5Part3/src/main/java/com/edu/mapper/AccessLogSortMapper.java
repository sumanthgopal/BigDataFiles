package com.edu.mapper;

import java.io.IOException;
import java.util.Date;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.edu.writable.AccessLogWritable;


public class AccessLogSortMapper extends Mapper<LongWritable, Text, AccessLogWritable, IntWritable>{
	
	private static final IntWritable one = new IntWritable(1);
	private static AccessLogWritable val;

	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, AccessLogWritable, IntWritable>.Context context)
			throws IOException, InterruptedException {
		String[] accessValues = value.toString().split("\\s");
		val = new AccessLogWritable(accessValues[0], new Date(accessValues[3].replaceAll("[\\[]", "")));
		context.write(val, one);
	}
	
	

}
