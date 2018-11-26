package com.edu.reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import com.me.writable.MedStdDevWritable;

public class MovieLensReducer extends Reducer<IntWritable, DoubleWritable, IntWritable, MedStdDevWritable> {

	MedStdDevWritable result = new MedStdDevWritable();
	ArrayList<Double> ratingList = new ArrayList<Double>();

	@Override
	protected void reduce(IntWritable key, Iterable<DoubleWritable> values,
			Reducer<IntWritable, DoubleWritable, IntWritable, MedStdDevWritable>.Context context)
			throws IOException, InterruptedException {

		double sum = 0.0;
		int count = 0;
		ratingList.clear();

		// calculate sum & count; add ratings into arraylist
		for (DoubleWritable value : values) {
			ratingList.add(value.get());
			sum += value.get();
			count += 1;
		}

		// sort arraylist
		Collections.sort(ratingList);

		// calculate median
		double median;
		if (count % 2 == 0) {
			median = (ratingList.get(count / 2 - 1) + ratingList.get(count / 2)) / 2;
			result.setMedian(String.valueOf(median));
		} else {
			median = ratingList.get(count / 2);
			result.setMedian(String.valueOf(median));
		}

		// calculate standard deviation
		double mean = sum / count;
		double sumOfSquares = 0.0;
		for (Double d : ratingList) {
			sumOfSquares += (d - mean) * (d - mean);
		}
		if(sumOfSquares == 0) {
			result.setStdDev("0");
		}else {
			double stdDev = Math.sqrt(sumOfSquares / (count - 1));
			result.setStdDev(String.valueOf(stdDev));
		}
		context.write(key, result);
	}

}
