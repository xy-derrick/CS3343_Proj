import Code.Command.Commands.FilterCommand;
import Code.Command.Commands.displayImg;
import Code.Command.Commands.readImgFromLocal;
import Code.Command.Commands.showImgInfo;
import Code.Command.Commands.Sample.EnumReadSampleCommand;
import Code.Command.Commands.Sample.SampleCommand;
import Code.Command.FilterDecorator.CombineFilter;
import Code.Command.FilterDecorator.GrayFilter;
import Code.Command.FilterDecorator.HighContrastFilter;
import Code.Command.FilterDecorator.VintageFilter;
import Code.Enum.Degree;
import Code.Software.Software;
import Code.Software.imgProcessor;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Software main_software = Software.getInstance();

        //单图片单处理
        imgProcessor ip_01 = new imgProcessor();
        main_software.setCommand(new showImgInfo(ip_01));
        main_software.execute();
        String urlToReadImgFile = "C:\\Users\\Administrator\\Desktop\\desktop_small.jpg";
        main_software.setCommand(new readImgFromLocal(ip_01,urlToReadImgFile));
        main_software.execute();
        main_software.setCommand(new displayImg(ip_01));
        main_software.execute();
//        main_software.setCommand(new VintageFilter(new CombineFilter(new FilterCommand(ip_01))));
//        main_software.execute();
//        main_software.setCommand(new displayImg(ip_01));
//        main_software.execute();
//        main_software.undo();
//        main_software.setCommand(new GrayFilter(new FilterCommand(ip_01)));
//        main_software.execute();
        main_software.setCommand(new GrayFilter(new CombineFilter(new FilterCommand(ip_01))));
        main_software.execute();
        //main_software.undo();
	    main_software.setCommand(new displayImg(ip_01));
	    main_software.execute();
	       
//	    //单图片单处理
//        imgProcessor ip_01 = new imgProcessor();
//        main_software.setCommand(new SampleCommand(ip_01));
//        main_software.execute();
//
//        //单图片多处理
//        imgProcessor ip_02 = new imgProcessor();
//        main_software.setCommand(new SampleCommand(ip_02));
//        main_software.execute();
//        main_software.setCommand(new EnumReadSampleCommand(ip_02,Degree.high));
//        main_software.execute();
//        main_software.undo();
//        main_software.redo();
//        //多图单处理 多图片多处理 循环上诉操作
//
//
//
//        main_software.setCommand(new showImgInfo(ip_01));
//        main_software.execute();
//        String urlToReadImgFile = "C:\\Users\\19288\\OD\\Desktop\\design.png";
//        main_software.setCommand(new readImgFromLocal(ip_01,urlToReadImgFile));
//        main_software.execute();
//        main_software.setCommand(new displayImg(ip_01));
//        main_software.execute();
    }
}
