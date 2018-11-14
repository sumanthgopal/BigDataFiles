package com.edu.reducer;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.edu.writable.DateCustomWritable;

public class DateFindReducer extends Reducer<Text, DateCustomWritable, Text, Text> {

	@Override
	protected void reduce(Text keyIn, Iterable<DateCustomWritable> valueIn,
			Reducer<Text, DateCustomWritable, Text, Text>.Context context) throws IOException, InterruptedException {
		DateCustomWritable initialvalue = valueIn.iterator().next();
		double maxVolume = initialvalue.getStockVolume();
		String dateMax = initialvalue.getStockDate();
		String dateMin = initialvalue.getStockDate();
		double minVolume = initialvalue.getStockVolume();
		double maxStockAdj = initialvalue.getStockAdjPrice();
		for (DateCustomWritable d : valueIn) {
			if (d.getStockVolume() > maxVolume) {
				maxVolume = d.getStockVolume();
				dateMax = d.getStockDate();
			}
			if (d.getStockVolume() < minVolume) {
				minVolume = d.getStockVolume();
				dateMin = d.getStockDate();
			}
			if (d.getStockAdjPrice() > maxStockAdj) {
				maxStockAdj = d.getStockAdjPrice();
			}
		}
		Text op = new Text("date of the max stock_volume: "+dateMax+ " date of the min stock_volume: "
				+ ""+dateMin+ " max stock_price_adj_close: "+maxStockAdj);
		context.write(keyIn,op);
	}

}
