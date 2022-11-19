package Java.Main;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;

import JDBCUtils.jdbc_test;
import Java.Code.Command.Commands.EditCommand;
import Java.Code.Command.Export.bmpTransfer;
import Java.Code.Command.Export.gifTransfer;
import Java.Code.Command.Export.imagCompress;
import Java.Code.Command.Export.jpgTransfer;
import Java.Code.Command.Export.localSave;
import Java.Code.Command.Export.pngTransfer;
import Java.Code.Command.Export.tiffTransfer;
import Java.Code.Command.Login.autoSave;
import Java.Code.Batch.batchAdd;
import Java.Code.Batch.batchDelete;
import Java.Code.Batch.batchExecute;
import Java.Code.Batch.batchPreview;
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
import Java.Code.Command.Base.Command;
import Java.Code.Command.Commands.showOperationHint;
import Java.Code.Command.Commands.Common.changeImgProcessor;
import Java.Code.Command.Commands.Common.closeAllImgProcessors;
import Java.Code.Command.Commands.Common.closeImgProcessor;
import Java.Code.Command.Commands.Common.displayImg;
import Java.Code.Command.Commands.Common.existSoftware;
import Java.Code.Command.Commands.Common.readImgFromLocal;
import Java.Code.Exception.ArgsTypeNotRightException;
import Java.Code.Exception.CommandIndexOverLimit;
import Java.Code.Exception.CommandTypeNotDefinedException;
import Java.Code.Exception.ImgProcessorSelectedIsNullException;
import Java.Code.Exception.NoCommandToRedoException;
import Java.Code.Exception.NoCommandToUndoException;
import Java.Code.Software.ArgsReader;
import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;

public class Main_cmd {
    public static Software main_software = null;
    public static Timer timer = null;
    
    public static void main(String[] args) {

        try
        {
            main_software = Software.getInstance();
            //---------------------------------------
            //File f = new File("testTxt\\case1.txt");
            
            Scanner scanner = new Scanner(System.in);
//---------------------------------------------------------
            String type = null;
            Integer num = null;
            

    		System.out.print("Register or Login:(If register, input 1. If login, input 2. If skip, input other.): ");
    		String decision = scanner.next();
    		switch (decision) {
    		case "1":
    			try {
    				System.out.print("Input your user name:");
    				String username = scanner.next();
    				System.out.print("Input your your password:：");
    				String password = scanner.next();

    				boolean flag = new jdbc_test().register(username, password);
    				while (!flag) {
    					System.out.print("Register fail,Please reinput the usename：");
    					username = scanner.next();
    					System.out.print("Please inpit your password：");
    					password = scanner.next();
    					flag = new jdbc_test().register(username, password);
    				}
    			}
    			catch(Exception e)
    			{
    				System.out.println(e);
    			}
    			System.out.println("Sucessful register");
    			break;
    		case "2":
    			try {
    				System.out.print("Input your user name: ");
    				String username = scanner.next();
    				System.out.print("Input your your password:：");
    				String password = scanner.next();

    				boolean flag = new jdbc_test().login(username, password);
    				while (!flag) {
    					System.out.print("Login fail,Please reinput the usename：");
    					username = scanner.next();
    					System.out.print("Please inpit your password：");
    					password = scanner.next();
    					flag = new jdbc_test().login(username, password);
    				}
    			}
    			catch(Exception e)
    			{
    				System.out.println(e);
    			}
    			System.out.println("Sucessful login");
    			timer = autoSave.onLoginSuccessful();
    			break;

    		}
    		
    		
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
                case "edit":
                    editCommands(num);
                    break;
                case "filter":
                    filterCommands(num);
                    break;
                case "export":
                    System.out.println("Export can deal with image type tansfer,zip and cloud saving.");
                    exportCommands(num);
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
            "img>>idx 1, idx 2, idx3\n"+
            "or \n"+
            "cmd>>cmd1Type cmd1Num cmd1Paramter,cmd2Type....\n-----------------------", batchAdd.class,null);
            break;

            case 2:
            quickCommand("please add img or cmd:\n"+
            "Common format is like:\n"+
            "img>>idx 1, idx 2, idx3\n"+
            "or \n"+
            "cmd>>cmd1Type cmd1Num cmd1Paramter,cmd2Type....\n-----------------------", batchDelete.class,null);
            break;

            case 3:
            quickCommand(null, batchPreview.class,null);
            break;

            case 4:
            quickCommand(null, batchExecute.class,null);
            break;
            
            
        }
    }catch(Exception e){
        System.out.println(e.getMessage());
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

    public static void exportCommands(Integer num) {
        switch (num) {
            case 1:
                quickCommand("pleaze input the save path: ", jpgTransfer.class, Software.getInstance().getMain_ip());
                break;
            case 2:
                quickCommand("pleaze input the save path: ", pngTransfer.class, Software.getInstance().getMain_ip());
                break;
            case 3:
                quickCommand("pleaze input the save path: ", gifTransfer.class, Software.getInstance().getMain_ip());
                break;
            case 4:
                quickCommand("pleaze input the save path: ", bmpTransfer.class, Software.getInstance().getMain_ip());
                break;
            case 5:
                quickCommand("pleaze input the save path: ", tiffTransfer.class, Software.getInstance().getMain_ip());
                break;
            case 6:
                quickCommand("pleaze input the zip path: ", imagCompress.class, Software.getInstance().getMain_ip());
                break;
            case 7:
                quickCommand("pleaze input the local path: ", localSave.class, Software.getInstance().getMain_ip());
                break;

        }
        ;
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