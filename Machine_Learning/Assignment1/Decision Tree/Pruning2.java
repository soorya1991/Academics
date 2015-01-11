import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
public class Pruning2 {
	
	
	private static List<Instance> instanceList = new ArrayList<Instance>();
	private static List<Instance> validationInstanceList = new ArrayList<Instance>();
	private static int validationCount = 0;
	private static List<String> columnList = getColumnList();
	private static List<String> nodeList = new ArrayList<String>();
	private static Tree tree = new Tree();
	public static void main(String[] args) throws IOException {
		System.out.println("Enter the file path for validation set : ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String filename = br.readLine();
//		String filename="d:/13.csv";
		File f = new File(filename);
		System.out.println("Enter the file path for test set : ");
		String validationFile  = br.readLine();
//		String validationFile = "d:/14.csv";
		File validationF = new File(validationFile);
		System.out.println("Enter the value of l:");
		String lValue = br.readLine();
		System.out.println("Enter the value of k:");
		String kValue = br.readLine();
		
		
	try {
			
			Scanner input1 = new Scanner(f);
			input1.next();
			while(input1.hasNext())
			{
				
				String data = input1.next();
				String[] values = data.split(",");
				
				
				Instance instance = getInstanceObj(values);
				instanceList.add(instance);
				
			}
			Scanner input2 = new Scanner(validationF);
			input2.next();
			while(input2.hasNext())
			{
				
				String data = input2.next();
				String[] values = data.split(",");
				
				
				Instance instance = getInstanceObj(values);
				validationInstanceList.add(instance);
				
			}
			
			
			buildTree(instanceList, new HashMap<String, Integer>(), "-1", "");
			
			
			
			List<String> bestTree = new ArrayList<String>();
			for (String node : nodeList) {
				bestTree.add(node);
			}
			
			int l = Integer.parseInt(lValue);
			int k =Integer.parseInt(kValue);
			for (int i = 1; i<=l ; i++ ){
				List<String> newTree = new ArrayList<String>();
				for (String node : nodeList) {
					newTree.add(node);
				}
				
			    Random randM = new Random();
			    int m = randM.nextInt((k - 1) + 1) + 1;
			    
			    for(int j = 1; j<=m; j++) {
			    	
			    	int n = 0;
			    	for(String node : newTree) {
			    		if (!node.equals("1") && !node.equals("0")) {
			    			n++;
			    		}
			    	}
			    	
			    	List<String> nodesWithoutLeaf = new ArrayList<String>();
			    	for (String node : newTree) {
			    		if (!node.equals("1") && !node.equals("0")) {
			    			nodesWithoutLeaf.add(node);
			    		}
			    	}
			    	
				    Random randP = new Random();
				    int p = randP.nextInt((k - 1) + 1) + 1;
				    
				    
			    }
			    
			    
			    
			}
			
			
			double a = validationCount;
			double b = validationInstanceList.size();
			double v = a/b;
			v = (v*100) + 0.1;
			
			System.out.println("\n\n\nAccuracy : " + v);
			
			input1.close();
			
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		private static Instance getInstanceObj(String[] values) {
			
			Instance instance = new Instance();
			instance.setXb(Integer.parseInt(values[0]));
			instance.setXc(Integer.parseInt(values[1]));
			instance.setXd(Integer.parseInt(values[2]));
			instance.setXe(Integer.parseInt(values[3]));
			instance.setXf(Integer.parseInt(values[4]));
			instance.setXg(Integer.parseInt(values[5]));
			instance.setXh(Integer.parseInt(values[6]));
			instance.setXi(Integer.parseInt(values[7]));
			instance.setXj(Integer.parseInt(values[8]));
			instance.setXk(Integer.parseInt(values[9]));
			instance.setXl(Integer.parseInt(values[10]));
			instance.setXm(Integer.parseInt(values[11]));
			instance.setXn(Integer.parseInt(values[12]));
			instance.setXo(Integer.parseInt(values[13]));
			instance.setXp(Integer.parseInt(values[14]));
			instance.setXq(Integer.parseInt(values[15]));
			instance.setXr(Integer.parseInt(values[16]));
			instance.setXs(Integer.parseInt(values[17]));
			instance.setXt(Integer.parseInt(values[18]));
			instance.setXu(Integer.parseInt(values[19]));
			instance.setClassValue(Integer.parseInt(values[20]));
			
			return instance;
		
		}
		
		private static Map<Integer, Integer> getDecisionTreeValue(String attribute, int value, List<Instance> list) {
			
			Map<Integer, Integer> decisionMap = new HashMap<Integer, Integer>();
			decisionMap.put(0, 0);
			decisionMap.put(1, 0);
			
			
			for (Instance instance : list) {
				
				int attributeValue = getCellValue(attribute, instance);
				
				if (attributeValue == value) {
					
					if (instance.getClassValue() == 0) {
						
						int count = decisionMap.get(0);
						count++;
						decisionMap.put(0, count);
						
					} else {
						
						int count = decisionMap.get(1);
						count++;
						decisionMap.put(1, count);
						
					}
					
				}
				
				
			}
			
			
			return decisionMap;
			
			
			
			
		}
		
		
		private static int getCellValue(String attribute, Instance instance) {
			
			if (attribute.equalsIgnoreCase("xb")) {
				return instance.getXb();
			} else if (attribute.equalsIgnoreCase("xc")) {
				return instance.getXc();
			} else if (attribute.equalsIgnoreCase("xd")) {
				return instance.getXd();
			} else if (attribute.equalsIgnoreCase("xe")) {
				return instance.getXe();
			} else if (attribute.equalsIgnoreCase("xf")) {
				return instance.getXf();
			} else if (attribute.equalsIgnoreCase("xg")) {
				return instance.getXg();
			} else if (attribute.equalsIgnoreCase("xh")) {
				return instance.getXh();
			} else if (attribute.equalsIgnoreCase("xi")) {
				return instance.getXi();
			} else if (attribute.equalsIgnoreCase("xj")) {
				return instance.getXj();
			} else if (attribute.equalsIgnoreCase("xk")) {
				return instance.getXk();
			} else if (attribute.equalsIgnoreCase("xl")) {
				return instance.getXl();
			} else if (attribute.equalsIgnoreCase("xm")) {
				return instance.getXm();
			} else if (attribute.equalsIgnoreCase("xn")) {
				return instance.getXn();
			} else if (attribute.equalsIgnoreCase("xo")) {
				return instance.getXo();
			} else if (attribute.equalsIgnoreCase("xp")) {
				return instance.getXp();
			} else if (attribute.equalsIgnoreCase("xq")) {
				return instance.getXq();
			} else if (attribute.equalsIgnoreCase("xr")) {
				return instance.getXr();
			} else if (attribute.equalsIgnoreCase("xs")) {
				return instance.getXs();
			} else if (attribute.equalsIgnoreCase("xt")) {
				return instance.getXt();
			} else if (attribute.equalsIgnoreCase("xu")) {
				return instance.getXu();
			} else {
				return instance.getClassValue();
			}
			
			
			
		}
		
		
		
		private static void buildTree(List<Instance> instanceTreeList, Map<String, Integer> iteratedMap, String identify, String printValue) {
			int rootZeroCount = 0;
			int rootOneCount = 0;
			
			for (Instance instance : instanceTreeList) {
				
				if (instance.getClassValue() == 0) {
					rootZeroCount++;					
				} else {
					rootOneCount++;					
				}
				
			}
			
			if (rootZeroCount == 0) {
				System.out.print(identify + " : 1");
				getAccuracy(iteratedMap, 1);
				return;
			} else if (rootOneCount == 0) {
				System.out.print(identify + " : 0");
				getAccuracy(iteratedMap, 0);
				return;
			}
			
			List<String> nonIteratedList = new ArrayList<String>();
			for (String string : columnList) {
				
				if (!iteratedMap.containsKey(string)) {
					nonIteratedList.add(string);
				}
				
			}
			
			
			Map<String, Double> infoGainValueMap = new HashMap<String, Double>();
			double baseValue = 0;
			for (String attribute : nonIteratedList) {
				
				Map<Integer, Integer> zeroValueMap = getDecisionTreeValue(attribute, 0, instanceTreeList);
				Map<Integer, Integer> oneValueMap = getDecisionTreeValue(attribute, 1, instanceTreeList);
				
				
				//find entropy
				double totalNegativeValue = zeroValueMap.get(0) + zeroValueMap.get(1);
				double k0 = zeroValueMap.get(0);
				double k1 = zeroValueMap.get(1);
				double k2 = totalNegativeValue * totalNegativeValue;
				double zeroEntropyValue = k0 * k1/k2;
							
				
				double totalPostiveValue = oneValueMap.get(0) + oneValueMap.get(1);
				k0 = oneValueMap.get(0);
				k1 = oneValueMap.get(1);
				k2 = totalPostiveValue * totalPostiveValue;
				double oneEntropyValue = k0 * k1/k2;
				

				double totalRootValue = rootOneCount + rootZeroCount;
				k0 = rootOneCount;
				k1 = rootZeroCount;
				k2 = totalRootValue * totalRootValue;
				double rootEntropyValue = k0 * k1/k2;
				
				
				double negGain = (totalNegativeValue * zeroEntropyValue)/ totalRootValue;
				double posGain = (totalPostiveValue * oneEntropyValue)/ totalRootValue;
				double infoGain = rootEntropyValue - negGain - posGain;
				
				infoGainValueMap.put(attribute, infoGain);
				baseValue = infoGain;
				
				
			}
			
			
			Set<Entry<String, Double>> entrySet = infoGainValueMap.entrySet();
			String maxAttribute = "";
			for (Entry<String, Double> entry : entrySet) {
				
				if (baseValue <= entry.getValue()) {
					
					baseValue = entry.getValue();
					maxAttribute = entry.getKey();
					
				}
				
			}
			
			
			if (identify.equals("0")) {
				System.out.println("0 : ");
				System.out.print(printValue + maxAttribute + " = ");
				
			} else if (identify.equals("1")) {
				System.out.println("1 : ");
				System.out.print(printValue + maxAttribute + " = ");
			} else {
				System.out.print(maxAttribute + " = ");
			}
			
			
			if (baseValue == 0.0) {
				return ;
			}
			
			
			List<Instance> newInstanceZeroList = new ArrayList<Instance>();
			List<Instance> newInstanceOneList = new ArrayList<Instance>();
			
			for (Instance instance : instanceTreeList) {
				int cellValue = getCellValue(maxAttribute, instance);
				if (cellValue == 0) {
					newInstanceZeroList.add(instance);
				} else {
					newInstanceOneList.add(instance);
				}
				
			}
			
			Map<String, Integer> map1 = new HashMap<String, Integer>();
			Set<Entry<String, Integer>> entrySet1 = iteratedMap.entrySet();

			for (Entry<String, Integer> entry : entrySet1) {
				map1.put(entry.getKey(), entry.getValue());
			}
			map1.put(maxAttribute, 0);
			String v = printValue;
			buildTree(newInstanceZeroList, map1, "0", v + "|");
			
			Map<String, Integer> map2 = new HashMap<String, Integer>();
			Set<Entry<String, Integer>> entrySet2 = iteratedMap.entrySet();

			for (Entry<String, Integer> entry : entrySet2) {
				map2.put(entry.getKey(), entry.getValue());
			}
			map2.put(maxAttribute, 1);
			String a = printValue;
			
			System.out.println();
			System.out.print(a + maxAttribute + " = ");
			buildTree(newInstanceOneList, map2, "1", a + "|");
			
		}
		
		
		private static void getAccuracy(Map<String, Integer> iteratedMap, int leafValue) {
			
			Set<Entry<String, Integer>> entrySet = iteratedMap.entrySet();
			

			for (Instance instance : validationInstanceList) {
				int count = 0;
				
				for (Entry<String, Integer> entry : entrySet) {
					String attribute = entry.getKey();
					
					if (attribute.equalsIgnoreCase("xb") && (instance.getXb() == entry.getValue())) {
						count++;
					} else if (attribute.equalsIgnoreCase("xc") && (instance.getXc() == entry.getValue())) {
						count++;
					} else if (attribute.equalsIgnoreCase("xd") && (instance.getXd() == entry.getValue())) {
						count++;
					} else if (attribute.equalsIgnoreCase("xe") && (instance.getXe() == entry.getValue())) {
						count++;
					} else if (attribute.equalsIgnoreCase("xf") && (instance.getXf() == entry.getValue())) {
						count++;
					} else if (attribute.equalsIgnoreCase("xg") && (instance.getXg() == entry.getValue())) {
						count++;
					} else if (attribute.equalsIgnoreCase("xh") && (instance.getXh() == entry.getValue())) {
						count++;
					} else if (attribute.equalsIgnoreCase("xi") && (instance.getXi() == entry.getValue())) {
						count++;
					} else if (attribute.equalsIgnoreCase("xj") && (instance.getXj() == entry.getValue())) {
						count++;
					} else if (attribute.equalsIgnoreCase("xk") && (instance.getXk() == entry.getValue())) {
						count++;
					} else if (attribute.equalsIgnoreCase("xl") && (instance.getXl() == entry.getValue())) {
						count++;
					} else if (attribute.equalsIgnoreCase("xm") && (instance.getXm() == entry.getValue())) {
						count++;
					} else if (attribute.equalsIgnoreCase("xn") && (instance.getXn() == entry.getValue())) {
						count++;
					} else if (attribute.equalsIgnoreCase("xo") && (instance.getXo() == entry.getValue())) {
						count++;
					} else if (attribute.equalsIgnoreCase("xp") && (instance.getXp() == entry.getValue())) {
						count++;
					} else if (attribute.equalsIgnoreCase("xq") && (instance.getXq() == entry.getValue())) {
						count++;
					} else if (attribute.equalsIgnoreCase("xr") && (instance.getXr() == entry.getValue())) {
						count++;
					} else if (attribute.equalsIgnoreCase("xs") && (instance.getXs() == entry.getValue())) {
						count++;
					} else if (attribute.equalsIgnoreCase("xt") && (instance.getXt() == entry.getValue())) {
						count++;
					} else if (attribute.equalsIgnoreCase("xu") && (instance.getXu() == entry.getValue())) {
						count++;
					} else {
						count = count;
					}
					
				}
				
				
				
				if (iteratedMap.size() == count && instance.getClassValue() == leafValue) {
					validationCount++;
				}
				
				
				
			}
			
			
		}
		private static List<String> getColumnList() {
			List<String> columnList = new ArrayList<String>();
			columnList.add("xb");
			columnList.add("xc");
			columnList.add("xd");
			columnList.add("xe");
			columnList.add("xf");
			columnList.add("xg");
			columnList.add("xh");
			columnList.add("xi");
			columnList.add("xj");
			columnList.add("xk");
			columnList.add("xl");
			columnList.add("xm");
			columnList.add("xn");
			columnList.add("xo");
			columnList.add("xp");
			columnList.add("xq");
			columnList.add("xr");
			columnList.add("xs");
			columnList.add("xt");
			columnList.add("xu");
			
			return columnList;
		}
		
		
	}


