package Code.Command.Commands.Common;

import java.util.ArrayList;
import Code.Command.Base.Command;
import Code.Software.Software;
import Code.Software.imgProcessor;
import Code.Software.ipState;

public class closeAllImgProcessors extends Command {
    ArrayList<imgProcessor> ipList = null;
    ipState state_last = null;
    ipState state_curr = null;

    public closeAllImgProcessors(imgProcessor receiver) {
        super(receiver);
    }

    public closeAllImgProcessors(imgProcessor receiver, ArrayList<Object> args) {
        super(receiver);
    }

    @Override
    public void execute() {
        // ignore receiver

        try {
            state_last = Software.getInstance().getState();
            if (state_curr == null) {
                Software.getInstance().setImgProcessorList(null);
                Software.getInstance().setMain_ip(null);

                state_curr = Software.getInstance().getState();
            } else {
                Software.getInstance().setState(state_curr);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        try {
            Software.getInstance().setState(state_last);
            System.out.println("close all commond undo successfully !");
        } catch (Exception e) {
            System.out.println("close all commond undo failed !");
        }
    }
}
