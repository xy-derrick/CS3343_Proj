package Java.Main;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Scanner;

import Java.Code.Command.Base.Command;
import Java.Code.Command.Commands.EditCommand;
import Java.Code.Command.Commands.showOperationHint;
import Java.Code.Command.Commands.Common.changeImgProcessor;
import Java.Code.Command.Commands.Common.closeAllImgProcessors;
import Java.Code.Command.Commands.Common.closeImgProcessor;
import Java.Code.Command.Commands.Common.displayImg;
import Java.Code.Command.Commands.Common.existSoftware;
import Java.Code.Command.Commands.Common.readImgFromLocal;
import Java.Code.Command.Commands.Common.showImgInfo;
import Java.Code.Command.EditDecorator.EditDecorator;
import Java.Code.Software.ArgsReader;
import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;

public class Main {
    public static Software main_software = null;

    public static void main(String[] args) {

        try
        {
            main_software = Software.getInstance();
            Scanner scanner = new Scanner(System.in);
            String type = null;
            String num = null;
            System.out.println("\nWelcome to Img Process Software !\n"+ 
            "please select operation from the following list :\n");

            while(true)
            {
                main_software.setCommand(new showOperationHint(null));
                main_software.execute();
                try
                {
                    type = scanner.next();
                    num = scanner.next();
                    switchCommand(type,num);
                }
                catch(Exception e)
                {
                    System.out.println("Your input command doesnot follow the 'Type Num' format!");
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("software initialization failed !");
            System.out.println(e);
        }       
    }

    public static void quickCommand(String hint,Class commond_name,imgProcessor ip)
    {
        try
        {
            ArgsReader.getInstance().hint(hint);
            ArrayList<Object>args_object = ArgsReader.getInstance().read(commond_name);

            Constructor c= commond_name.getConstructor(imgProcessor.class,ArrayList.class);

            main_software.setCommand((Command)c.newInstance(ip,args_object));
            main_software.execute();
        }
        catch(Exception e)
        {
            System.out.println("Command failed ! ");
            System.out.println(e);
        }
    }

    public static void switchCommand(String type,String num)
    {
        //args reader usage sample
        try
        {
            switch(type.toLowerCase())
            {
                 case "common":
                     commonCommands(num);
                     break;
                 case "filter":
                	 //scan a list here for operation and args
                	 //filterCommands will receive a list later.
                     String stringArray[] = num.split(",");
                     ArrayList<String> filterKind = new ArrayList<>();
                     for (String item:stringArray) {
                         //list.add(Integer.parseInt(stringArray[i]));
                    	 System.out.println(item);
                    	 filterKind.add(item.substring(0,1));
             
                     }
                     for (String x:filterKind) {
                         //list.add(Integer.parseInt(stringArray[i]));
                    	 System.out.println(x);
                     }  
                	 filterCommands(filterKind);
            }
        }
        catch(Exception e)
        {
            System.out.println("Unknown command!");
        }
    }

    //输入参数错误应该重新输入还是直接退出？
    //所有操作有个前提是要有imgProcessor
    public static void commonCommands(String num)
    {
        switch(num)
        {
            case "1": 
                quickCommand("pleaze input your image file address as a String", readImgFromLocal.class,null);
                break;
            case "2":
                quickCommand("pleaze input the id of image processor as a int", changeImgProcessor.class,Software.getInstance().getMain_ip());
                break;
            case "3":
                Software.getInstance().undo();
                break;
            case "4":
                Software.getInstance().redo();
                break;
            case "5":
                quickCommand(null, closeImgProcessor.class,Software.getInstance().getMain_ip());
                break;
            case "6":
                quickCommand(null, closeAllImgProcessors.class,Software.getInstance().getMain_ip());
                break;
            case "7":
                // pass 
                quickCommand(null, displayImg.class,Software.getInstance().getMain_ip());
                break;
            case "8":
                // pass 
                quickCommand(null, showImgInfo.class,Software.getInstance().getMain_ip());
                break;
            case "9":
                // pass 
                quickCommand(null, existSoftware.class,null);
                break;
        };
    }
    
    public static void filterCommands(ArrayList<String> filterKind)
    {
    	for (int i=0;i<filterKind.size();i++) {
    		String num=filterKind.get(i);
    		//根据num套decorator
    		switch(num)
            {
                case "1": 
                    
                    break;
                case "2":
                    
                    break;
                case "3":
                    
                    break;
                case "4":
                    
                    break;
            };
    	}
    }
    
    public static void filterCommand(String hint,Class commond_name,imgProcessor ip)
    {
        try
        {
            ArgsReader.getInstance().hint(hint);
            ArrayList<Object>args_object = ArgsReader.getInstance().read(commond_name);

            Constructor c= commond_name.getConstructor(imgProcessor.class,ArrayList.class);

            main_software.setCommand((Command)c.newInstance(ip,args_object));
            main_software.execute();
        }
        catch(Exception e)
        {
            System.out.println("Command failed ! ");
            System.out.println(e);
        }
    }

}
