import Code.Command.Commands.EnumReadSampleCommand;
import Code.Command.Commands.SampleCommand;
import Code.Enum.Degree;
import Code.Software.Software;
import Code.Software.imgProcessor;

public class Main {
    public static void main(String[] args) {

        Software main_software = Software.getInstance();

        //单图片单处理
        imgProcessor ip_01 = new imgProcessor();
        main_software.setCommand(new SampleCommand(ip_01));
        main_software.execute();

        //单图片多处理
        imgProcessor ip_02 = new imgProcessor();
        main_software.setCommand(new SampleCommand(ip_02));
        main_software.execute();
        main_software.setCommand(new EnumReadSampleCommand(ip_02,Degree.high));
        main_software.execute();
        main_software.undo();
        main_software.redo();
        //多图单处理 多图片多处理 循环上诉操作
    }
}
