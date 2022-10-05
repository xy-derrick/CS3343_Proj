package Code.Command.Commands.HuiyuBAI;

import java.util.ArrayList;

import Code.Command.Base.Command;

public class batchExecute extends Command{
    private batchProcessor bp;
    public batchExecute(batchProcessor _bp) {
        super(null);
        bp = _bp;
    }
    @Override
    public void execute() {
        // TODO Auto-generated method stub

        bp.notifyAlls();
        System.out.println("BatchExecute runs successfully.");
    }
    @Override
    public void undo() {
        bp.unotifyAlls();
        System.out.println("BatchExecute undo successfully.");
    }



}
