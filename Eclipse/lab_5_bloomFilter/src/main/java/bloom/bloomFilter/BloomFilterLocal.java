package bloom.bloomFilter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Sink;


public class BloomFilterLocal {
	public static class BloomFilterMapper extends Mapper<Object, Text, Text, NullWritable> {
		Funnel<Person> p = new Funnel<Person>() {

			public void funnel(Person person, Sink into) {
				// TODO Auto-generated method stub
				into.putString(person.firstName, Charsets.UTF_8)
						.putString(person.lastName, Charsets.UTF_8);
			}

		};
		private BloomFilter<Person> friends = BloomFilter.create(p, 500, 0.1);

		@Override
		public void setup(Context context) throws IOException, InterruptedException {

			Person p1 = new Person("Abby", "Lahm");
			Person p2 = new Person( "Jamie", "Scott");
			
			ArrayList<Person> friendsList = new ArrayList<Person>();
			friendsList.add(p1);
			friendsList.add(p2);

			for (Person pr : friendsList) {
				friends.put(pr);
			}
		}

		@Override
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			String values[] = value.toString().split(",");
			Person p = new Person( values[1], values[2]);

			if (friends.mightContain(p)) {
				context.write(value, NullWritable.get());
			}

		}

	}

	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration();
		Path inputPath = new Path(args[0]);
		Path outputDir = new Path(args[1]);
		Job job = Job.getInstance(conf, "Bloom Filter");
		job.setJarByClass(BloomFilterLocal.class);
		job.setMapperClass(BloomFilterMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(NullWritable.class);
		job.setNumReduceTasks(0);
		FileInputFormat.addInputPath(job, inputPath);
		FileOutputFormat.setOutputPath(job, outputDir);
		// delete output if exist
				FileSystem hdfs = FileSystem.get(conf);
				if (hdfs.exists(outputDir))
					hdfs.delete(outputDir, true);

		boolean success = job.waitForCompletion(true);
		System.out.println(success);

	}
}
