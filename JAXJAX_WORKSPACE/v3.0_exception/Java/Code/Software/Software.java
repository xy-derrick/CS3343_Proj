package Code.Software;

import java.util.ArrayList;
import java.util.Stack;
import Code.Command.Base.Command;
import Code.Command.Base.CommandNoncancelabe;
import Code.Exception.NoCommandToRedoException;
import Code.Exception.NoCommandToUndoException;

public class Software {
    static private Software software = null;
    private Command command;
    private Stack<Command> undoCommand = new Stack<Command>();
    private Stack<Command> redoCommand = new Stack<Command>();
    private ArrayList<imgProcessor> imgProcessorList = new ArrayList<>();
    private imgProcessor main_ip = null;
    ipState state = null;

    private Software(){

    }

    static public Software getInstance(){
        if(software==null)
        {
            software = new Software();
            return software;
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
        if(!(command instanceof CommandNoncancelabe))
        {
            undoCommand.add(command);
            if(!redoCommand.isEmpty())
            {
                redoCommand.clear();
            }
        }
    }

    public void undo() throws NoCommandToUndoException{
        if(!undoCommand.empty()){
           Command toUndoCommand = undoCommand.pop();
           if(toUndoCommand instanceof CommandNoncancelabe)
           {
            undo();
           }
           else
           {
            toUndoCommand.undo();
            redoCommand.add(toUndoCommand);
           }
       }
       else
       {
        throw new NoCommandToUndoException();
       }
    }

    public void redo() throws NoCommandToRedoException{
        if(!redoCommand.empty()){
            Command toRedocommand = redoCommand.pop();
            toRedocommand.execute();
        }
        else
        {
            throw new NoCommandToRedoException();
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
