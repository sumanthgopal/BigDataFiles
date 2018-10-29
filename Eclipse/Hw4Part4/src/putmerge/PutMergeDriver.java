package putmerge;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class PutMergeDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Configuration conf = new Configuration();

        //FIleSystem Abstract class is used to get the conf key to connect to HDFS
        //FileSystem needs only one configuration key to successfully connect to HDFS. Previously it was fs.default.name. 
        //From yarn onward it's changed to fs.defaultFS. So the following snippet is sufficient for the connection. 
        FileSystem hdfs = FileSystem.get(conf);
        //For local connection l
        FileSystem local = FileSystem.getLocal(conf);

        Path inputDirectory = new Path(args[0]);
        Path hdfsFiles = new Path(args[1]);

        try {

            FileStatus[] inputFiles = local.listStatus(inputDirectory);
            FSDataOutputStream out = hdfs.create(hdfsFiles);

            for (int i = 0; i < inputFiles.length; i++) {
                //System.out.println(inputFiles[i].getPath().getName());

                FSDataInputStream in = local.open(inputFiles[i].getPath());
                byte buffer[] = new byte[256];
                int byteRead = 0;

                while ((byteRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, byteRead);
                }
                in.close();
            }
            out.close();
        } catch (Exception e) {
            System.out.print("We have an error");
        }
        
        Job job = Job.getInstance(conf, "PutMerge");
        job.setJarByClass(PutMergeDriver.class);
        job.setInputFormatClass(TextInputFormat.class);

		job.setOutputFormatClass(TextOutputFormat.class);
		job.setMapperClass(PutMergeMapper.class);

		job.setReducerClass(PutMergeReducer.class);

		job.setNumReduceTasks(1);

		// Specify Key Value
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FloatWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[2]));
        try {

            // For the job to wait unless one part of job is over
            System.exit(job.waitForCompletion(true)? 0 : 1);
            //To take the input path of multiple csvs
        } catch (InterruptedException ex) {
            System.out.print("Error 1 in main file %^#%$^&*(&&$%^&*(*^&%");
        }
	}

}
