package Code.Software;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Scanner;

public class ArgsReader {
    static private ArgsReader reader = null;
    
    private ArgsReader(){};

    static public ArgsReader getInstance(){
        if(reader==null)
        {
            return new ArgsReader();
        }
        else
        {
            return reader;
        }
    }

    public void hint(String h)
    {
        System.out.println(h);
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
