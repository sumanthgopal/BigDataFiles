package com.edu.writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Date;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

public class AccessLogWritable implements Writable, WritableComparable<AccessLogWritable>{
	
	public String ip;
	public Date accessDate;
	
	

	public AccessLogWritable() {
		super();
	}

	public AccessLogWritable(String ip, Date accessDate) {
		super();
		this.ip = ip;
		this.accessDate = accessDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getAccessDate() {
		return accessDate;
	}

	public void setAccessDate(Date accessDate) {
		this.accessDate = accessDate;
	}

	public int compareTo(AccessLogWritable o) {
		int result = ip.compareTo(o.getIp());
		if(result == 0) {
			result = accessDate.compareTo(o.getAccessDate());
		}
		return result;
	}

	public void write(DataOutput out) throws IOException {
		out.writeUTF(ip);
		out.writeUTF(accessDate.toString());
	}

	public void readFields(DataInput in) throws IOException {
		ip = in.readUTF();
		accessDate = new Date(in.readUTF());
	}

	@Override
	public String toString() {
		return "AccessLogWritable [ip=" + ip + ", accessDate=" + accessDate + "]";
	}
}
