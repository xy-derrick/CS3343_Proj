package Code.Software;

import java.util.ArrayList;

import Code.Command.Base.Invoker;
import Code.Command.Commands.SampleCommand;

public class Software {
    private static Software instance;
    private ArrayList<imgProcessor> imagList;
    private Software(){
        imagList= new ArrayList<imgProcessor>();
    }

    public ArrayList<imgProcessor> getImgList()
    {
        return imagList;
    }

    public static Software getInstance()
    {
        if(instance==null){
            instance=new Software();
        }
        return instance;
    }

    public void execute(Invoker invoker)
    {
        invoker.setCommand(new SampleCommand(instance));
        invoker.execute();
    }
}
