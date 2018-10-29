import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SeqFileIFMapper extends Mapper<LongWritable, Text, LongWritable, Text>{

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		/*System.out.println("in");
		String[] fields = value.toString().split(" ");
		for(String s:fields) {
			Text t = new Text(s);
			context.write(t, one);
		}*/
		context.write(new LongWritable(Long.parseLong(key.toString())), value);
	}

}
