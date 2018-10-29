import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class KeyValueTextIFMapper extends Mapper<Text, Text, Text, Text>{
	private final static IntWritable one = new IntWritable(1);

	@Override
	protected void map(Text key, Text value, Mapper<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {

		System.out.println(key);
		System.out.println(value);
		context.write(key, value);
	}
}
