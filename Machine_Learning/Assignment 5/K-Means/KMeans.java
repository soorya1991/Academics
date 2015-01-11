
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.util.*;	 

	public class KMeans {
	    public static void main(String [] args){
		if (args.length < 3){
		    System.out.println("Usage: Kmeans <input-image> <k> <output-image>");
		    return;
		}
		try{
		    BufferedImage originalImage = ImageIO.read(new File(args[0]));
		    int k=Integer.parseInt(args[1]);
		    BufferedImage kmeansJpg = kmeans_helper(originalImage,k);
		    ImageIO.write(kmeansJpg, "jpg", new File(args[2])); 
		    
		}catch(IOException e){
		    System.out.println(e.getMessage());
		}	
	    }
	    
	  public static BufferedImage kmeans_helper(BufferedImage originalImage, int k){
		int w=originalImage.getWidth();
		int h=originalImage.getHeight();
		BufferedImage kmeansImage = new BufferedImage(w,h,originalImage.getType());
		Graphics2D g = kmeansImage.createGraphics();
		g.drawImage(originalImage, 0, 0, w,h, null);
		// Read rgb values from the image
		int[] rgb=new int[w*h];
		int count=0;
		for(int i=0;i<w;i++){
		    for(int j=0;j<h;j++){
			rgb[count++]=kmeansImage.getRGB(i,j);
		    }
		}
		// Call kmeans algorithm: update the rgb values
		kmeans(rgb,k,w,h);

		// Write the new rgb values to the image
		count=0;
		for(int i=0;i<w;i++){
		    for(int j=0;j<h;j++){
			kmeansImage.setRGB(i,j,rgb[count++]);
		    }
		}
		return kmeansImage;
	    }
	    
	    
	    // Your k-means code goes here
	    // Update the array rgb by assigning each entry in the rgb array to its cluster center
	    public static void kmeans(int[] rgb, int k,int w,int h){
	    	int l=0,minpos=0,group=0,x,y,z,avgred,avggreen,avgblue;
	    	double x1,y1,distance,dis1,dis2,min=0;
	    	Random random = new Random();
	    	class1[] obj1 = new class1[k];
	    	ArrayList<Integer> newlist = new ArrayList<Integer>();
		    while(l<k)
		    {
		    	int position = random.nextInt(rgb.length);
		    	obj1[l] = new class1();
	    		obj1[l].position = position;
	    		obj1[l].rgb1 = rgb[position];
		    	l++;
		    }
		    
		    l=0;
		    int count = 0;
		    
			   
		    while(count<20) {
			    	for(int n=0;n<k;n++)
			    	{
			    		obj1[n].klist = new ArrayList<Integer>();
			    	}
			   for(int i=0; i<rgb.length; i++)
			   {
				   int red = (rgb[i]>>16)&0xFF;
				   int green=(rgb[i]>>8) &0xFF;
				   int blue= (rgb[i]) &0xFF;
			    	
			    	
			    	min=Integer.MAX_VALUE;
			    	
			    
			    	for(int j=0; j<k; j++)
			    	{
			    		int kred = (obj1[j].rgb1>>16)&0xFF;
			    		int kgreen=(obj1[j].rgb1>>8) &0xFF;
						int kblue= (obj1[j].rgb1) &0xFF;
						
						x=red-kred;
						y=green-kgreen;
						z=blue-kblue;
						distance=0;
			    		distance=Math.sqrt(x*x+y*y+z*z);
			    		if(distance<min)
			    		{
			    			min=distance;
			    			minpos=obj1[j].rgb1;
			    			group=j;
			    			obj1[j].klist.add(i);
			    		}
			    	}	
			   }
			   for(int q=0;q<k;q++) {
				   avgred=0;
				   avggreen=0;
				   avgblue=0;
				   newlist=obj1[q].klist;
				   for(int f=0;f<newlist.size();f++)
					   {
					   int pos=newlist.get(f);
					   int newrgb= rgb[pos];
					    avgred += (newrgb>>16) &0xFF;
					    avggreen += (newrgb>>8) &0xFF;
					    avgblue +=  (newrgb) &0xff;
					   }
				   if(newlist.size()!=0)
				   {
				  avgred=avgred/newlist.size();
				  avggreen=avggreen/newlist.size();
				  avgblue=avgblue/newlist.size();
				  obj1[q].rgb1 |= avgred;
				   obj1[q].rgb1 |=avggreen<<8;
				   obj1[q].rgb1 |= avgblue<<16;
				   }
			   }
			   
			   count++;
		    }
		    for(int i=0;i<k;i++)
		    {
		    	 newlist=obj1[i].klist;
		    	 
		    	 for(int f=0;f<newlist.size();f++)
		    	 {
		    		 int pos=newlist.get(f);
		    		 rgb[pos]=obj1[i].rgb1;
		    	 }
		    }
	    }
	}

