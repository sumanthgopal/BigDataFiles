import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Lab5Mapper extends Mapper<Object,Text,NullWritable,Text>{

	private TreeMap<Integer,Text> recMap;
	@Override
	protected void cleanup(Mapper<Object, Text, NullWritable, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		super.cleanup(context);
	}

	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, NullWritable, Text>.Context context)
			throws IOException, InterruptedException {
		String values[] = value.toString().split(",");
		try {
			String score = values[1];
			recMap.put(Integer.parseInt(score), new Text(value));
		}catch(Exception e) {
			
		}
		if(recMap.size() > 10) {
			
		}
	}

	@Override
	protected void setup(Mapper<Object, Text, NullWritable, Text>.Context context)
			throws IOException, InterruptedException {
		this.recMap = new TreeMap<Integer,Text>();
	}

	
}
