package wordCountDemo;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;



public class Driver {

	public static void main(String[] args) throws IOException {
		
		// Create a new Job
	     Job job = Job.getInstance();
	     //System.out.println(args[0]);
	     //System.out.println(args[1]);
	     job.setJarByClass(Driver.class);
	     
	   //Specify Input Path
	     FileInputFormat.addInputPath(job, new Path(args[0]));
	     FileOutputFormat.setOutputPath(job, new Path(args[1]));
	     
	    
	     job.setInputFormatClass(TextInputFormat.class);
	     
	     job.setOutputFormatClass(TextOutputFormat.class);
	     System.out.println();
	     job.setMapperClass(WordCountMapper.class);
	     
	     job.setReducerClass(WordCountReducer.class);
	     
	     job.setNumReduceTasks(2);
	     
	     //Specify Key Value
	     job.setOutputKeyClass(Text.class);
	     job.setOutputValueClass(IntWritable.class);
	     System.out.println(FileOutputFormat.getOutputPath(job));
	     
	     //Specify Output Path
	    
	}

}
