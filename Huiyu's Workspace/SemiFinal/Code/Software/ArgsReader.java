package Code.Software;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Scanner;

import Code.Command.Commands.HuiyuBAI.ExUnbatchableCommand;
import Code.Command.Commands.HuiyuBAI.ExWrongBatchType;
import Code.Command.Commands.HuiyuBAI.batchableCommandDict;
import Code.Command.Commands.HuiyuBAI.stringCommand;
import Code.Exception.ArgsTypeNotRightException;

public class ArgsReader{
    static private ArgsReader reader = null;
    
    private ArgsReader(){};

    static public ArgsReader getInstance(){
        if(reader==null)
        {
            reader = new ArgsReader();
            return reader;
        }
        else
        {
            return reader;
        }
    }

    public void hint(String h)
    {
        if(h!=null){       System.out.println(h);};
    }

    public ArrayList<Object> read(Class commond_name)  throws ExUnbatchableCommand, ArgsTypeNotRightException 
    {
        try
        {
            Constructor con = commond_name.getDeclaredConstructors()[0];
            Class[] parameterTypes = con.getParameterTypes(); 
            //打印名字debug
            // for(int j=0;j<parameterTypes.length;j++)  
            // {
            //     System.out.println(parameterTypes[j].getName());
            // }
            Scanner scanner = new Scanner(System.in);
            ArrayList<Object> args = new ArrayList<>();
            for (int i = 1;i<parameterTypes.length;i++)
            {
                Class arg = parameterTypes[i];
                switch(arg.getName())
                {
                    case "java.lang.Float":
                        args.add(scanner.nextFloat());
                        break;
                    case "java.lang.Integer":
                        args.add(scanner.nextInt());
                        break;
                    case "java.lang.String":
                        args.add(scanner.next());
                        break;
                    case "java.lang.Boolean":
                        args.add(scanner.nextBoolean());
                        break;
//-----------------------------------------------------------------------------------------------列表分支
                    case "java.util.ArrayList":

                    String[] batchStrings = scanner.nextLine().split(">>");
                    String[] argLists = batchStrings[1].trim().split(",");
                    String batchType = batchStrings[0].trim();
                    if(batchType.equals("img")){//判断是imgProcessor

                    for (String imgOrder:argLists){
                             args.add(Integer.parseInt(imgOrder.trim()));

                        }
                    }

                    else if (batchType.equals("cmd")){//判断是Command
                        for (String cmd:argLists){
                            cmd = cmd.trim();
                            String[] cmdParts = cmd.split(" ");

                            String cmdType = cmdParts[0].trim();
                            Integer cmdNum = Integer.parseInt(cmdParts[1].trim());

                            if(!batchableCommandDict.getInstance().checkBatchable(cmdType,cmdNum))  
                            throw new ExUnbatchableCommand(cmdType,cmdNum);
                            
                            int temp = cmdParts[0].length()+cmdParts[1].length()+2;
                            if(temp>=cmd.length()) 
                            args.add(new stringCommand(cmdType, cmdNum, null));
                            else
                            args.add(new stringCommand(cmdType, cmdNum, cmd.substring(temp))); 
                           
                        }
                        

                    } else{
                        throw new ExWrongBatchType();
                    }                       
                }       
            };
            return args;
        }
        catch(ExUnbatchableCommand e){
            throw e;
        }
//-------------------------------------------------------------------------------------------------
        catch(Exception e)
        {
            throw new ArgsTypeNotRightException();
        }
        
    }

}
