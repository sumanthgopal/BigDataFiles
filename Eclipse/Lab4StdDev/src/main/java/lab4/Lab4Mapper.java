package lab4;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SortedMapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Lab4Mapper extends Mapper<LongWritable, Text, Text, SortedMapWritable> {
	
	Text brand = new Text();
	DoubleWritable rating = new DoubleWritable();
	IntWritable one = new IntWritable(1);

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

		String values[] = value.toString().split(",");
//		System.err.println(values[0]);
		try {
			brand.set(values[0]);
			rating.set(Double.parseDouble(values[2]));
			
			SortedMapWritable ratingResult = new SortedMapWritable();
			ratingResult.put(rating, one);
			
//			System.err.println(ratingResult.keySet());
			context.write(brand, ratingResult);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}