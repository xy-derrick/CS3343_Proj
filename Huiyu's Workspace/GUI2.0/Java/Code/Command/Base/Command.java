package Code.Command.Base;


import Code.Software.imgProcessor;

abstract public class Command implements CommandInterface{
    protected imgProcessor iProcessor;

    public Command (imgProcessor receiver)
    {
        this.iProcessor = receiver;
    }

    public abstract void execute();
    public abstract void undo() ;
    
    public void setIP(imgProcessor ip){
        this.iProcessor = ip;
    }
    public imgProcessor getIP() {
    	return this.iProcessor;
    }


}
