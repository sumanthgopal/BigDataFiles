package com.edu.mapper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.edu.writable.AccessLogWritable;

public class AccessLogSortMapper extends Mapper<LongWritable, Text, AccessLogWritable, NullWritable> {

	//private static final IntWritable one = new IntWritable(1);
	private static AccessLogWritable val;

	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, AccessLogWritable, NullWritable>.Context context)
			throws IOException, InterruptedException {
		String[] accessValues = value.toString().split("\\s");
		if(accessValues[0] != null && accessValues[3] != null) {
			String dateString = accessValues[3].replaceAll("[\\[]", "");
			DateFormat df = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss");
			Date dateOp = null;
			try {
				dateOp = df.parse(dateString);
				if(dateOp!=null) {
					//System.out.println("one "+accessValues[0]+" two "+dateOp);
					val = new AccessLogWritable(accessValues[0], dateOp);
					context.write(val, NullWritable.get());
				}
			} catch (ParseException e) {
				System.out.println("Error while converting Date "+e);
			} 
		}
	}

}
