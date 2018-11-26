package com.edu.reducer;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import com.edu.writable.AccessLogWritable;

public class AccessLogSortReducer extends Reducer<AccessLogWritable, NullWritable, AccessLogWritable, NullWritable> {

	//private IntWritable total = new IntWritable();

	@Override
	protected void reduce(AccessLogWritable keyIn, Iterable<NullWritable> valueIn,
			Reducer<AccessLogWritable, NullWritable, AccessLogWritable, NullWritable>.Context context)
			throws IOException, InterruptedException {

		/*int sum = 0;
		for (IntWritable a : valueIn) {
			sum += a.get();
		}
		total.set(sum);*/
		for (NullWritable val : valueIn) {
			context.write(keyIn, val);
		}
	}

}
