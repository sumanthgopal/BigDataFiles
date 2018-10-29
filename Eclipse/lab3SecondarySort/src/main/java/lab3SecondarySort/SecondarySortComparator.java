package lab3SecondarySort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SecondarySortComparator extends WritableComparator{

	protected SecondarySortComparator() {
		super(CompositeKeyWritable.class,true);
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		
		CompositeKeyWritable cw1 = (CompositeKeyWritable) a;
		CompositeKeyWritable cw2 = (CompositeKeyWritable) b;
		int comResult = cw1.getZipCode().compareTo(cw2.getZipCode());
		if(comResult == 0) {
			return -cw1.getBikeId().compareTo(cw2.getBikeId());
		}
		return comResult;
	}
	
	
}
