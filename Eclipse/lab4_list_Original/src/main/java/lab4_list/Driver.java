package lab4_list;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Driver {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Path inputPath = new Path(args[0]);
		Path outputDir = new Path(args[1]);
		
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		job.setJarByClass(lab4Mapper.class);
		
		job.setMapperClass(lab4Mapper.class);
		job.setReducerClass(lab4Reducer.class);
		job.setNumReduceTasks(1);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(DoubleWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(MedStdDevWritable.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		FileInputFormat.addInputPath(job, inputPath);
		
		FileOutputFormat.setOutputPath(job, outputDir);
		
		FileSystem hdfs = FileSystem.get(conf);
		if(hdfs.exists(outputDir)) {
			hdfs.delete(outputDir);
		}
		
		int code = job.waitForCompletion(true) ? 0 : 1;
		System.exit(code);
	}
}
