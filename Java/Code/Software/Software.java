package Code.Software;

import java.util.ArrayList;

import Code.Command.Base.Invoker;
import Code.Command.Commands.SampleCommand;

public class Software {
    private static Software instance = new Software();
    private ArrayList<imgProcessor> imagList = new ArrayList<imgProcessor>();
    
    private Software(){
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
}
