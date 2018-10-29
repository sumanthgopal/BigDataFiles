package sec_sort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupingComparator extends WritableComparator{

	protected GroupingComparator() {
		super(CompositeKeyWritable.class, true);
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		
		CompositeKeyWritable cw1 = (CompositeKeyWritable) a;
		CompositeKeyWritable cw2 = (CompositeKeyWritable) b;
		
		int comResult = cw1.getZipcode().compareTo(cw2.getZipcode());
		
		return comResult;
	}
}
