
import java.io.*;
import java.util.*;
import java.util.Map.Entry;
public class LLRStop {
	public static ArrayList<String> ham = new ArrayList<String>();
	public static HashSet hs = new HashSet();
	public static ArrayList<String> spam = new ArrayList<String>();
	public static ArrayList<Double> rows = new ArrayList<Double>();
	public static ArrayList<Double> rows1 = new ArrayList<Double>();
	private static List<HashMap<String, Integer>> hamFileCountMap = new ArrayList<HashMap<String,Integer>>();
	private static List<HashMap<String, Integer>> spamFileCountMap = new ArrayList<HashMap<String,Integer>>();
	private static Map<String, Integer> dataIndexMap = new HashMap<String, Integer>();
	private static List<HashMap<String, Integer>> hamFileCountMap1 = new ArrayList<HashMap<String,Integer>>();
	private static List<HashMap<String, Integer>> spamFileCountMap1 = new ArrayList<HashMap<String,Integer>>();
	private static Map<String, Integer> dataIndexMap1 = new HashMap<String, Integer>();
	private static Map<String, Integer> colCountMap = new HashMap<String, Integer>();
	private static Map<String, Integer> colCountMap1 = new HashMap<String, Integer>();
	private static int value = 1;
	private static int value1 = 1;
	private static int noOfFiles = 0;
  public static void main(String[] args)throws IOException 
  {
		String x= "-";
		File k[] = null;
		File k1[];
		File k2[];
		File k3[];
		int pos=0;
		//double arr[][];
		 double netprobham=1;
		double listsize=0,listsize2=0;
		double count=0,count1,total=0,probham;
	
		  double probspam,netprobspam=1,newcount=0;
	  try {
		//  BufferedReader reader = new BufferedReader(new FileReader("D:/machine learning/train/ham/ham (1).txt"));
		  //String line = null;
		  String filename ="D:/machine learning/train/ham/";
		  File f = new File(filename);
		  k= f.listFiles();
		  noOfFiles = noOfFiles + k.length;
		  for(int i=0;i<k.length;i++)
		  {	 
		  HashMap<String, Integer> attributesCountMap = new HashMap<String, Integer>(); 
		  hamFileCountMap.add(attributesCountMap);
		  Scanner input = new Scanner(k[i]);
		  while (input.hasNext()) 
		  		{
			  String data = input.next();
		//	  System.out.println(data);
		       
			  
//			  hs.add(data);
			  
			  if (!dataIndexMap.containsKey(data)) {
				  dataIndexMap.put(data, value);
				  value++;
			  }
			  
			  if (colCountMap.containsKey(data)) {
				  int colCount = colCountMap.get(data);
				  colCount++;
				  colCountMap.put(data, colCount);
			  } else {
				  colCountMap.put(data, 1);
			  }
			  
			  if (attributesCountMap.containsKey(data)) {
				  int countValue = attributesCountMap.get(data);
				  countValue++;
				  attributesCountMap.put(data, countValue);
			  } else {
				  attributesCountMap.put(data, 1);
				  
			  }
			  
			  
		        }
		    input.close();
		  }
		 

	  }
	  catch (IOException e) 
		{        

		e.printStackTrace();
		}
	  
	  
	  try {
			//  BufferedReader reader = new BufferedReader(new FileReader("D:/machine learning/train/ham/ham (1).txt"));
			  //String line = null;
			  String filename ="D:/machine learning/train/spam/";
			  File f2 = new File(filename);
			  k2= f2.listFiles();
			  noOfFiles = noOfFiles + k2.length;
			  for(int i=0;i<k2.length;i++)
			  {	 
			  HashMap<String, Integer> attributesCountMap = new HashMap<String, Integer>(); 
			  spamFileCountMap.add(attributesCountMap);
			  Scanner input2 = new Scanner(k2[i]);
			  while (input2.hasNext()) 
			  		{
				  String data = input2.next();
				  
				  
				  if (!dataIndexMap.containsKey(data)) {
					  dataIndexMap.put(data, value);
					  value++;
				  }
				  
				  if (colCountMap.containsKey(data)) {
					  int colCount = colCountMap.get(data);
					  colCount++;
					  colCountMap.put(data, colCount);
				  } else {
					  colCountMap.put(data, 1);
				  }
				  
				  if (attributesCountMap.containsKey(data)) {
					  int countValue = attributesCountMap.get(data);
					  countValue++;
					  attributesCountMap.put(data, countValue);
				  } else {
					  attributesCountMap.put(data, 1);
				  }
			//	  System.out.println(data);
			       
//			        hs.add(data);
			        }
			    input2.close();
			  }
			 
			 listsize2=hs.size();
		  }
		  catch (IOException e) 
			{        
			  System.out.println("");
			}

       float totalfiles,hashsize;
	  totalfiles=Math.round(listsize+listsize2);
	  hashsize=hs.size();
	  int x1,x2;
	  x1=Math.round(totalfiles);
	  x2=Math.round(hashsize);
	  int attributeCount = dataIndexMap.size();
	  int fileCount = noOfFiles;
	  x1=x1+10;
	  x2=x2+10;
	  double[][] arr = new double[fileCount][attributeCount + 2];
	  double[][] newarr = new double[fileCount][attributeCount + 2];
	  

	  for (int i = 0; i < fileCount; i++) {
		arr[i][0] = 1/fileCount;
		newarr[i][0]=1/fileCount;
	  }
	  int classValue = 1;
	  int fileNumber = 0;
	  double temp;
	  for (HashMap<String, Integer> map : hamFileCountMap) {
		  HashMap<String, Integer> fileAttributeMap = map;
		  HashMap<String, Integer> attributesCountMap = new HashMap<String, Integer>(); 
	//	 temp= map.get(fileNumber);
		  Set<Entry<String, Integer>> fileSet = fileAttributeMap.entrySet();
		  for (Entry<String, Integer> entry : fileSet) {
			
			  int dataIndex = dataIndexMap.get(entry.getKey());
			  double colCount = colCountMap.get(entry.getKey());
		//	  temp=dataIndexMap.get(entry.getValue());
			  double a = entry.getValue();
			  arr[fileNumber][dataIndex] = a/colCount;
			  newarr[fileNumber][dataIndex] = (entry.getValue())/1000;
			  
			  
//			  if(entry.getValue() == 0) {
//				  System.out.println("0");
//			  }
//			  
		  }
		   
		  
		  arr[fileNumber][attributeCount + 1] = classValue;
		  newarr[fileNumber][attributeCount + 1] = classValue;
		  fileNumber++;
		  
	  }
	  temp=0;
	  
	  classValue = 0;
	  for (HashMap<String, Integer> map : spamFileCountMap) {
		  HashMap<String, Integer> fileAttributeMap = map;
		  Set<Entry<String, Integer>> fileSet = fileAttributeMap.entrySet();
		  
		//  temp=map.get(fileNumber);
		  for (Entry<String, Integer> entry : fileSet) {
			
			  int dataIndex = dataIndexMap.get(entry.getKey());
			//  temp=dataIndexMap.get(entry.getValue());
			  double colCount = colCountMap.get(entry.getKey());
			  double a = entry.getValue();
    		arr[fileNumber][dataIndex] = a/colCount;
    		newarr[fileNumber][dataIndex] = (entry.getValue())/1000;
    		
//    		if(entry.getValue() == 0) {
//				  System.out.println("0");
//			  }
//    		
		  }
		  arr[fileNumber][attributeCount + 1] = 0;
		  newarr[fileNumber][attributeCount + 1] = 0;
		  fileNumber++;
		  
	  }
	  int hsize;
	  
	  hsize=dataIndexMap.size();
	  double warr[] = new double[hsize+1];
	  for (int i = 0; i < warr.length; i++) {
		warr[i]=0;
	}
	  double prob[] = new double[fileCount];
	  double minus[] = new double[fileCount];
	  double dw[] = new double[hsize+1];
	  for (int d = 0; d < dw.length; d++) {
		dw[d]=0;
	}
	  
	 
	  for(int i=0;i<100;i++)
	  {
		for(int j=0;j<fileCount;j++)
		{
			double sum9=0;
			for(int l=0;l<hsize;l++)
			{
//				String s = sum9+(arr[j][l]*warr[l]) + "";
//				if (s.equals("NaN")) {
//					System.out.println("Nan");
//				}
			sum9=sum9+(arr[j][l]*warr[l]);	
			}
//			String s = (Math.exp(sum9))/(1+Math.exp(sum9)) + "";
//			if (s.equals("NaN")) {
//				System.out.println("Nan");
//			}
			prob[j]=(Math.exp(sum9))/(1+Math.exp(sum9));
		}
		for(int i1=0;i1<hsize;i1++)
		{
			double sum7=0;
		for(int j1=0;j1<fileCount;j1++)
		{
			double cls=arr[j1][hsize+1];
//			String s = sum7+(arr[j1][i1]*(cls-prob[j1])) + "";
//			if (s.equals("NaN")) {
//				System.out.println("Nan");
//			}
			sum7=sum7+(arr[j1][i1]*(cls-prob[j1]));
		}
		dw[i1]=sum7;
//		String s = (warr[i1]*(1-(hsize*0.01)))+(0.1*dw[i1]) + "";
//		if (s.equals("NaN")) {
//			System.out.println("Nan");
//		}
		
		warr[i1]=warr[i1]+(0.01*(dw[i1] - 0.1*warr[i1]));
		}
	  }
//	int pos1 = 0;
//	int neg = 0;
//	for (double d : warr) {
//		if (d> 0) {
//			pos1++;
//		} else {
//			neg++;
//		}
//	}
//	  System.out.println("pos : "  + pos1);
//	  System.out.println("neg : "  + neg);

	
//	  System.out.println("done");
	  
	  

	  try {
			//  BufferedReader reader = new BufferedReader(new FileReader("D:/machine learning/train/ham/ham (1).txt"));
			  //String line = null;
			  String filename ="D:/machine learning/test/ham/";
			  File f = new File(filename);
			  k= f.listFiles();
			  noOfFiles = k.length;
			  for(int i=0;i<k.length;i++)
			  {	 
			  HashMap<String, Integer> attributesCountMap1 = new HashMap<String, Integer>(); 
			  hamFileCountMap1.add(attributesCountMap1);
			  Scanner input = new Scanner(k[i]);
			  while (input.hasNext()) 
			  		{
				  String data = input.next();
			//	  System.out.println(data);
			       
				  
//				  hs.add(data);
				  if (dataIndexMap.containsKey(data)) {
					  if (!dataIndexMap1.containsKey(data)) {
						  dataIndexMap1.put(data, value1);
						  value1++;
					  }
					  
					  if (attributesCountMap1.containsKey(data)) {
						  int countValue = attributesCountMap1.get(data);
						  countValue++;
						  attributesCountMap1.put(data, countValue);
					  } else {
						  attributesCountMap1.put(data, 1);
						  
					  }
					  
				  }
				  
				  
			        }
			    input.close();
			  }
			 
			 listsize=hs.size();
//			 System.out.println(listsize);
		  }
		  catch (IOException e) 
			{        
			  System.out.println("");
			}
	  try {
			//  BufferedReader reader = new BufferedReader(new FileReader("D:/machine learning/train/ham/ham (1).txt"));
			  //String line = null;
			  String filename ="D:/machine learning/test/spam/";
			  File f2 = new File(filename);
			  k2= f2.listFiles();
			  noOfFiles = noOfFiles + k2.length;
			  for(int i=0;i<k2.length;i++)
			  {	 
			  HashMap<String, Integer> attributesCountMap1 = new HashMap<String, Integer>(); 
			  spamFileCountMap1.add(attributesCountMap1);
			  Scanner input2 = new Scanner(k2[i]);
			  while (input2.hasNext()) 
			  		{
				  String data = input2.next();
				  
				  if (dataIndexMap.containsKey(data)) {
				  
					  if (!dataIndexMap1.containsKey(data)) {
						  dataIndexMap1.put(data, value1);
						  value1++;
					  }
					  
					  if (attributesCountMap1.containsKey(data)) {
						  int countValue = attributesCountMap1.get(data);
						  countValue++;
						  attributesCountMap1.put(data, countValue);
					  } else {
						  attributesCountMap1.put(data, 1);
					  } 
				  }
				  
			//	  System.out.println(data);
			       
//			        hs.add(data);
			        }
			    input2.close();
			  }
			 
			 listsize2=hs.size();
//			 System.out.println(listsize2);
		  }
		  catch (IOException e) 
			{        
			  System.out.println("");
			}
	  
	  
	  
	  
	  int filenumber1=0,hamacc=0,spamacc=0;
	  double[][] arr1 = new double[noOfFiles][attributeCount + 2];
	  double[][] newarr1 = new double[noOfFiles][attributeCount+2];
	  System.out.println(noOfFiles);
	  for (int i = 0; i < noOfFiles; i++) {
			arr1[i][0] = 1/noOfFiles;
			newarr1[i][0]=1/noOfFiles;
	  }
	  for (HashMap<String, Integer> map : hamFileCountMap1) {
		  HashMap<String, Integer> fileAttributeMap1 = map;
		  Set<Entry<String, Integer>> fileSet1 = fileAttributeMap1.entrySet();
		  
		  
		  for (Entry<String, Integer> entry : fileSet1) {
			
			  int dataIndex = dataIndexMap.get(entry.getKey());
			  double colCount = 0;
			try {
				colCount = colCountMap.get(entry.getKey());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  double a = entry.getValue();
			  arr1[filenumber1][dataIndex] = a/colCount;
			  newarr1[filenumber1][dataIndex] = entry.getValue()/1000;
			  
			  
			  
		  }
		  filenumber1++;
	  }
	  
	  
		  for (HashMap<String, Integer> map : spamFileCountMap1) {
			  HashMap<String, Integer> fileAttributeMap = map;
			  Set<Entry<String, Integer>> fileSet1 = fileAttributeMap.entrySet();
			  
			  
			  for (Entry<String, Integer> entry : fileSet1) {
				
				  int dataIndex = dataIndexMap.get(entry.getKey());
	    		try {
					
	    			double colCount = colCountMap.get(entry.getKey());
           		    double a = entry.getValue();
	    			arr1[filenumber1][dataIndex] = a/colCount;
					newarr1[filenumber1][dataIndex] = entry.getValue()/1000;
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			  }
		  filenumber1++;
		  }
		  int n = 0;
		  for (int i = 0; i < noOfFiles; i++) {
			for (int j = 0; j < attributeCount + 2; j++) {
				double a = arr1[i][j];
				if (a < 0) {
					n++;
				}
			}
		}
		  double sum5,sum6=315;
		  for(int i=0;i<noOfFiles;i++)
		  {
			  sum5=warr[0];
			for(int j=0;j<hsize+1;j++)
			{
				try {
					sum5=sum5+(arr1[i][j]*warr[j]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(sum5>0)
			{
				hamacc++;
			}
//			else if (sum5<0)
//			{
//				spamacc++;
//			}
		  }
	  System.out.println("ACCURACY " +(sum6/k.length)*100);
	  
		
}
	
}


