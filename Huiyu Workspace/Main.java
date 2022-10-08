import java.util.ArrayList;

import Code.Command.Base.Command;
import Code.Command.Commands.FilterCommand;
import Code.Command.Commands.displayImg;
import Code.Command.Commands.readImgFromLocal;
import Code.Command.Commands.showImgInfo;
import Code.Command.Commands.HuiyuBAI.batchAdd;
import Code.Command.Commands.HuiyuBAI.batchDelete;
import Code.Command.Commands.HuiyuBAI.batchExecute;
import Code.Command.Commands.HuiyuBAI.batchProcessor;
import Code.Command.Commands.Sample.SampleCommand1;
import Code.Software.Software;
import Code.Software.imgProcessor;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Software main_software = Software.getInstance();

        imgProcessor ip_01 = new imgProcessor();
        imgProcessor ip_02 = new imgProcessor();
        imgProcessor ip_03 = new imgProcessor();

        main_software.setCommand(new showImgInfo(ip_01));
        main_software.execute();
        String urlToReadImgFile1 = "C:\\Users\\Emery BAI\\Desktop\\a.jpg";
        String urlToReadImgFile2 = "C:\\Users\\Emery BAI\\Desktop\\b.jpg";
        String urlToReadImgFile3 = "C:\\Users\\Emery BAI\\Desktop\\c.jpg";

        main_software.setCommand(new readImgFromLocal(ip_01,urlToReadImgFile1));
        main_software.execute();
        main_software.setCommand(new readImgFromLocal(ip_02,urlToReadImgFile2));
        main_software.execute();
        main_software.setCommand(new readImgFromLocal(ip_03,urlToReadImgFile3));
        main_software.execute();
//      main_software.setCommand(new displayImg(ip_01));
//      main_software.execute();
 //       main_software.setCommand(new GrayFilter(new FilterCommand(ip_01)));
 //       main_software.execute();
 /* 
        main_software.setCommand(new displayImg(ip_01));
        main_software.execute();
        main_software.undo();
        main_software.undo();
*/
//      Thread.sleep(1000);
//	    main_software.setCommand(new displayImg(ip_01));
//	    main_software.execute();
	       
//	
//        imgProcessor ip_01 = new imgProcessor();
//        main_software.setCommand(new SampleCommand(ip_01));
//        main_software.execute();
//


//    
//        imgProcessor ip_02 = new imgProcessor();
//        main_software.setCommand(new SampleCommand(ip_02));
//        main_software.execute();
//        main_software.setCommand(new EnumReadSampleCommand(ip_02,Degree.high));
//        main_software.execute();
//        main_software.undo();
//        main_software.redo();
//
//
//        main_software.setCommand(new showImgInfo(ip_01));
//        main_software.execute();
//        String urlToReadImgFile = "C:\\Users\\19288\\OD\\Desktop\\design.png";
//        main_software.setCommand(new readImgFromLocal(ip_01,urlToReadImgFile));
//        main_software.execute();
//        main_software.setCommand(new displayImg(ip_01));
//        main_software.execute();

//--------------------------------------------------------------------------------------------
       
        batchProcessor bp_01 = new batchProcessor();

        ArrayList<imgProcessor> imgs01 = new ArrayList<imgProcessor>();
        imgs01.add(ip_01);
        imgs01.add(ip_02);
        imgs01.add(ip_03);

        ArrayList<imgProcessor> imgs02 = new ArrayList<imgProcessor>();
        imgs01.add(ip_02);

        Command cmd01 = new SampleCommand1(ip_02);
        ArrayList<Command> cmds01 = new ArrayList<Command>();
        cmds01.add(cmd01);
//--------------------------------------------------------------------------------------------




        main_software.setCommand(new batchAdd(bp_01,imgs01));
        main_software.execute();
        
        main_software.setCommand(new batchDelete(bp_01,imgs02));
        main_software.execute();

        main_software.setCommand(new batchAdd(bp_01,cmds01));
        main_software.execute();

        main_software.setCommand(new batchExecute(bp_01));
        main_software.execute();

        
        main_software.undo();
        main_software.redo();

        main_software.undo();
        main_software.undo();
        main_software.undo();

//---------------------------------------------------------------------------------------------
    }
}
