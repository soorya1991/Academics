/*
 * ClientWorker class is responsible for executing, updating and maintaining routing tables,
 * and all other distance vector protocol data structures.
 * 
 * We also do our distance vector update work in this class when a new node joins. 
 * 
 * Each m0 nodes listening for TCP connections and every node joining the network will run
 * many instance of this ClientWorker. The exact number of instance indicates the degree
 * of the node. For example if a node has 2 outgoing connections, there will be 2 instances
 * of ClientWorker running. 
 * 
 * The data structures are made static so that one node will have exactly one routing table.
 * All the instance can make changes to this table.
 * 
 * Classes calling ClientWorker: JoinNetwork and TCPManager and ProgMain
 * 
 * JoinNetwork creates an instance of this class when a new node is done selecting m no of connections
 * 
 * TCPManager creates an instance of this class when a node connects to a node listening for a TCP connection
 * 
 * ProgMain creates m0-1 instances of this class when the base m0 nodes are being initialized 
 * 
 */

import java.util.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.net.InetAddress;
import java.io.*;

public class ClientWorker implements Runnable {
	Socket client;
	public final int joinPort=9198;
	private static ConcurrentHashMap<String, Integer> myNeigh = new ConcurrentHashMap<>();;
	//my routing table hashmap<hostname,next hop>
	private static ConcurrentHashMap<String,String> myRoutingTable = new ConcurrentHashMap<String,String>();
	//my routing table distance hashmap<hostname,distance to the host>
	private static ConcurrentHashMap<String,Integer> myRoutingDist= new ConcurrentHashMap<String,Integer>();
	//data structure to hold degree information calculated hashmap<hostname,degree of that host>
	private static ConcurrentHashMap<String,Integer> myDegreeInfo =  new ConcurrentHashMap<String,Integer>();
	//Hostname and port numbers of all connected hosts
	private static ConcurrentHashMap<String, Integer> HostConnDetails;
	boolean flag=true;
	private int count=1;
	private static int noOfConnections;
	private static PrintWriter out = null;
    private int degreeinfo;
	private Socket clientSocket;
	private ServerSocket serverSocket;
	ClientWorker(Socket myClient)
	{
		this.client=myClient;
		noOfConnections++;
		myNeigh.put(myClient.getInetAddress().getHostName().toString(), myClient.getPort());
	}
	private static Set<String> keyset = new HashSet<String>();
	private static Set<String> distanceset = new HashSet<String>();
	private static Set<String> degreeset = new HashSet<String>();
	@Override
	public void run() {
		keyset=myNeigh.keySet();
		while(flag)
		{

			//System.out.println(keyset.size());
			if(count<=keyset.size())
			{
			try {
				doDistVectWork();
				count++;

				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			else
			{
				continue;
			}
			
		}
		
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void doDistVectWork() throws IOException
	{
		System.out.println("Start Distance vector");
		degreeinfo=0;
		InetAddress ip;
		ip = InetAddress.getLocalHost();
		String hostname=InetAddress.getLocalHost().getHostName();
		keyset=myNeigh.keySet();	
		degreeinfo=noOfConnections;
		myDegreeInfo.put(hostname,degreeinfo);
		
		for(String s : keyset)
			{
			myRoutingTable.put(s,s);	
			myRoutingDist.put(s,1);
			myDegreeInfo.put(s,1);
			}
		myRoutingTable.put(hostname,hostname);
		myRoutingDist.put(hostname,0);
		distanceset=myRoutingDist.keySet();
		String result=hostname + ":";
			for(String s : distanceset)
				{
					int value;
					value=myRoutingDist.get(s);
					result=result + s + "-" + value + ":";
				}
			result=result + "!"+ degreeinfo;
		for(String s : keyset)
		{
			try {
				//clientSocket =   new Socket(s, joinPort);
				//PrintWriter out = new PrintWriter(client.getOutputStream(), true);
				DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
				System.out.println("Sending Data " + result);
				//out.write(result);
				result=result+"\n";
				outToServer.writeBytes(result);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
		outToServer.writeBytes("terminate"+"\n");
	//InputStream is = client.getInputStream();
	
	
	
	
	
	BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
	
	StringBuilder sb = new StringBuilder();

	String line;
	
	while((line=in.readLine())!=null)
	{
        if (line.indexOf("terminate") != -1) {
            break;
        }
		
		sb.append(line + "\n");
	}

	
	String recvdData;
	recvdData=sb.toString();
	System.out.println("Receiver Data " + recvdData);
			
			String[] routeinfo,newdata;
			newdata=recvdData.split("\n");
			for(int j=0;j<newdata.length;j++)
			{
				recvdData=newdata[j];
				String key,ipaddr;
				String actualvalue;
				int incomingvalue,actualdistance;
				String[] routeinfo1,routeinfo2;
				String[] deg;
				routeinfo=recvdData.split(":");
				ipaddr=routeinfo[0];
				int size=routeinfo.length-1;
				int newdegreeinfo;
				deg=routeinfo[size].split("!");
				newdegreeinfo=Integer.parseInt(deg[1].trim());
				for(int i=1;i<size;i++)
				{
					routeinfo2=routeinfo[i].split("-");
					System.out.println(routeinfo2[1]);
					incomingvalue=Integer.parseInt(routeinfo2[1]);
					if(myRoutingDist.get(routeinfo2[0])==null)
					{
						int newdis;
						System.out.println(routeinfo2[0]);
						newdis=myRoutingDist.get(ipaddr);
						
						myRoutingDist.put(routeinfo2[0], newdis+1);
						//myDegreeInfo.put(routeinfo[0], newdegreeinfo);
						myRoutingTable.put(routeinfo2[0],ipaddr);
					}
					if(myDegreeInfo.get(routeinfo2[0])==null)
					{
						System.out.println("NEWdEGREEINFO" + newdegreeinfo);
						myDegreeInfo.put(ipaddr,newdegreeinfo);
					}
					actualdistance=myRoutingDist.get(routeinfo2[0]);
					
					if((incomingvalue+1)<actualdistance)
					{
						myRoutingDist.put(routeinfo2[0],incomingvalue+1);
						myRoutingTable.put(routeinfo2[0], clientSocket.getInetAddress().getHostName());				}
				}
		myDegreeInfo.put(ipaddr,newdegreeinfo);
			}
			
		
			/*for(String s : keyset)
			{
				System.out.println(s);
			System.out.println(myRoutingTable.get(s));	
			System.out.println(myRoutingDist.get(s));
			}
			degreeset=myDegreeInfo.keySet();
			for(String s : degreeset)
			{
				System.out.println(s);
				System.out.println(myDegreeInfo.get(s));
			}*/
	}
	public static ConcurrentHashMap<String,Integer> getDegreeInfo()
	{
		return myDegreeInfo;
	}
	
	public static ConcurrentHashMap<String, Integer> getHostConnDetails()
	{
		return HostConnDetails;
	}
	
	public static ConcurrentHashMap<String, String> getRoutingTable()
	{
		return myRoutingTable;
	}
	public static ConcurrentHashMap<String, Integer> getRoutingDistTable()
	{
		return myRoutingDist;
	}
}
