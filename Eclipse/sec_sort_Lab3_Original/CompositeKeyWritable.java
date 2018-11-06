package sec_sort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

public class CompositeKeyWritable implements Writable, WritableComparable<CompositeKeyWritable>{
	
	private String zipcode;
	private String bikeId;
	
	public CompositeKeyWritable() {
		super();
	}

	public CompositeKeyWritable(String zipcode, String bikeId) {
		super();
		this.zipcode = zipcode;
		this.bikeId = bikeId;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getBikeId() {
		return bikeId;
	}

	public void setBikeId(String bikeId) {
		this.bikeId = bikeId;
	}

	@Override
	public String toString() {
		return "CompositeKeyWritable [zipcode=" + zipcode + ", bikeId=" + bikeId + "]";
	}

	public int compareTo(CompositeKeyWritable o) {
		// TODO Auto-generated method stub
		int result = zipcode.compareTo(o.zipcode);
		
		if(result == 0) {
			result = bikeId.compareTo(o.bikeId);
		}
		return result;
	}

	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeUTF(zipcode);
		out.writeUTF(bikeId);
		
	}

	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		zipcode = in.readUTF();
		bikeId = in.readUTF();
	}
	
	
}
