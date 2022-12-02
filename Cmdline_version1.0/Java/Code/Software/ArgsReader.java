package Java.Code.Software;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Scanner;

import Java.Code.Exception.*;
import Java.Code.Command.Batch.batchableCommandDict;
import Java.Code.Command.Batch.transCommand;

public class ArgsReader {
	static private ArgsReader reader = null;

	private ArgsReader() {
	}

	static public ArgsReader getInstance() {
		if (reader == null) {
			reader = new ArgsReader();
			return reader;
		} else {
			return reader;
		}
	}

	public void hint(String h) {
		if (h != null) {
			System.out.println(h);
		}
		;
	}

	public ArrayList<Object> read(Class commond_name,boolean whetherTest,int count,String[] test) {
		try {
			Constructor con = commond_name.getDeclaredConstructors()[0];
			for (Constructor contemp : commond_name.getDeclaredConstructors()) {
				boolean flag = true;
				for (Class para : contemp.getParameterTypes()) {
					if (para.getName() == "java.util.ArrayList") {
						flag = false;
						break;
					}
				}
				if (flag == true) {
					con = contemp;
				}

			}
			Class[] parameterTypes = con.getParameterTypes();
			Scanner scanner = new Scanner(System.in);
			ArrayList<Object> args = new ArrayList<>();
			for (int i = 1; i < parameterTypes.length; i++) {
				Class arg = parameterTypes[i];
				switch (arg.getName()) {
				case "java.lang.Float":
					args.add(whetherTest? Float.parseFloat(test[count++]):scanner.next());
					break;
				case "java.lang.Integer":
					args.add(whetherTest? Integer.valueOf(test[count++]):scanner.nextInt());
					break;
				case "java.lang.String":
					args.add(whetherTest? test[count++]:scanner.next());
					break;
				case "java.lang.Boolean":
					args.add(whetherTest? Boolean.valueOf(test[count++]):scanner.next());
					break;
					//--------------------------------------
				case "java.util.ArrayList":
					String rawString ;
					rawString = (whetherTest?test[count]:scanner.nextLine());
					
                    String[] blockL1 = rawString.split(">>");
                    // "cmd"  "export 1 path, filter 2 32,2351,23 43"
                    
                    String[] blockL2 = blockL1[1].trim().split(",");
                    // "export 1 path" "filter 2 32 2 23 43"
                    
                    String batchType = blockL1[0].trim();
                    // "cmd"
                    
                    if(batchType.equals("img")){//判断是imgProcessor
                        for (String imgOrder:blockL2){
                            args.add(Integer.parseInt(imgOrder.trim()));
                        }
                    }

                    else if (batchType.equals("cmd")){//判断是Command
                        for (String cmd:blockL2){
                            cmd = cmd.trim();
                            
                            String[] blockL3 = cmd.split(" ");

                            String cmdType = blockL3[0].trim();
                            Integer cmdNum = Integer.parseInt(blockL3[1].trim());

                            if(!batchableCommandDict.getInstance().checkBatchable(cmdType,cmdNum))  
                            break;
                            int temp = blockL3[0].length()+blockL3[1].length()+1;
                        	ArrayList<Object> batchParameters = new ArrayList<>();
                        	if(temp>=cmd.length()) {
                        		System.out.println("0. "+cmdType+" "+cmdNum);
                                args.add(new transCommand(cmdType, cmdNum, ""));

                        	}
                            else {
                        		System.out.println("0."+cmdType+cmdNum+cmd.substring(temp));
                                args.add(new transCommand(cmdType, cmdNum, cmd.substring(temp)));
                            }
                        }
                    } 
                   	
				//--------------------------------------
				}
			}
			;
			for(Object i : args)
			{
				System.out.println(i);
			}
			return args;
		} catch (Exception e) {
			System.out.println("args type not right, plz input again");
			return null;
		}
	}

}