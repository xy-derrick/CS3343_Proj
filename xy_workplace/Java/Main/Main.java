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
import Java.Code.Command.EditDecorator.CombineFilter;
import Java.Code.Command.EditDecorator.CreateBorder;
import Java.Code.Command.EditDecorator.EditDecorator;
import Java.Code.Command.EditDecorator.FlipHorizontal;
import Java.Code.Command.EditDecorator.FlipVertical;
import Java.Code.Command.EditDecorator.GrayFilter;
import Java.Code.Command.EditDecorator.HighContrastFilter;
import Java.Code.Command.EditDecorator.MosaicFilter;
import Java.Code.Command.EditDecorator.PaintFilter;
import Java.Code.Command.EditDecorator.Rotate180Degrees;
import Java.Code.Command.EditDecorator.Rotate90DegreesClockwise;
import Java.Code.Command.EditDecorator.Rotate90DegreesCounterclockwise;
import Java.Code.Command.EditDecorator.Tailoring;
import Java.Code.Command.EditDecorator.VintageFilter;
import Java.Code.Command.EditDecorator.Zoom;
import Java.Code.Exception.ArgsTypeNotRightException;
import Java.Code.Exception.CommandTypeNotDefinedException;
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
                 case "filter":
                	filterCommands(num);
                	 break;
                 case "edit":
                 	editCommands(num);
                 	 break;
                 default:
                     throw new CommandTypeNotDefinedException(type.toLowerCase());
            }
        }
        catch(Exception e)
        {
            System.out.println("e");
            e.printStackTrace(System.out);
        }
    }
    
    private static void editCommands(Integer num) {
		// TODO Auto-generated method stub
		switch (num) {
			case 1:
				decoratorCommand("no input now", FlipHorizontal.class,Software.getInstance().getMain_ip());
				break;
			case 2:
                decoratorCommand("no input now", FlipVertical.class,Software.getInstance().getMain_ip());	
                break;			
			case 3:
				decoratorCommand("no input now", Rotate90DegreesClockwise.class,Software.getInstance().getMain_ip());
				break;
            case 4:
				decoratorCommand("no input now", Rotate90DegreesCounterclockwise.class,Software.getInstance().getMain_ip());
				break;
            case 5:
				decoratorCommand("no input now", Rotate180Degrees.class,Software.getInstance().getMain_ip());
				break;
            case 6:
                decoratorCommand("4 integer to represent a=cutting start point(x),b=cutting start point(y),c=length of retention(x),d=length of retention(y)\n"+
                "a,b: 0-100,  c,d: 1-100  and a+c<=100, b+d<=100", Tailoring.class,Software.getInstance().getMain_ip());
				break;
			case 7:
                decoratorCommand("an integer to represent the scaling", Zoom.class,Software.getInstance().getMain_ip());
				break;
		}
	}

    private static void filterCommands(Integer num) {
		// TODO Auto-generated method stub
		switch (num) {
			case 1:
				decoratorCommand("no input now", GrayFilter.class,Software.getInstance().getMain_ip());
				break;
			case 2:
				decoratorCommand("an integer to represent the degree", HighContrastFilter.class,Software.getInstance().getMain_ip());
				break;
			case 3:
				decoratorCommand("from [0,100] represent noise", VintageFilter.class,Software.getInstance().getMain_ip());
				break;
			case 4:
				decoratorCommand("Please provide another IP id and the transparent degree", CombineFilter.class,Software.getInstance().getMain_ip());
				break;
			case 5:
				decoratorCommand("Please provide the mosaic size", MosaicFilter.class,Software.getInstance().getMain_ip());
				break;
			case 6:
				decoratorCommand("Please provide the degree", PaintFilter.class,Software.getInstance().getMain_ip());
				break;
			case 7:
				decoratorCommand("border size and ip num (invalid ipnum will use defult)", CreateBorder.class,Software.getInstance().getMain_ip());
				break;

		} 
	}

    public static void decoratorCommand(String hint,Class decorator_name,imgProcessor ip)
    {
        try
        {
        	EditCommand wrappee = new EditCommand(ip);
            ArgsReader.getInstance().hint(hint);
            ArrayList<Object>args_object = ArgsReader.getInstance().read(decorator_name);
            Constructor c= decorator_name.getConstructor(EditCommand.class,ArrayList.class);
            main_software.setCommand((EditDecorator)c.newInstance(wrappee,args_object));
            main_software.execute();
        }
        catch(Exception e)
        {
            System.out.println("Command failed ! ");
            System.out.println(e);
            e.printStackTrace(System.out);
        }
    }
	//输入参数错误应该重新输入还是直接退出？
    //所有操作有个前提是要有imgProcessor
    public static void commonCommands(Integer num)
    {
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
        };
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
        catch(Exception e){
            System.out.println("Command failed due to unknow situation !");
            System.out.println(e);
        }
    }

   
}
