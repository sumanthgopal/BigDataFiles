package putmerge;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PutMergeReducer extends Reducer<Text,FloatWritable,Text,FloatWritable>{

	@Override
	protected void reduce(Text key, Iterable<FloatWritable> values,
			Reducer<Text, FloatWritable, Text, FloatWritable>.Context context) throws IOException, InterruptedException {
		
		float maxPrice = 0;
		
		for(FloatWritable val: values) {
			if(val.get()> maxPrice) {
				maxPrice = val.get();
			}
		}
		context.write(key, new FloatWritable(maxPrice));
	}

}
