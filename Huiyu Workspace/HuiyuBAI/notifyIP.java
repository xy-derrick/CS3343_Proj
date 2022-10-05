package Code.Command.Commands.HuiyuBAI;

import java.util.ArrayList;

import Code.Command.Base.Command;
import Code.Software.imgProcessor;

public class notifyIP extends imgProcessor implements Observer{

    public notifyIP(imgProcessor ip){
        setImg(ip.getImg());
    }
    public ArrayList<Command> update(ArrayList<Command> cmdBuffer) {
        for(Command i:cmdBuffer){
            i.setIP(this);
        }
        return cmdBuffer;
    }
}
    



