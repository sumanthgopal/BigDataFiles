package com.edu.reducer;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import com.edu.writable.AccessLogWritable;

public class AccessLogSortReducer extends Reducer<AccessLogWritable, IntWritable, AccessLogWritable, IntWritable>{
	
	private IntWritable total = new IntWritable();

	@Override
	protected void reduce(AccessLogWritable keyIn, Iterable<IntWritable> valueIn,
			Reducer<AccessLogWritable, IntWritable, AccessLogWritable, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		int sum = 0;
		for(IntWritable a: valueIn) {
			sum += a.get();
		}
		total.set(sum);
		context.write(keyIn, total);
	}

}
