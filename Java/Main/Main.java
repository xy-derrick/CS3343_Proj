import javax.swing.ImageIcon;

import Code.Command.Base.Invoker;
import Code.Command.Commands.EnumReadSampleCommand;
import Code.Command.Commands.SampleCommand;
import Code.Enum.Degree;
import Code.Software.Software;
import Code.Software.imgProcessor;

public class Main {
    public static void main(String[] args) {

        Software Software = Code.Software.Software.getInstance();
        Invoker invoker = new Invoker();

        //单图片单处理
        imgProcessor ip_01 = new imgProcessor();
        invoker.setCommand(new SampleCommand(ip_01));
        invoker.execute();

        //单图片多处理
        imgProcessor ip_02 = new imgProcessor();
        invoker.setCommand(new SampleCommand(ip_02));
        invoker.execute();
        invoker.setCommand(new EnumReadSampleCommand(ip_02,Degree.high));
        invoker.execute();

        //多图单处理 多图片多处理 循环上诉操作
    }
}
