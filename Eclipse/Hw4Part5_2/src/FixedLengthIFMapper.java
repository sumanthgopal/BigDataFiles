import java.io.IOException;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class FixedLengthIFMapper extends Mapper<LongWritable, BytesWritable, LongWritable, BytesWritable>{
	@Override
	protected void map(LongWritable key, BytesWritable value, Context context)
			throws IOException, InterruptedException {
			
		System.out.println(key);
		System.out.println(new String(value.getBytes()));
		context.write(key,value);
	}
}
