package sec_sort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SecondarySortComparator extends WritableComparator{

	protected SecondarySortComparator() {
		super(CompositeKeyWritable.class, true);
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		// TODO Auto-generated method stub
		
		CompositeKeyWritable cw1 = (CompositeKeyWritable) a;
		CompositeKeyWritable cw2 = (CompositeKeyWritable) b;
		
		int comResult = cw1.getZipcode().compareTo(cw2.getZipcode());
		
		if (comResult == 0) {
			return -cw1.getBikeId().compareTo(cw2.getBikeId());
		}
		
		return comResult;
	}

}
