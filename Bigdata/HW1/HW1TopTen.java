
import java.io.IOException;

import java.util.*;
        
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
        
public class HW1TopTen {
        
 public static class Map extends Mapper<LongWritable, Text, Text, FloatWritable> {
    private final static FloatWritable r = new FloatWritable();
    private Text word = new Text();
        
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
    	String[] temp;
    	String movieid;
    	int rating;
        String line1 = value.toString();
        temp = line1.split("::");
        if(temp.length ==4)
        {
        movieid = temp[1];
        rating = Integer.parseInt(temp[2]);
        r.set(rating); 
        word.set(movieid);
        context.write(word, r);
        }
    }
 } 
        
 public static class Reduce extends Reducer<Text, FloatWritable, Text, FloatWritable> {

    public void reduce(Text key, Iterable<FloatWritable> values, Context context) 
      throws IOException, InterruptedException {
        float sum = 0;
        int count =0;
        for (FloatWritable val : values) {
            sum += val.get();
            count++;
        }
        sum = sum/count;
        context.write(key, new FloatWritable(sum));
    }
 }
 
 public static class MapTop extends Mapper<LongWritable, Text, NullWritable, Text> {
	    //private final static FloatWritable r = new FloatWritable();
	    //private Text word = new Text();
	        
	    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
	    	//String[] temp;
	    	//String movieid;
	    	//String rating;
	        String line1 = value.toString().trim();
	        //temp = line1.split("\\t");
	        //movieid = temp[0];
	        //rating = temp[temp.length -1];
	        //r.set((float) Double.parseDouble(rating)); 
	       	context.write(NullWritable.get(),new Text(line1));
	        
	    }
	 }
 public static class ReduceTop extends Reducer<NullWritable, Text, NullWritable, Text> {
	 
	  private TreeMap<Double, List<String>> topten = new TreeMap<Double, List<String>>();
	    public void reduce(NullWritable key, Iterable<Text> values, Context context)
	    		throws IOException, InterruptedException {
	    
	         //float sum = 0;
	        for (Text val : values) {
	            String[] top = val.toString().trim().split("\\t");
	            Double avg = Double.parseDouble(top[1].trim());
	            if(topten.containsKey(avg))
	            {	            
	            	List<String> s1 = topten.get(avg);
	            	s1.add(top[0]);	            	
	            }
	            else
	            {
	            	List<String> s2 = new ArrayList<String>();
	            	s2.add(top[0]);
	            	topten.put(avg, s2);
	            } 	           
	        }
	        Text temp = new Text();
	        int count = 1;
	        boolean reach = true;
	        NavigableMap<Double, List<String>> nmap=topten.descendingMap();
	        for(Double keyVal: nmap.keySet())
	        {
	        	for (String str : nmap.get(keyVal)) 
	        	{
	        		if(count <= 10)
		        	{
	        			//String tempstr = (keyVal.toString()).concat(" "+str);
	        			String tempstr = str.concat("     ").concat(keyVal.toString());
		        		context.write(NullWritable.get(), new Text(tempstr));
		        	
		        	}
	        		else
		        	{
	        			reach =false;
		        		break;
		        	}
		        	count = count+1;
		        	
	        	}
	        	if(reach == false)
	        	{
	        		break;
	        	}
	        	
	        }
	    }
	 }
        
 public static void main(String[] args) throws Exception {
	 
    Configuration conf = new Configuration();        
    Job job = new Job(conf, "TopTemp");
    
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(FloatWritable.class);
    job.setJarByClass(HW1TopTen.class);
    job.setMapperClass(Map.class);
    job.setCombinerClass(Reduce.class);
    job.setReducerClass(Reduce.class);
        
    job.setInputFormatClass(TextInputFormat.class);
    job.setOutputFormatClass(TextOutputFormat.class);
        
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
    job.waitForCompletion(true);
        
    Job job1 = new Job(conf, "TopAvg");
    
    job1.setOutputKeyClass(NullWritable.class);
    job1.setOutputValueClass(Text.class);
    job1.setJarByClass(HW1TopTen.class);
    job1.setMapperClass(MapTop.class);
    //job1.setCombinerClass(ReduceTop.class);
    job1.setReducerClass(ReduceTop.class);
        
    job1.setInputFormatClass(TextInputFormat.class);
    job1.setOutputFormatClass(TextOutputFormat.class);
        
    FileInputFormat.addInputPath(job1, new Path(args[1]));
    FileOutputFormat.setOutputPath(job1, new Path(args[2]));
        
    job1.waitForCompletion(true);
 }
        
}
