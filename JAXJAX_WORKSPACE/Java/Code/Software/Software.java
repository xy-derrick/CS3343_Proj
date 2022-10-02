package Code.Software;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import Code.Command.Base.Command;
import Code.Command.Commands.readImgFromLocal;
import Code.Command.Commands.showOperationHint;

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

    public Command switchCommand(String c,int n) {
        switch(c)
        {
            case "Common":switch(n)
            {
                case 1: return new readImgFromLocal(null, "");
                case 2: return new readImgFromLocal(null, "");
            }
            case "Filter":switch(n)
            {
                case 1: return new readImgFromLocal(null, "");
            }
            case "Edit":switch(n)
            {
                case 1: return new readImgFromLocal(null, "");
            }
            case "Export":switch(n)
            {
                case 1: return new readImgFromLocal(null, "");
            }
            case "Batch":switch(n)
            {
                case 1: return new readImgFromLocal(null, "");
            }
        }
        return command;
    }

    public void readCommand(){
        setCommand(new showOperationHint(null));
        execute();
        Scanner sc = new Scanner(System.in);
        String c = sc.next();
        int n = sc.nextInt();
        
        Command nextCommand = null;

        nextCommand = switchCommand(c, n);
        
    }
}
