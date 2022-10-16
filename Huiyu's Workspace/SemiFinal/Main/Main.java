package Main;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;

import Code.Command.Base.Command;
import Code.Command.Commands.showOperationHint;
import Code.Command.Commands.Common.changeImgProcessor;
import Code.Command.Commands.Common.closeAllImgProcessors;
import Code.Command.Commands.Common.closeImgProcessor;
import Code.Command.Commands.Common.displayImg;
import Code.Command.Commands.Common.existSoftware;
import Code.Command.Commands.Common.readImgFromLocal;
import Code.Command.Commands.Common.showImgInfo;
import Code.Command.Commands.HuiyuBAI.CommandParameter;
import Code.Command.Commands.HuiyuBAI.ExUnbatchableCommand;
import Code.Command.Commands.HuiyuBAI.batchAdd;
import Code.Command.Commands.HuiyuBAI.batchDelete;
import Code.Command.Commands.HuiyuBAI.batchExecute;
import Code.Command.Commands.HuiyuBAI.batchExport;
import Code.Command.Commands.HuiyuBAI.batchProcessor;
import Code.Exception.ArgsTypeNotRightException;
import Code.Exception.CommandIndexOverLimit;
import Code.Exception.CommandTypeNotDefinedException;
import Code.Exception.ImgProcessorSelectedIsNullException;
import Code.Exception.NoCommandToRedoException;
import Code.Exception.NoCommandToUndoException;
import Code.Software.ArgsReader;
import Code.Software.Software;
import Code.Software.imgProcessor;

public class Main {
    public static Software main_software = null;

    public static void main(String[] args) {

        try
        {
            main_software = Software.getInstance();
            Scanner scanner = new Scanner(System.in);
            String type = null;
            Integer num = null;
            System.out.println("\nWelcome to Img Process Software !\n"+ 
            "please select operation from the following list :\n");

            while(true)
            {
                main_software.setCommand(new showOperationHint(null));
                main_software.execute();
                try
                {
                    type = scanner.next();
                    num = scanner.nextInt();
                    switchCommand(type,num);
                }
                catch(Exception e)
                {
                    System.out.println("Your input command does not follow the 'Type Num' format!");
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("software initialization failed !");
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
        //---------------------------------------
        catch(InvocationTargetException e){
            System.out.println(e.getTargetException().getMessage());
        }
        catch(ExUnbatchableCommand e){
            System.out.println(e.getMessage());
        }
        catch(Exception e){
            System.out.println("Command failed due to unknow situation !");
        //-------------------------
            System.out.println(e.getMessage());
        //-------------------------
        }
    }

    public static void switchCommand(String type,Integer num)
    {
        //args reader usage sample
        try
        {
            switch(type.toLowerCase())
            {
                 case "common":
                     commonCommands(num);
                     break;
                case "batch":
                    batchCommands(num);
                    break;
                 default:
                    throw new CommandTypeNotDefinedException(type.toLowerCase());
            }
        }catch(CommandTypeNotDefinedException e){
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    private static void batchCommands(Integer num) {
        try{

        
        switch(num)
        {
            case 1:
            quickCommand("please add img or cmd:\n"+
            "Common format is like:\n"+
            "img>>number1,number2,number3 (the number is the order of the img in the image list)\n"+
            "or \n"+
            "cmd>>cmd1Type cmd1Num cmd1Paramter,cmd2Type cmd2Num cmd2Paramter", batchAdd.class,null);
            break;

            case 2:
            quickCommand("please delete img or cmd:\n"+
            "Common format is like:\n"+
            "img>>number1,number2,number3 (the number is the order of the img in the image list)\n"+
            "or \n"+
            "cmd>>cmd1Type cmd1Num cmd1Paramter,cmd2Type cmd2Num cmd2Paramter", batchDelete.class,null);
            break;

            case 3:
            quickCommand(null, batchExecute.class,null);
            break;

            case 4:
            quickCommand(null, batchExport.class,null);
            break;
            
            case 5:
            batchProcessor.getInstance().unotifyAlls();
            break;

            case 6:
            batchProcessor.getInstance().notifyAlls();
            break;
        }
    }catch(Exception e){
        System.out.println(e.getMessage());
    }
      

    }

    //输入参数错误应该重新输入还是直接退出？
    //所有操作有个前提是要有imgProcessor
    public static void commonCommands(Integer num)
    {
        try{
            switch(num)
            {
                case 1: 
                    quickCommand("pleaze input your image file address as a String", readImgFromLocal.class,null);
                    break;
                case 2:
                    quickCommand("pleaze input the id of image processor as a int", changeImgProcessor.class,Software.getInstance().getMain_ip());
                    break;
                case 3:
                    Software.getInstance().undo();
                    break;
                case 4:
                    Software.getInstance().redo();
                    break;
                case 5:
                    quickCommand(null, closeImgProcessor.class,Software.getInstance().getMain_ip());
                    break;
                case 6:
                    quickCommand(null, closeAllImgProcessors.class,Software.getInstance().getMain_ip());
                    break;
                case 7:
                    // pass 
                    quickCommand(null, displayImg.class,Software.getInstance().getMain_ip());
                    break;
                case 8:
                    // pass 
                    quickCommand(null, showImgInfo.class,Software.getInstance().getMain_ip());
                    break;
                case 9:
                    // pass 
                    quickCommand(null, existSoftware.class,null);
                    break;
              
                default:
                    throw new CommandIndexOverLimit(num);
            };
        }catch(CommandIndexOverLimit e){
        }catch(NullPointerException e){
            System.out.println("Please select main image processor first !");
        }
        
        catch(Exception e){
            System.out.println(e);
        }
    }
}
