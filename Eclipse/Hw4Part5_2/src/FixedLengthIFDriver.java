import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FixedLengthInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class FixedLengthIFDriver {

	public static void main(String[] args) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		FixedLengthInputFormat.setRecordLength(conf, 5);
	     Job job = Job.getInstance(conf, "FixedLengthIFDriver");
	     //System.out.println(args[0]);
	     //System.out.println(args[1]);
	     job.setJarByClass(FixedLengthIFDriver.class);
	     
	   //Specify Input Path
	     job.setInputFormatClass(FixedLengthInputFormat.class);
	     
	     //job.setOutputFormatClass(TextOutputFormat.class);
	     System.out.println();
	     job.setMapperClass(FixedLengthIFMapper.class);
	     
	     job.setReducerClass(FixedLengthIFReducer.class);
	     
	     job.setNumReduceTasks(1);
	     
	     //Specify Key Value
	     job.setOutputKeyClass(LongWritable.class); 
	     job.setOutputValueClass(BytesWritable.class);
	     FileInputFormat.addInputPath(job, new Path(args[0]));
	     Path outputPath = new Path(args[1]);
	     FileSystem hdfs = FileSystem.get(conf);
	     if (hdfs.exists(outputPath)) {
				hdfs.delete(outputPath, true);
			}
	     FileOutputFormat.setOutputPath(job, outputPath);
	     System.exit(job.waitForCompletion(true) ? 0 : 1);

	}

}
