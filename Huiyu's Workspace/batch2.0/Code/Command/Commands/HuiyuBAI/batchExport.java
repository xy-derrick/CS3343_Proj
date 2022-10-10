package Code.Command.Commands.HuiyuBAI;

import java.util.ArrayList;

import Code.Command.Base.CommandNoncancelabe;
import Code.Software.imgProcessor;

public class batchExport extends batchExecute implements CommandNoncancelabe{
    public batchExport(imgProcessor ip) {
        super(ip);
        //TODO Auto-generated constructor stub

    }
    public batchExport(imgProcessor ip, ArrayList<?> ob) {
        super(ip, ob);
        //TODO Auto-generated constructor stub
 
    }
    public void execute() {
        // TODO Auto-generated method stub

        bp.notifyAlls();
        bp.reset();
        System.out.println("BatchExport runs successfully.");
    }
}
