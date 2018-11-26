package com.edu.mapper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class AverageNyseMapper extends Mapper<LongWritable,Text,IntWritable,DoubleWritable>{

	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, IntWritable,DoubleWritable>.Context context)
			throws IOException, InterruptedException {
		String values[] = value.toString().split(",");
		if(values[2] != null && values[8]!= null) {
			DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
			//DateFormat df = new SimpleDateFormat("mm/dd/yy");
			Date dateOp = null;
			try {
				dateOp = df.parse(values[2]);
				int year = dateOp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
					double stock = Double.parseDouble(values[8]);
					context.write(new IntWritable(year),new DoubleWritable(stock));
			} catch (ParseException e) {
				System.out.println("Error while converting Date and Parsing mapper "+e);
			} 
		}
	}

	
}
