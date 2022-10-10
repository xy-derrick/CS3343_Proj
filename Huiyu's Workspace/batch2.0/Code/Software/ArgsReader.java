package Code.Software;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Scanner;

import Code.Command.Commands.HuiyuBAI.stringCommand;

public class ArgsReader {
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
        if(h!=null){        System.out.println(h);};
    }

    public ArrayList<Object> read(Class commond_name)
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
                    case "java.util.ArrayList":

                    String[] batchStrings = scanner.nextLine().split(">>");
                    String[] argLists = batchStrings[1].trim().split(",");
                    
                    if(batchStrings[0].trim().equals("img")){//判断是imgProcessor
                        for (String imgOrder:argLists){
                            args.add(Software.getInstance().getImgProcessorList().get(Integer.parseInt(imgOrder)));
                        }
                    }

                    else if (batchStrings[0].trim().equals("cmd")){//判断是Command
                        for (String cmd:argLists){
                            String[] cmdParts = cmd.split(" ");
                            int temp = cmdParts[0].length()+cmdParts[1].length()+2;
                            if(temp>=cmd.length()) 
                            args.add(new stringCommand(cmdParts[0], Integer.parseInt(cmdParts[1].trim()), null));

                            else
                            args.add(new stringCommand(cmdParts[0], Integer.parseInt(cmdParts[1].trim()), 
                            cmd.substring(temp)));
                            System.out.println("OK till now");

                        }
                        

                    } 
                        
                }       
            };
            return args;
        }
        catch(Exception e)
        {
            System.out.println("args type not right, plz input again");
            return null;
        }     
    }

}
