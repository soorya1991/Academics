import java.io.IOException;
//import java.util.*;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
//import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class HW1 {

        public static class Map extends Mapper<LongWritable, Text, Text, NullWritable> {
        	private static String input_zipcode; 
        	@Override
            public void setup(Context context) {
                    Configuration conf = context.getConfiguration();
                    input_zipcode = conf.get("input");		
            }
            
                @Override
                public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
                        String line = value.toString();
                        Text op = new Text();
                        String a[] = line.split("::");
                        if(a[4].equals(input_zipcode))
                        		{
                        		op.set(a[0]);
                        		context.write(op,NullWritable.get());
                        		}
                }
        }

        public static class Reduce extends Reducer<Text, NullWritable, Text, NullWritable> {
                @Override
                public void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException{
                        // write your code
                }
        }

        public static void main(String[] args) throws Exception {

                Configuration conf = new Configuration();
                conf.set("input", args[2]);
                Job job = new Job(conf, "zipcode");
                job.setJarByClass(HW1.class);
                job.setMapperClass(Map.class);
                //job.setReducerClass(Reduce.class);

                job.setOutputKeyClass(Text.class);
                job.setOutputValueClass(NullWritable.class);
				
                job.setInputFormatClass(TextInputFormat.class);
                job.setOutputFormatClass(TextOutputFormat.class);

                FileInputFormat.addInputPath(job, new Path(args[0]));
                FileOutputFormat.setOutputPath(job, new Path(args[1]));

                job.waitForCompletion(true);
        }

}

