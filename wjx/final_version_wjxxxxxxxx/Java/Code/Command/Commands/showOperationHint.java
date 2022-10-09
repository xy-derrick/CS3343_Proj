package Code.Command.Commands;

import Code.Command.Base.Command;
import Code.Command.Base.CommandNoncancelabe;
import Code.Software.Software;
import Code.Software.imgProcessor;

public class showOperationHint extends Command implements CommandNoncancelabe{

    public showOperationHint(imgProcessor receiver) {
        super(receiver);
    }
    
    @Override
    public void execute() {
        String ipList = "";
        String ipMain = "";
        if(Software.getInstance().getImgProcessorList()!=null)
        {
            for(var ip : Software.getInstance().getImgProcessorList())
            {
                int id = Software.getInstance().getImgProcessorList().indexOf(ip);
                boolean hasImg= (ip.getImg()!=null);
                if(ipList==null){ipList=" id-"+id+":"+(hasImg?"Image ":"None ");}
                else{ipList += " id-"+id+":"+(hasImg?"Image ":"None ");}  
            };
        };

        ipMain = (Software.getInstance().getMain_ip()==null)?"":("id-"+Software.getInstance().getImgProcessorList().indexOf(Software.getInstance().getMain_ip())+" processor");
       
        System.out.println(
                            "\nimage processor list : ["+ipList+"]\n"+
                            "selected image processor : ["+ipMain+"]\n"+
                            "undo commond list : ["+Software.getInstance().getUndoCommand().size()+"]  redo commond list : ["+Software.getInstance().getRedoCommand().size()+"]\n"+
                            "common: 1.open 2.change 3.undo 4.redo 5.close 6.closeAll 7.display 8.info 9.exist\n"+
                            "edit: 1.flipHorizontal 2.flipVertical 3.rotate90DegreesClockwise 4.rotate90DegreesCounterclockwise\n"+
                                    "5.rotate180Degrees 6.tailoring 7.zoom\n"+
                            "export:\n"+
                            "filter: 1.grayFilter 2.highContrastFilter 3.vintageFilter 4.anticolor 5.combineFilter\n"+
                            "Command format should be 'title +(space) + number', like 'common 1' \n\n"
                            );
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        System.out.println("nothing to undo");
    }
}
