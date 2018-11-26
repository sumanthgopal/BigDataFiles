package com.me.reducer;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;


public class AverageNyseReducer extends Reducer<IntWritable,DoubleWritable,IntWritable,DoubleWritable>{
	
	private DoubleWritable average;

	@Override
	protected void reduce(IntWritable keyIn, Iterable<DoubleWritable> valueIn,
			Reducer< IntWritable,DoubleWritable, IntWritable, DoubleWritable>.Context context)
			throws IOException, InterruptedException {
		double sum = 0;
		double avg = 0;
		int count = 0;
		for(DoubleWritable d:valueIn) {
			sum += d.get();
			count++;
		}
		avg = sum/count;
		average = new DoubleWritable(avg);
		context.write(keyIn, average);
	}

	
}
