package com.edu.writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

public class MedianStdDevWritable implements Writable, WritableComparable<MedianStdDevWritable> {

	private String median;
	private String stdDev;

	public MedianStdDevWritable() {
		super();
	}

	public MedianStdDevWritable(String d, String l) {

		this.median = d;
		this.stdDev = l;
	}

	public int compareTo(MedianStdDevWritable o) {

		int result = median.compareTo(o.median);
		if (result == 0) {
			result = stdDev.compareTo(o.stdDev);
		}
		return result;
	}

	public void write(DataOutput d) throws IOException {
		d.writeUTF(median);
		d.writeUTF(stdDev);
	}

	public void readFields(DataInput di) throws IOException {

		median = di.readUTF();
		stdDev = di.readUTF();
	}

	public String getMedian() {
		return median;
	}

	public void setMedian(String median) {
		this.median = median;
	}

	public String getStdDev() {
		return stdDev;
	}

	public void setStdDev(String stdDev) {
		this.stdDev = stdDev;
	}

	@Override
	public String toString() {
		return " [median=" + median + ", stdDev=" + stdDev + "]";
	}
}
