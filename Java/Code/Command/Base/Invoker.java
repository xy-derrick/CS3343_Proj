package Code.Command.Base;

import java.util.Stack;

public class Invoker {
    private Command command;
    private Stack<Command> undoCommand = new Stack<Command>();
    private Stack<Command> redoCommand = new Stack<Command>();

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
    
}
