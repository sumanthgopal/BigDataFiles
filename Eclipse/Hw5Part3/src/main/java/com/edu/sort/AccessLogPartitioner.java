package com.edu.sort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

import com.edu.writable.AccessLogWritable;

public class AccessLogPartitioner extends Partitioner<AccessLogWritable, IntWritable> {

	@Override
	public int getPartition(AccessLogWritable key, IntWritable value, int numPartitions) {
		int hash = key.getIp().hashCode();
		int res = hash % numPartitions;
		return res;
	}

}
