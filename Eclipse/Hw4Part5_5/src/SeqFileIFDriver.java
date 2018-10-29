import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile.CompressionType;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.DefaultCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class SeqFileIFDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "Access Log");
		job.setJarByClass(SeqFileIFDriver.class);

		job.setInputFormatClass(SequenceFileInputFormat.class);

		job.setMapperClass(SeqFileIFMapper.class);

		job.setReducerClass(SeqFileIFReducer.class);

		job.setNumReduceTasks(0);

		job.setMapOutputKeyClass(LongWritable.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		/*Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "Access Log");
	    job.setJobName("Convert Text");
	    job.setJarByClass(Mapper.class);

	    job.setMapperClass(Mapper.class);
	    job.setReducerClass(Reducer.class);

	    // increase if you need sorting or a special number of files
	    job.setNumReduceTasks(0);

	    job.setOutputKeyClass(LongWritable.class);
	    job.setOutputValueClass(Text.class);

	    job.setOutputFormatClass(SequenceFileOutputFormat.class);
	    job.setInputFormatClass(TextInputFormat.class);*/
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
