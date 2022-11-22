package Java.Code.Command.Export;

import Java.Code.Command.Base.Command;
import Java.Code.Command.Base.CommandCancelable;
import Java.Code.Exception.nameNotFoundException;
import Java.Code.Software.imgProcessor;

public abstract class typeTransfer extends Command implements CommandCancelable  {

	protected static int newName = 1;

	public typeTransfer(imgProcessor receiver) {
		super(receiver);
	}

	/*
	 * 总共为5种格式互相转化png，jpg，tiff，gif，bmp
	 */
	public abstract void transfer() throws nameNotFoundException;

	public String getName(String fName) {
		String name = fName.substring(fName.lastIndexOf("\\") + 1);
		return name.substring(0, name.lastIndexOf(".")==-1?name.length():name.lastIndexOf("."));
	}

	@Override
	public void execute() {
		// ignore receiverß

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
