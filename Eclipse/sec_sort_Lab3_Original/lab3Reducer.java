package sec_sort;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class lab3Reducer extends Reducer <CompositeKeyWritable, NullWritable, CompositeKeyWritable, NullWritable>{

	@Override
	protected void reduce(CompositeKeyWritable arg0, Iterable<NullWritable> arg1,
			Reducer<CompositeKeyWritable, NullWritable, CompositeKeyWritable, NullWritable>.Context arg2)
			throws IOException, InterruptedException {
		
		for (NullWritable val : arg1) {
			try {
				arg2.write(arg0, val);
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
