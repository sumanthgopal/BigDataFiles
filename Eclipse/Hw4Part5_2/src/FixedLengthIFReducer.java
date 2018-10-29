import java.io.IOException;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FixedLengthIFReducer extends Reducer<LongWritable, BytesWritable, Text, LongWritable> {
	@Override
	protected void reduce(LongWritable key, Iterable<BytesWritable> values, 
			Reducer<LongWritable, BytesWritable, Text, LongWritable>.Context context) throws IOException, InterruptedException {
		
		for (BytesWritable val : values) {
			byte[] bytes = val.getBytes();
			Text t = new Text(bytes);
		      context.write(t,key);
		  }
	}
}
