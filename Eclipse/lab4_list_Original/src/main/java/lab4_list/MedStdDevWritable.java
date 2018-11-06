package lab4_list;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

public class MedStdDevWritable implements Writable, WritableComparable<MedStdDevWritable>{

	public String median;
	public String StdDev;
	
	public MedStdDevWritable(String median, String stdDev) {
		super();
		this.median = median;
		StdDev = stdDev;
	}

	public MedStdDevWritable() {
		super();
	}

	public String getMedian() {
		return median;
	}

	public void setMedian(String median) {
		this.median = median;
	}

	public String getStdDev() {
		return StdDev;
	}

	public void setStdDev(String stdDev) {
		StdDev = stdDev;
	}

	public int compareTo(MedStdDevWritable o) {
		// TODO Auto-generated method stub
		int result = median.compareTo(o.median);
		if(result == 0) {
			result = StdDev.compareTo(o.StdDev);
		}	
		return result;
	}

	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeUTF(median);
		out.writeUTF(StdDev);
	}

	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		median = in.readUTF();
		StdDev = in.readUTF();
	}

	@Override
	public String toString() {
		return "[median=" + median + ", StdDev=" + StdDev + "]";
	}
}
