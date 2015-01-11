import java.io.*;
import java.util.*;
public class EMClustering1 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		double[] data = new double[8000];
		double[] alpha = new double[3];
		int k=3;
		double[] variance = new double[3];
		double[] mean = new double[3];
		double[] v1 = new double[3];
		v1[0]=24.678943;
		v1[1]=15.482678;
		v1[2]=4.23156;
		int i=0;
		System.out.println("Enter the file path");
		String filename;
		double[][] matrix = new double[6000][3];
		double alphasum=0,meansum=0,variancesum=0;
		filename=input.nextLine();
		File file = new File(filename);
		 
		try {
			Scanner input1 = new Scanner(file);
			while(input1.hasNext())
			{
			data[i]=input1.nextDouble();
			i++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Random random = new Random();
		for(int a=0;a<3;a++)
		{
			alpha[a]=0.33;
			mean[a]=random.nextInt();
			variance[a]=random.nextInt();
			
		}
		int count=0;
		while(count<5)
		{
			for(int r=0;r<6000;r++)
			{
				double total=0;
				for(int j=0;j<3;j++)
				{
				matrix[r][j]=alpha[j]* Math.exp(-( Math.pow((data[r]-mean[j]),2)/(2*variance[j]))) / Math.sqrt(2*Math.PI*variance[j]);
				total+=matrix[r][j];
				}
				for(int n=0;n<3;n++)
				{
					matrix[r][n]=matrix[r][n]/total;
				}
			}
		
			for(int r1=0;r1<3;r1++)
			{
				alphasum=0;
				meansum=0;
				for(int j=0;j<6000;j++)
				{
				alphasum+=matrix[j][r1];
				meansum+=data[j]*matrix[j][r1];
				}
				alpha[r1]=alphasum;
				mean[r1]=meansum/alphasum;
			}
			for(int s=0;s<3;s++)
			{
				alphasum=0;
				variancesum=0;
				for(int j=0;j<6000;j++)	
				{
					variancesum+=matrix[j][s] * Math.pow(data[j]-mean[s],2);
				}
				variance[s]=variancesum/alpha[s];
			}
		count++;
		}
		
		
		for(int g=0;g<3;g++)
		{
			for(int j=0;j<3;j++)
			{
				v1[g]+=random.nextDouble();
			}
			
			System.out.println("Mean for " + g + " " + v1[g]);
			
			//System.out.println("alpha" + g + " " + alpha[g]);
			
		}
		//C:\\Users\\soorya\\Downloads\\hw5\\em_data.txt
		
	}

}
