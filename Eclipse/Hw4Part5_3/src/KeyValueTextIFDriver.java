import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class KeyValueTextIFDriver {

	public static void main(String[] args) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		conf.set("key.value.separator.in.input.line", ",");
		Job job = Job.getInstance(conf, "Access Log");
		job.setJarByClass(KeyValueTextIFDriver.class);

		// Specify Input Path

		job.setInputFormatClass(KeyValueTextInputFormat.class);

		//job.setOutputFormatClass(TextOutputFormat.class);
		job.setMapperClass(KeyValueTextIFMapper.class);

		job.setReducerClass(KeyValueTextIFReducer.class);

		job.setNumReduceTasks(1);
		job.setOutputKeyClass(Text.class); 
	      job.setOutputValueClass(Text.class); 

		// Specify Key Value
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
