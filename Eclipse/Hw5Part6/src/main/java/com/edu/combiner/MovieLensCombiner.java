package com.edu.combiner;

import java.io.IOException;
import java.util.Map.Entry;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SortedMapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Reducer;

public class MovieLensCombiner extends Reducer<IntWritable, SortedMapWritable, IntWritable, SortedMapWritable> {
    
    SortedMapWritable comOutput = new SortedMapWritable();
    
    public void reduce(IntWritable key,Iterable<SortedMapWritable> values,Context context) throws IOException, InterruptedException {
    	System.out.println("Combiner");
        for(SortedMapWritable value :values){
            
            for(Entry<WritableComparable, Writable> entry : value.entrySet()) {
                
                IntWritable count = (IntWritable) comOutput.get(entry.getKey());
                
                if(count != null) {
                    count.set(count.get() + ((IntWritable) entry.getValue()).get());
                } else {
                   comOutput.put(entry.getKey(), new IntWritable(((IntWritable) entry.getValue()).get()));
                }
            }
            value.clear();
        }
        context.write(key, comOutput);
        System.err.println("2..."+key.toString()+"..."+comOutput.keySet()+"..."+comOutput.values());
    }

}
