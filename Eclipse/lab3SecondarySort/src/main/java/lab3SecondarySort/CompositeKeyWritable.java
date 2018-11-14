package lab3SecondarySort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

public class CompositeKeyWritable implements Writable, WritableComparable<CompositeKeyWritable> {
	private String zipCode;
	private String bikeId;

	public CompositeKeyWritable() {
		super();
	}

	public CompositeKeyWritable(String zipCode, String bikeId) {
		this.zipCode = zipCode;
		this.bikeId = bikeId;
	}

	@Override
	public String toString() {
		return "CompositeKeyWritable [zipCode=" + zipCode + ", bikeId=" + bikeId + "]";
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getBikeId() {
		return bikeId;
	}

	public void setBikeId(String bikeId) {
		this.bikeId = bikeId;
	}

	public int compareTo(CompositeKeyWritable o) {
		int result = zipCode.compareTo(o.zipCode);
		if (result == 0) {
			result = bikeId.compareTo(o.bikeId);
		}
		return result;
	}

	public void write(DataOutput out) throws IOException {
		out.writeUTF(zipCode);
		out.writeUTF(bikeId);
	}

	public void readFields(DataInput in) throws IOException {
		zipCode = in.readUTF();
		bikeId = in.readUTF();
	}
}
