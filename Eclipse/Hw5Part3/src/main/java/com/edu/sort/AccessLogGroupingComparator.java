package com.edu.sort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import com.edu.writable.AccessLogWritable;

public class AccessLogGroupingComparator extends WritableComparator {

	public AccessLogGroupingComparator() {
		super(AccessLogWritable.class,true);
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		AccessLogWritable o1 = (AccessLogWritable) a;
		AccessLogWritable o2 = (AccessLogWritable) b;
		int res = o1.getIp().compareTo(o2.getIp());
		return res;
	}

}
