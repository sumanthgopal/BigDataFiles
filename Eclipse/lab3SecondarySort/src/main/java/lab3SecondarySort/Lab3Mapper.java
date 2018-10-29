package lab3SecondarySort;
import java.io.IOException;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Lab3Mapper extends Mapper<Object,Text,CompositeKeyWritable,NullWritable>{
	protected void map(Object key, Text value, Mapper<Object, Text, CompositeKeyWritable, NullWritable>.Context context)
			throws IOException, InterruptedException {

		String[] values = value.toString().split(",");
		String zipcode = null;
		String bikeId = null;
		try {
			zipcode = values[10];
			bikeId = values[8];
		}catch(Exception e) {
			System.out.println(e);
		}
		
		if(zipcode!=null && bikeId!=null) {
			CompositeKeyWritable cw = new CompositeKeyWritable(zipcode,bikeId);
			
			try {
				context.write(cw, NullWritable.get());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
