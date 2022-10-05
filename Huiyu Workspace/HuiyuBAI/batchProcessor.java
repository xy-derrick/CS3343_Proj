package Code.Command.Commands.HuiyuBAI;

import java.util.ArrayList;

import Code.Command.Base.Command;

public class batchProcessor implements Observable{
    private ArrayList<notifyIP> imgBuffer = new ArrayList<notifyIP>();
    private ArrayList<Command> cmdBuffer= new ArrayList<Command>();
    private ArrayList<Command> fullCmdList = new ArrayList<Command>();
    public void addElement(Command c) {
        cmdBuffer.add(c);
    }

    public void addElement(notifyIP ip) {
        imgBuffer.add(ip);
    }

    public void deleteElement(Command c) {
        cmdBuffer.remove(c);
    }

    public void deleteElement(notifyIP ip) {
        imgBuffer.remove(ip);
    }
    public void notifyAlls(){
        for(notifyIP p: imgBuffer){
            fullCmdList.addAll(p.update(cmdBuffer));
        }
    }
    public void unotifyAlls() {
        int cmdSize = fullCmdList.size();
        for(int i = cmdSize-1;i>=0;i--){
            fullCmdList.get(i).undo();
        }
    }
}
    


