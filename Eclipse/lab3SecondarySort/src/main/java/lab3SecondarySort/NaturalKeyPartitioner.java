package lab3SecondarySort;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class NaturalKeyPartitioner extends Partitioner<CompositeKeyWritable,NullWritable>{

	@Override
	public int getPartition(CompositeKeyWritable key, NullWritable value, int numOfReducedTasks) {
		int hash = key.getZipCode().hashCode();
		System.out.println("Partitioner "+numOfReducedTasks);
		int partition = hash % numOfReducedTasks;
		return partition;
	}

}
