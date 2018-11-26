package com.edu.mapper;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SortedMapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MovieLensOptiMapper extends Mapper<LongWritable, Text, IntWritable, SortedMapWritable> {

	IntWritable movieId = new IntWritable();
	DoubleWritable rating = new DoubleWritable();
	IntWritable one = new IntWritable(1);

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

		String values[] = value.toString().split("::");
		try {
			movieId.set(Integer.parseInt(values[1]));
			rating.set(Double.parseDouble(values[2]));

			SortedMapWritable ratingResult = new SortedMapWritable();
			ratingResult.put(rating, one);

			context.write(movieId, ratingResult);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
