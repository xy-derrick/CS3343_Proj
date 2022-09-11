package Code.Command.Base;

import Code.Software.imgProcessor;

abstract public class Command implements CommandInterface{
    protected imgProcessor receiver;

    public Command(imgProcessor receiver)
    {
        this.receiver = receiver;
    }

    public abstract void execute();
    public abstract void undo();

}
