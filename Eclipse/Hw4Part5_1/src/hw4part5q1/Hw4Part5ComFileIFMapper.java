package hw4part5q1;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class Hw4Part5ComFileIFMapper extends Mapper<WordOffset, Text, Text, IntWritable>{
	 private final static IntWritable one = new IntWritable(1);
	    private Text word = new Text();

	@Override
	protected void map(WordOffset key, Text value, Context context)
			throws IOException, InterruptedException {

		String line = value.toString();
	      StringTokenizer itr = new StringTokenizer(line);
	      while (itr.hasMoreTokens()) {
	        word.set(itr.nextToken());
	        context.write(word, one);
	      }
	}
}
