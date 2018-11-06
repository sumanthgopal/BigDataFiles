package sec_sort;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class NKPartitioner extends Partitioner <CompositeKeyWritable, NullWritable>{

	@Override
	public int getPartition(CompositeKeyWritable arg0, NullWritable arg1, int numOfReducerTasks) {
		// TODO Auto-generated method stub
		int hash = arg0.getZipcode().hashCode();
		int partition = hash % numOfReducerTasks;
		return partition;
	}

}
