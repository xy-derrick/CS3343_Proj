package Code.Command.Commands.HuiyuBAI;
import java.util.ArrayList;

import Code.Command.Base.Command;

public interface Observer{
    public ArrayList<Command> update(ArrayList<Command> cmdBuffer);
}