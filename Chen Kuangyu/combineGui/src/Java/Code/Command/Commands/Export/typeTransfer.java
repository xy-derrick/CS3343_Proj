package Code.Command.Commands.Export;

import Code.exportException.*;
import Code.Command.Base.Command;
import Code.Command.Base.CommandNoncancelabe;
import Code.Software.imgProcessor;

public abstract class typeTransfer extends Command implements CommandNoncancelabe {

    protected static int newName = 1;

    public typeTransfer(imgProcessor receiver) {
        super(receiver);
    }

    /*
     * 总共为5种格式互相转化png，jpg，tiff，gif，bmp
     */
    public abstract void transfer() throws nameNotFoundException;

    public String getName(String localPath) {
        String fName = localPath.trim();
        // System.out.println(fName);
        String name = fName.substring(fName.lastIndexOf("\\") + 1);
        return name.substring(0, name.lastIndexOf("."));
    }

    @Override
    public void execute() {
        // ignore receiver

        try {
            transfer();
        } catch (nameNotFoundException e) {
            System.out.println("Please rename your imag file!");
        }
        newName++;

    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }
}
