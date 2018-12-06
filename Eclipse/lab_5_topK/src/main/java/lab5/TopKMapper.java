package lab5;

import java.io.IOException;
import java.util.TreeMap;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TopKMapper extends Mapper<Object, Text, NullWritable, Text> {
	
	private TreeMap<Integer, Text> recordsMap;
	
	@Override
	protected void setup(Mapper<Object, Text, NullWritable, Text>.Context context)
			throws IOException, InterruptedException {
		this.recordsMap = new TreeMap<Integer, Text>();
	}
	
	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, NullWritable, Text>.Context context)
			throws IOException, InterruptedException {
		
		String values[] = value.toString().split(",");
		try {
			String score = values[1];
			recordsMap.put(Integer.parseInt(score), new Text(value));
		}catch(Exception e){
		}
		
		if (recordsMap.size() > 10) {
			recordsMap.remove(recordsMap.firstKey());
		}
	}

	@Override
	protected void cleanup(Mapper<Object, Text, NullWritable, Text>.Context context)
			throws IOException, InterruptedException {
		for (Text t : recordsMap.values()) {
			context.write(NullWritable.get(), t);
		}
	}
}