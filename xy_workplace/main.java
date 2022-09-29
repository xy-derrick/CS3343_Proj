package xy_workplace;

import xy_workplace.packages.Command.FilterCommand;
import xy_workplace.packages.Software.Software;
import xy_workplace.packages.Software.imgProcessor;

public class Main {
    public static void main(String[] args) {

        Software main_software = Software.getInstance();

        // 单图片单处理
        imgProcessor ip_01 = new imgProcessor();
        main_software.setCommand(new FilterCommand(ip_01));
        main_software.execute();
    }
}