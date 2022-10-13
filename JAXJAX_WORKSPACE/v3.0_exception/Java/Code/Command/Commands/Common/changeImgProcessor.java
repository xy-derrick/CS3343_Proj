package Code.Command.Commands.Common;
import java.util.ArrayList;
import Code.Command.Base.Command;
import Code.Exception.ImgProcessorNotFindInListException;
import Code.Software.Software;
import Code.Software.imgProcessor;
import Code.Software.ipState;

public class changeImgProcessor extends Command{
    Integer num = null;
    ipState state_last = null;
    ipState state_curr = null;

    public changeImgProcessor(imgProcessor receiver,Integer num) {
        super(receiver);
        this.num = num ;
    }

    public changeImgProcessor(imgProcessor receiver,ArrayList<Object> args) {
        super(receiver);
        this.num = (Integer)args.get(0);
    }
    
    @Override
    public void execute() {
        // ignore receiver
        
        try {
            state_last = Software.getInstance().getState();
            if(state_curr  == null)
            {
                if(Software.getInstance().getImgProcessorList()==null || Software.getInstance().getImgProcessorList().indexOf(num)==-1){throw new ImgProcessorNotFindInListException((int)num);};
                imgProcessor order = Software.getInstance().getImgProcessorList().get(num);
                Software.getInstance().setMain_ip(order);
                System.out.println("image processor now change to "+num+"-image processor");  

                state_curr =Software.getInstance().getState();
            }
            else
            {
                Software.getInstance().setState(state_curr);
            }
            
        } catch (ImgProcessorNotFindInListException e) {
            // TODO Auto-generated catch block
        }
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        try {
            Software.getInstance().setState(state_last);
            System.out.println("change commond undo successfully !");  
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("change commond undo failed !"); 
        }
    }
}
