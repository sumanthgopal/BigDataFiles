import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SeqFileIFReducer extends Reducer<LongWritable, Text, LongWritable, Text>{
	private IntWritable total = new IntWritable();

	@Override
	protected void reduce(LongWritable key, Iterable<Text> values,
			Context context) throws IOException, InterruptedException {
		
		for(Text val: values) {
			context.write(key, val);
		}
	}

}
