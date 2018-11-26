package lab4;

import java.io.IOException;
import java.util.Map.Entry;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SortedMapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Reducer;
 
public class Lab4Combiner extends Reducer<Text, SortedMapWritable, Text, SortedMapWritable> {
    
    SortedMapWritable comOutput = new SortedMapWritable();
    
    public void reduce(Text key,Iterable<SortedMapWritable> values,Context context) throws IOException, InterruptedException {
        
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