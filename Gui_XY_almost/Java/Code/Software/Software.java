package Java.Code.Software;

import java.util.ArrayList;
import java.util.Stack;

import Java.Code.Batch.notifyIP;
import Java.Code.Command.Base.Command;
import Java.Code.Command.Base.CommandNoncancelabe;
import Java.Code.Software.imgProcessor;

public class Software {
	static private Software software = null;
	private Command command;
	private Stack<Command> undoCommand = new Stack<Command>();
	private Stack<Command> redoCommand = new Stack<Command>();
	private ArrayList<imgProcessor> imgProcessorList = new ArrayList<>();
	private imgProcessor main_ip = null;
	ipState state = null;

	private Software() {

	}

	static public Software getInstance() {
		if (software == null) {
			software = new Software();
			return software;
		} else {
			return software;
		}
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public void execute() {
		command.execute();
		if (!(command instanceof CommandNoncancelabe || command.getIP() instanceof notifyIP)) {
			undoCommand.add(command);
			if (!redoCommand.isEmpty()) {
				redoCommand.clear();
			}
		}
	}

	public void undo() {
		if (!undoCommand.empty()) {
			Command toUndoCommand = undoCommand.pop();
			if (toUndoCommand instanceof CommandNoncancelabe) {
				undo();
			} else {
				toUndoCommand.undo();
				redoCommand.add(toUndoCommand);
			}
		} else {
			System.out.println("nothing to undo !");
		}
	}

	public void redo() {
		if (!redoCommand.empty()) {
			Command toRedocommand = redoCommand.pop();
			toRedocommand.execute();
		} else {
			System.out.println("nothing to redo !");
		}
	}

	public ArrayList<imgProcessor> getImgProcessorList() {
		return imgProcessorList;
	}

	public void setImgProcessorList(ArrayList<imgProcessor> imgProcessorList) {
		this.imgProcessorList = imgProcessorList;
	}

	public void addImgProcessor(imgProcessor ip) {
		imgProcessorList.add(ip);
	}

	public imgProcessor getMain_ip() {
		return main_ip;
	}

	public void setMain_ip(imgProcessor main_ip) {
		this.main_ip = main_ip;
	}

	public ipState getState() {
		state = new ipState(imgProcessorList, main_ip);
		return state;
	}

	public void setState(ipState state) {
		this.imgProcessorList = state.getImgProcessorList();
		this.main_ip = state.getMain_ip();
	}

	public Stack<Command> getUndoCommand() {
		return undoCommand;
	}

	public Stack<Command> getRedoCommand() {
		return redoCommand;
	}
}
