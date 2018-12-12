import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.TableName;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;


public class HbaseMain {

	public static void main(String[] args) throws IOException {
		Configuration  conf = HBaseConfiguration.create();
		HBaseAdmin admin = new HBaseAdmin(conf);
		HTableDescriptor desc = new HTableDescriptor(TableName.valueOf("test"));
	}

}
