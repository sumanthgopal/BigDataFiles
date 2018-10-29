import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class KeyValueTextIFReducer extends Reducer<Text,Text,Text,Text>{

	@Override
	protected void reduce(Text key, Iterable<Text> values,
			Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
		
		String name = "";
		for (Text val : values) {
		    name = "Value = "+val.toString();
		  } 
		//context.write(key, new Text(name)); 
		  context.write(new Text("Key = "+key.toString()), new Text(name));  
	}
}
