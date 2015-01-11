
import java.io.*;
import java.util.*;
public class naivestop {
	public static ArrayList<String> ham = new ArrayList<String>();
	public static HashSet hs1 = new HashSet();
	public static ArrayList<String> spam = new ArrayList<String>();
	public static HashSet hs2 = new HashSet();
	public static ArrayList<Double> rows = new ArrayList<Double>();
	public static ArrayList<Double> rows1 = new ArrayList<Double>();
	public static ArrayList<String> ram = new ArrayList<String>();
  public static void main(String[] args)throws IOException 
  {
		String x= "-";
		File k[];
		File k1[];
		File k2[];
		File k3[];
		int pos=0;
		 double netprobham=1;
		double listsize=0,listsize2=0;
		double count=0,count1,total=0,probham;
		double sum;
		  double probspam,netprobspam=1,newcount=0;
	  try {
		//  BufferedReader reader = new BufferedReader(new FileReader("D:/machine learning/train/ham/ham (1).txt"));
		  //String line = null;
		  String filename ="D:/machine learning/train/ham/";
		  File f = new File(filename);
		  k= f.listFiles();
		  for(int i=0;i<340;i++)
		  {	 
		  Scanner input = new Scanner(k[i]);
		  while (input.hasNext()) 
		  		{
			  String data = input.next();
		//	  System.out.println(data);
		       
		        ham.add(data);
		        }
		    input.close();
		  }
		 
		 listsize=ham.size();
	//	 System.out.println(listsize);
		 
	  }
	  catch (IOException e) 
		{        
		  System.out.println("");
		}
	  try
	  {
	  File b;
	  String flename1 ="D:/machine learning/rem1.txt";
	  File f = new File(flename1);
	  Scanner in = new Scanner(f);
		 while(in.hasNext())
		 {
			 String data = in.next();
			 ram.add(data);
			// System.out.println(data);
		 }
		for(String s:ram)
		{
		ham.removeAll(ram);	
		}
	  }
	  
	  catch(IOException o)
	  {
		System.out.println("NNNN");  
	  }
	  /*hs1.addAll(ham);
	  hs1.removeAll(ram);
	  ham.clear();
	  ham.addAll(hs1);*/
	
	  
	  try {
			//  BufferedReader reader = new BufferedReader(new FileReader("D:/machine learning/train/ham/ham (1).txt"));
			  //String line = null;
			  String filename ="D:/machine learning/train/spam/";
			  File f2 = new File(filename);
			  k2= f2.listFiles();
			  for(int i=0;i<123;i++)
			  {	 
			  Scanner input2 = new Scanner(k2[i]);
			  while (input2.hasNext()) 
			  		{
				  String data = input2.next();
			//	  System.out.println(data);
			       
			        spam.add(data);
			        }
			    input2.close();
			  }
			 
			 listsize2=spam.size();
			  for(String s:ram)
				{
				spam.removeAll(ram);	
				}
		//	 System.out.println(listsize2);
		  }
		  catch (IOException e) 
			{        
			  System.out.println("");
			}
	
	/*  hs2.addAll(spam);
	  hs2.removeAll(ram);
	  spam.clear();
	  spam.addAll(hs2);*/
	
	  
	  try {
			//  BufferedReader reader = new BufferedReader(new FileReader("D:/machine learning/train/ham/ham (1).txt"));
			  //String line = null;
		  sum=0;
		 
		  int a=0;
		  double accuracy,l=0;
			 String filename ="D:/machine learning/test/ham";
			 File f1 = new File(filename);
			  k1= f1.listFiles();
			  a=k1.length;
			//  System.out.println(a);
			  for(int i=0;i<a;i++)
			  {
				  netprobham=0;
				  netprobspam=0;
			  Scanner input1 = new Scanner(k1[i]);
			  while (input1.hasNext()) 
			  		{
				  count1=0;
				  String data = input1.next();
					  if(ham.contains(data))
					  {
						for(String s:ham)
						{
							if(s.equals(data))
							{
								count1++;
							}
						}
						 sum=(count1/listsize);
						 Scanner key = new Scanner(k1[i]);
						 while(key.hasNext())
						 {
							 String data1 = key.next();
							 if(data1.equals(data))
							 {
								 newcount++;
							 }
						 }
						 sum=sum*newcount++;
					  }
					  else
					  {
						  sum=(0.5);
					  }
					 
					//  System.out.print(ham.size()+ " ");
				//	 System.out.println(count1);
					 
			//	 System.out.println(" " + a);
					rows.add(Math.log(sum));
					
			  		}
		//	  System.out.print(rows);
			  total=listsize+listsize2;
			  probham=listsize/total;
			  for(double d:rows)
				{
					netprobham=netprobham+d;
				}
			  netprobham=netprobham+Math.log(probham);
			  rows.clear();
			  sum=0;
			  
			
			  
		//	  System.out.println("For Spam");
				 String filename1 ="D:/machine learning/test/ham";
					 File f3 = new File(filename1);
					 
					  k3= f3.listFiles();
					  Scanner input3 = new Scanner(k3[i]);
					  while (input3.hasNext()) 
					  		{
						  count1=0;
						  String data3 = input3.next();
							  if(spam.contains(data3))
							  {
								for(String s:spam)
								{
									if(s.equals(data3))
									{
										count1++;
									}
								}
								 Scanner key = new Scanner(k1[i]);
								 while(key.hasNext())
								 {
									 String data2 = key.next();
									 if(data2.equals(data3))
									 {
										 newcount++;
									 }
								 }
								
								 sum=(count1/spam.size());
								 sum=sum*newcount++;
							  }
							  else
							  {
								  sum=(0.5);
							  }
							//  System.out.print(spam.size()+ " ");
						//	  System.out.println(count1);
						//	  System.out.println(" ");
							rows1.add(Math.log(sum));
				  }
			//	  System.out.println(data);
					  probspam=listsize2/total;
					  for(double d:rows1)
						{
							netprobspam=netprobspam+d;
						}
					  netprobspam=netprobspam+Math.log(probspam);   
			       
			// System.out.println(rows1);
			//  System.out.println("Probability for ham + "+ netprobham);
		//	  System.out.println("Probability for spam + "+ netprobspam);
			//  System.out.println("Maximum");
			  if(netprobham>netprobspam)
			  {
				  l++;
			  }
			//  System.out.println(Math.max(netprobspam, netprobham)); 
			 rows1.clear();
		//	 listsize=ham.size();
			// System.out.println(listsize);
		  }
			  System.out.println(l);
			  accuracy=(l/a)*100;
			  System.out.println("A="+a);
			  System.out.println("Ham Accuracy = "+accuracy);
	  }
	  catch (IOException z) 
		{        
		  System.out.println("File not found");
		}
	  
	  
	  
	  try {
			//  BufferedReader reader = new BufferedReader(new FileReader("D:/machine learning/train/ham/ham (1).txt"));
			  //String line = null;
		  sum=0;
		 
		  int a=0;
		  double accuracy,l=0;
			 String filename ="D:/machine learning/test/spam";
			 File f1 = new File(filename);
			  k1= f1.listFiles();
			  a=k1.length;
			//  System.out.println(a);
			  for(int i=0;i<a;i++)
			  {
				  netprobham=0;
				  netprobspam=0;
			  Scanner input1 = new Scanner(k1[i]);
			  while (input1.hasNext()) 
			  		{
				  count1=0;
				  String data = input1.next();
					  if(ham.contains(data))
					  {
						for(String s:ham)
						{
							if(s.equals(data))
							{
								count1++;
							}
						}
						 sum=(count1/listsize);
						 Scanner key = new Scanner(k1[i]);
						 while(key.hasNext())
						 {
							 String data1 = key.next();
							 if(data1.equals(data))
							 {
								 newcount++;
							 }
						 }
						 sum=sum*newcount++;
					  }
					  else
					  {
						  sum=(0.5);
					  }
				//	  newcount=0;
					 
					//  System.out.print(ham.size()+ " ");
				//	 System.out.println(count1);
					 
			//	 System.out.println(" " + a);
					rows.add(Math.log(sum));
					
			  		}
		//	  System.out.print(rows);
			  total=listsize+listsize2;
			  probham=listsize/total;
			  for(double d:rows)
				{
					netprobham=netprobham+d;
				}
			  netprobham=netprobham+Math.log(probham);
			  rows.clear();
			  sum=0;
			  
			
			  
		//	  System.out.println("For Spam");
				 String filename1 ="D:/machine learning/test/spam";
					 File f3 = new File(filename1);
					 
					  k3= f3.listFiles();
					  Scanner input3 = new Scanner(k3[i]);
					  while (input3.hasNext()) 
					  		{
						  count1=0;
						  String data3 = input3.next();
							  if(spam.contains(data3))
							  {
								for(String s:spam)
								{
									if(s.equals(data3))
									{
										count1++;
									}
								}
								 Scanner key = new Scanner(k1[i]);
								 while(key.hasNext())
								 {
									 String data2 = key.next();
									 if(data2.equals(data3))
									 {
										 newcount++;
									 }
								 }
								
								 sum=(count1/spam.size());
								 sum=sum*newcount++;
							  }
							  else
							  {
								  sum=(0.5);
							  }
					//		  newcount=0;
							//  System.out.print(spam.size()+ " ");
						//	  System.out.println(count1);
						//	  System.out.println(" ");
							rows1.add(Math.log(sum));
				  }
			//	  System.out.println(data);
					  probspam=listsize2/total;
					  for(double d:rows1)
						{
							netprobspam=netprobspam+d;
						}
					  netprobspam=netprobspam+Math.log(probspam);   
			       
			// System.out.println(rows1);
			//  System.out.println("Probability for ham + "+ netprobham);
		//	  System.out.println("Probability for spam + "+ netprobspam);
			//  System.out.println("Maximum");
			  if(netprobspam>netprobham)
			  {
				  l++;
			  }
			//  System.out.println(Math.max(netprobspam, netprobham)); 
			 rows1.clear();
		//	 listsize=ham.size();
			// System.out.println(listsize);
		  }
			  System.out.println(l);
			  accuracy=(l/a)*100;
			  System.out.println("A="+a);
			  System.out.println("spam Accuracy = "+accuracy);
	  }
	  catch (IOException q) 
		{        
		  System.out.println("File not found");
		}
		}
  
}


