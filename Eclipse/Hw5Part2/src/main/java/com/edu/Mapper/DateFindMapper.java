package com.edu.Mapper;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.edu.writable.DateCustomWritable;

public class DateFindMapper extends Mapper<LongWritable, Text, Text, DateCustomWritable> {
	
	protected Text stockName;
	protected DateCustomWritable dateCustom;

	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, DateCustomWritable>.Context context) throws InterruptedException {
			String[] allValues = value.toString().split(",");
			stockName = new Text(allValues[1]);
			try {
				dateCustom = new DateCustomWritable(allValues[2],Double.parseDouble(allValues[7]),Double.parseDouble(allValues[8]));
			}catch(Exception e){
				System.out.println("Not a double value");
			}
			try {
				if(stockName!=null && dateCustom!=null) {
					context.write(stockName, dateCustom);
				}
			} catch (IOException e) {
				System.out.println("Error in writing context");
			}
	}

}
