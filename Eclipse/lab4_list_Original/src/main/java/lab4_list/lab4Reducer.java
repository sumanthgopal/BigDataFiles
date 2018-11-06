package lab4_list;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class lab4Reducer extends Reducer <Text, DoubleWritable, Text, MedStdDevWritable>{

	MedStdDevWritable result = new MedStdDevWritable();
	ArrayList<Double> ratingList = new ArrayList<Double>();
	
	@Override
	protected void reduce(Text arg0, Iterable<DoubleWritable> arg1,
			Reducer<Text, DoubleWritable, Text, MedStdDevWritable>.Context arg2)
			throws IOException, InterruptedException {
		
		int counter = 0;
		double sum = 0.0;
		
		for (DoubleWritable val : arg1) {
			ratingList.add(val.get());
			counter += 1;
			sum += val.get();
		}
		
		Collections.sort(ratingList);
		
		//find median
		double median;
		if (counter % 2 == 0) {
			median = (ratingList.get(counter/2 - 1) + ratingList.get(counter/2)) / 2;
		}else {
			median = ratingList.get(counter/2);
		}
		result.setMedian(String.valueOf(median));
		
		//find StdDev
		double means = sum/counter;
		double sumOfSqares = 0.0;
		for(double d : ratingList) {
			sumOfSqares += (d - means) * (d - means);
		}
		double stdDev = Math.sqrt(sumOfSqares/(counter-1));
		result.setStdDev(String.valueOf(stdDev));
		
		arg2.write(arg0, result);
	}

}
