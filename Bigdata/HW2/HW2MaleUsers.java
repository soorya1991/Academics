
import java.io.IOException;
import java.util.*;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.*;
import java.net.URI;

import org.apache.hadoop.filecache.DistributedCache;

public class HW2MaleUsers {
	
	public static class Map extends Mapper<LongWritable, Text, Text, IntWritable> {	
	 
		private HashMap<String,String> MovieDetails = new HashMap<String,String>();
		private static String input_movieid;
		 
		public void setup(Context context) throws IOException, InterruptedException	{
			Configuration conf = context.getConfiguration();
			input_movieid = conf.get("input").toString();
			input_movieid = input_movieid.trim();
			Path[] files = DistributedCache.getLocalCacheFiles(context.getConfiguration());
			File myFile = new File(files[0].getName());
			for(Path p : files){							
				BufferedReader br = new BufferedReader(new FileReader(myFile));
				String str = null;
				while((str = br.readLine())!=null){
					String[] line = str.split("::");
						if(line[1].equals("M"))
						{
							MovieDetails.put(line[0].trim(), line[1].trim());	
						} 
				}
				br.close();		
			}
		}
			 
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String line = value.toString();
            String a[] = line.split("::");
            if(a[1].equals(input_movieid) && MovieDetails.containsKey(a[0]))
            		{
            		context.write(new Text(input_movieid),new IntWritable(1));
            		}
		}
	}
	public static class Reduce extends Reducer<Text, IntWritable, Text, IntWritable> {
		public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			int count = 0;
			for(IntWritable val : values)
			{
				count++;
			}
			context.write(new Text("No of Male users"), new IntWritable(count));
		}
	}	 
		        
	public static void main(String[] args) throws Exception {			 
			 Configuration conf = new Configuration();
	         //argument validation
	         if (args.length != 4){
	           System.err.println("Usage: InMemoryJoin <input: movies.dat> <input: ratings.dat> <temp> <out>");
	           System.exit(2);
	         }
			 
	         conf.set("input", args[3]);
	         try{
			 DistributedCache.addCacheFile(new URI(args[0]),conf);
	         }
	         catch(Exception e)
	         {
	        	 System.out.println(e);
	         }
			 
			 Job countJob = new Job(conf, "InMemoryJoin");	        
			 countJob.setJarByClass(HW2MaleUsers.class);
			 countJob.setMapperClass(Map.class);	        
			 countJob.setReducerClass(Reduce.class);         
			 countJob.setOutputKeyClass(Text.class);
			 countJob.setOutputValueClass(IntWritable.class);
			 countJob.setInputFormatClass(TextInputFormat.class);
             countJob.setOutputFormatClass(TextOutputFormat.class);

             FileInputFormat.addInputPath(countJob, new Path(args[1]));
             FileOutputFormat.setOutputPath(countJob, new Path(args[2]));

			 countJob.waitForCompletion(true);
	         
		 }

}