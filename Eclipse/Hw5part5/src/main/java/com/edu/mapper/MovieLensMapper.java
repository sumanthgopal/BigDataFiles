package com.edu.mapper;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MovieLensMapper extends Mapper<LongWritable,Text, IntWritable,DoubleWritable>{

	IntWritable movieId = new IntWritable();
	DoubleWritable rating = new DoubleWritable();

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, IntWritable, DoubleWritable>.Context context)
			throws IOException, InterruptedException {
		String values[] = value.toString().split("::");
		try {
			movieId.set(Integer.parseInt(values[1]));
			rating.set(Double.parseDouble(values[2]));
		}catch(Exception e) {
			System.out.println("Error in Parsing movie ID "+e);
		}
		context.write(movieId, rating);
	}

}
