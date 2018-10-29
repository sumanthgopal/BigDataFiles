package lab3SecondarySort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Lab3Reducer extends Reducer<CompositeKeyWritable,NullWritable,CompositeKeyWritable,NullWritable>{
	@Override
	protected void reduce(CompositeKeyWritable key, Iterable<NullWritable> values,
			Reducer<CompositeKeyWritable, NullWritable, CompositeKeyWritable, NullWritable>.Context context) throws IOException, InterruptedException {
		
		for(NullWritable val:values) {
			try {
				context.write(key, val);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
