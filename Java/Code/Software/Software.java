package Code.Software;

import java.util.ArrayList;
import java.util.Stack;

import Code.Command.Base.Command;

public class Software {
    static private Software software = null;
    private Command command;
    private Stack<Command> undoCommand = new Stack<Command>();
    private Stack<Command> redoCommand = new Stack<Command>();
    private ArrayList<imgProcessor> imgProcessorList = new ArrayList<>();

    private Software(){

    }

    static public Software getInstance(){
        if(software==null)
        {
            return new Software();
        }
        else
        {
            return software;
        }
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void execute() {
        command.execute();
        undoCommand.add(command);

        if(!redoCommand.isEmpty())
        {
            redoCommand.clear();
        }
    }

    public void undo(){
        if(!undoCommand.empty()){
           Command toUndoCommand = undoCommand.pop();
           toUndoCommand.undo();
           redoCommand.add(toUndoCommand);
       }
    }

    public void redo(){
        if(!redoCommand.empty()){
            Command toRedocommand = redoCommand.pop();
            toRedocommand.execute();
        }
    }
    
    public ArrayList<imgProcessor> getImgProcessorList() {
        return imgProcessorList;
    }
    
    public void addImgProcessor(imgProcessor ip) {
        imgProcessorList.add(ip);
    }
}
