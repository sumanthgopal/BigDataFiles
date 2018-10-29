package wordCountDemo;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FixedLengthInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;



public class Driver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		// Create a new Job
		Configuration conf = new Configuration();
	     Job job = Job.getInstance(conf, "word count");
	     //System.out.println(args[0]);
	     //System.out.println(args[1]);
	     job.setJarByClass(Driver.class);
	     
	   //Specify Input Path
	    
	     job.setInputFormatClass(TextInputFormat.class);
	     
	     job.setOutputFormatClass(TextOutputFormat.class);
	     System.out.println();
	     job.setMapperClass(WordCountMapper.class);
	     
	     job.setReducerClass(WordCountReducer.class);
	     
	     job.setNumReduceTasks(2);
	     
	     //Specify Key Value
	     job.setOutputKeyClass(Text.class);
	     job.setOutputValueClass(IntWritable.class);
	     FileInputFormat.addInputPath(job, new Path(args[0]));
	     Path outputPath = new Path(args[1]);
	     FileSystem hdfs = FileSystem.get(conf);
	     if (hdfs.exists(outputPath)) {
				hdfs.delete(outputPath, true);
			}
	     FileOutputFormat.setOutputPath(job, outputPath);
	     System.exit(job.waitForCompletion(true) ? 0 : 1);
	     //Specify Output Path
	    
	}

}
