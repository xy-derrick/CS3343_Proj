package Code.Software;

import java.util.Stack;

import Code.Command.Base.Command;

public class imgProcessor {
    private Object img = null;
    private Stack<Command> undoCommand = new Stack<Command>();
    private Stack<Command> redoCommand = new Stack<Command>();

    public imgProcessor()
    {
        Code.Software.Software.getInstance().getImgList().add(this);
    }

    public Object getImg()
    {
        return img;
    }

    public Object setImg()
    {
        return img;
    }

    public void putIntoUndoList(Command command)
    {
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
