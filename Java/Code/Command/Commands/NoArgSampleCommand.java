package Code.Command.Commands;

import Code.Command.Base.Command;
import Code.Software.Software;
import Code.Software.imgProcessor;

public class NoArgSampleCommand extends Command{

    public NoArgSampleCommand(imgProcessor receiver) {
        super(receiver);
    }
    
    @Override
    public void execute() {
        // example for reading info from img
        for(imgProcessor i: Software.getInstance().getImgList())
        {
            i.getImg();
        }
        // iProcessor.getSize 图片基本信息
        //receiver.lightDegree 自定义信息
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }
}
