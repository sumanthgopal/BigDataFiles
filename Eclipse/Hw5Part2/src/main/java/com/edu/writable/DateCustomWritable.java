package com.edu.writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class DateCustomWritable implements Writable {
	public String stockDate;
	public double stockVolume;
	public double stockAdjPrice;
	
	public DateCustomWritable() {
		super();
	}

	public DateCustomWritable(String stockDate, double stockVolume, double stockAdjPrice) {
		this.stockDate = stockDate;
		this.stockVolume = stockVolume;
		this.stockAdjPrice = stockAdjPrice;
	}

	public String getStockDate() {
		return stockDate;
	}

	public void setStockDate(String stockDate) {
		this.stockDate = stockDate;
	}

	public double getStockVolume() {
		return stockVolume;
	}

	public void setStockVolume(double stockVolume) {
		this.stockVolume = stockVolume;
	}

	public double getStockAdjPrice() {
		return stockAdjPrice;
	}

	public void setStockAdjPrice(double stockAdjPrice) {
		this.stockAdjPrice = stockAdjPrice;
	}

	public void write(DataOutput out) throws IOException {
		out.writeUTF(stockDate);
		out.writeDouble(stockVolume);
		out.writeDouble(stockAdjPrice);
	}

	public void readFields(DataInput in) throws IOException {
		stockDate = in.readUTF();
		stockVolume = in.readDouble();
		stockAdjPrice = in.readDouble();
	}
}
