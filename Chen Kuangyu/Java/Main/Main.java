import java.util.Scanner;

import Code.Command.Commands.displayImg;
import Code.Command.Commands.readImgFromLocal;
import Code.Command.Commands.showImgInfo;
import Code.Command.Commands.Sample.EnumReadSampleCommand;
import Code.Command.Commands.Sample.SampleCommand;
import Code.Enum.Degree;
import Code.Software.Software;
import Code.Software.imgProcessor;
import Code.Command.Commands.Export.*;

public class Main {
    public static void main(String[] args) {

        Software main_software = Software.getInstance();

        //单图片单处理
        imgProcessor ip_01 = new imgProcessor();
        // main_software.setCommand(new SampleCommand(ip_01));
        // main_software.execute();

        // //单图片多处理
        // imgProcessor ip_02 = new imgProcessor();
        // main_software.setCommand(new SampleCommand(ip_02));
        // main_software.execute();
        // main_software.setCommand(new EnumReadSampleCommand(ip_02,Degree.high));
        // main_software.execute();
        // main_software.undo();
        // main_software.redo();
        // //多图单处理 多图片多处理 循环上诉操作

        Scanner input =new Scanner(System.in);
        System.out.print("Please input the path: ");
		String path = input.next();
        main_software.setCommand(new showImgInfo(ip_01));
        main_software.execute();
        String urlToReadImgFile = "C:\\Users\\chenkuangyu\\Desktop\\屏幕截图 2022-10-01 132127.png";
        main_software.setCommand(new readImgFromLocal(ip_01,urlToReadImgFile));
        main_software.execute();
        main_software.setCommand(new imagCompress(ip_01,path));
        main_software.execute();

        input.close();
    }
}
