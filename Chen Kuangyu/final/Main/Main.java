import java.lang.reflect.Constructor;
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
import Code.Command.Commands.Export.bmpTransfer;
import Code.Command.Commands.Export.gifTransfer;
import Code.Command.Commands.Export.imagCompress;
import Code.Command.Commands.Export.jpgTransfer;
import Code.Command.Commands.Export.localSave;
import Code.Command.Commands.Export.pngTransfer;
import Code.Command.Commands.Export.tiffTransfer;
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
                case "export":
                    System.out.println("Export can deal with image type tansfer,zip and cloud saving.");
                    exportCommands(num);
                    break;

            }
        }
        catch(Exception e)
        {
            System.out.println("Unknown command!");
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

    public static void exportCommands(Integer num)
    {
        switch(num)
        {
            case 1: 
                quickCommand("pleaze input the save path: ", jpgTransfer.class,Software.getInstance().getMain_ip());
                break;
            case 2:
                quickCommand("pleaze input the save path: ", pngTransfer.class,Software.getInstance().getMain_ip());
                break;
            case 3:
                quickCommand("pleaze input the save path: ", gifTransfer.class,Software.getInstance().getMain_ip());
                break;
            case 4:
                quickCommand("pleaze input the save path: ", bmpTransfer.class,Software.getInstance().getMain_ip());
                break;
            case 5:
                quickCommand("pleaze input the save path: ", tiffTransfer.class,Software.getInstance().getMain_ip());
                break;
            case 6:
                quickCommand("pleaze input the zip path(together with the name.zip): ", imagCompress.class,Software.getInstance().getMain_ip());
                break;
            case 7:
                quickCommand("pleaze input the local path: ", localSave.class,Software.getInstance().getMain_ip());
                break;
            //cloud
            // case 8:
            //     // pass 
            //     quickCommand(null, displayImg.class,Software.getInstance().getMain_ip());
            //     break;
            
        };
    }



}
