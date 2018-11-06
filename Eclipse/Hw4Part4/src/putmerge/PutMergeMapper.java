package putmerge;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PutMergeMapper extends Mapper<LongWritable, Text, Text, FloatWritable>{
	

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, FloatWritable>.Context context)
			throws IOException, InterruptedException {

		String[] fields = value.toString().split(",");
		Text t = new Text(fields[1]);
		FloatWritable f = new FloatWritable();
		try {
			f.set(Float.parseFloat(fields[4]));
		}catch(Exception e) {
			//System.out.println("Error in float");
			f.set(0);
		}
		context.write(t, f);
	}

}
