package Java.Main;
import java.sql.SQLException;

import Java.Code.Exception.ArgsInvalidException;
import Java.Code.Software.Software;
import Java.Gui.guiMain;

public class Main {
    public static Software main_software = null;

    public static void main(String[] args) throws InterruptedException, ArgsInvalidException, SQLException {
    	guiMain.getInstance();
    };
}