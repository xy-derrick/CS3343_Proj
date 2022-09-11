package Code.Command.Base;

import Code.Software.Software;

abstract public class Command implements CommandInterface{
    protected Software receiver;

    public Command(Software receiver)
    {
        this.receiver = receiver;
    }

    public abstract void execute();
    public abstract void undo();

}
