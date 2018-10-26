package lab;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class wordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>  {
	
	
}
