package lab4;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SortedMapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Reducer;
 
public class Lab4Reducer extends Reducer<Text, SortedMapWritable, Text, MedianStdDevWritable> {
	
	MedianStdDevWritable result = new MedianStdDevWritable();
	TreeMap<Double, Integer> ratingCount = new TreeMap<Double, Integer>();
	
	public void reduce(Text key,Iterable<SortedMapWritable> values,Context context) throws IOException, InterruptedException {
		
		double sum = 0.0;
		int totalAmount = 0;
		ratingCount.clear();
		System.out.println("reducer");
		for(SortedMapWritable value :values){
			System.out.println("In");
			for(Entry<WritableComparable, Writable> entry : value.entrySet()) {
				
				
				Double rate = ((DoubleWritable) entry.getKey()).get();
				int count = ((IntWritable) entry.getValue()).get();
				
				totalAmount += count;
				sum += rate * count;
				
				Integer storedCount = ratingCount.get(rate);
				if(storedCount == null) {
					ratingCount.put(rate, count);
				}else {
					ratingCount.put(rate, storedCount + count);
				}
			}
			
			int medianIndex = totalAmount/2;
			int prevRatingCount = 0;
			int RatingCount = 0;
			double prevKey = 0.0;
			
			for(Entry<Double, Integer> entry : ratingCount.entrySet()) {
				
				RatingCount = prevRatingCount + entry.getValue();
				
				if(prevRatingCount <= medianIndex && medianIndex < RatingCount) {
					
					if(totalAmount % 2 == 0 && prevRatingCount == medianIndex) {
						double median = (double) (entry.getKey() + prevKey) / 2.0;
						result.setMedian(String.valueOf(median));
					}else {
						result.setMedian(String.valueOf(entry.getKey()));
					}
					break;
				}
				prevRatingCount = RatingCount;
				prevKey = entry.getKey();
			}
			
			
			// calculate StdDev
			double mean = sum/totalAmount;
			double sumOfSquare = 0.0;
			
			for(Entry<Double, Integer> entry : ratingCount.entrySet()) {
				sumOfSquare += (entry.getKey() - mean) * (entry.getKey() - mean) * entry.getValue();
			}
			double stdDev = Math.sqrt(sumOfSquare/(totalAmount - 1));
			result.setStdDev(String.valueOf(stdDev));
			
			context.write(key, result);
		}
	}
}