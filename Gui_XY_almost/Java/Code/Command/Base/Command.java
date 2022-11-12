package Java.Code.Command.Base;

import Java.Code.Software.imgProcessor;

abstract public class Command implements CommandInterface {
	protected imgProcessor iProcessor;

	public Command(imgProcessor receiver) {
		this.iProcessor = receiver;
	}

	public abstract void execute();

	public abstract void undo();

	public imgProcessor getIP() {
		return this.iProcessor;
	}

	public void setIP(imgProcessor ip) {
		this.iProcessor = ip;
	}

}
