package sec_sort;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class lab3Mapper extends Mapper <Object, Text, CompositeKeyWritable, NullWritable>{

	@Override
	public void map(Object key, Text value, Mapper<Object, Text, CompositeKeyWritable, NullWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String values[] = value.toString().split(",");
		String zipcode = null;
		String bikeId = null;
		
		try {
			zipcode = Integer.valueOf(values[10]).toString();
			bikeId = values[8];
		}catch(Exception e) {
			// print()
		}
		
		if(zipcode != null && bikeId != null) {
			CompositeKeyWritable cw = new CompositeKeyWritable(zipcode, bikeId);
			
			try {
				context.write(cw, NullWritable.get());
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
