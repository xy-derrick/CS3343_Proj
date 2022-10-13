package Code.Command.Commands.Common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import Code.Command.Base.Command;
import Code.Exception.AddressFileUnreadableException;
import Code.Software.Software;
import Code.Software.imgProcessor;
import Code.Software.ipState;

public class readImgFromLocal extends Command{
    String text = null;
    ipState state_last = null;
    ipState state_curr = null;

    public readImgFromLocal(imgProcessor receiver,String text) {
        super(receiver);
        this.text = text;
    }

    public readImgFromLocal(imgProcessor receiver,ArrayList<Object> args) {
        super(receiver);
        this.text = (String)args.get(0);
    }
    
    @Override
    public void execute() {
        // ignore receiver
        System.out.println("try to read img file from "+text);  
        try {
            state_last = Software.getInstance().getState();

            //first
            if (state_curr == null)
            {
                
                File imgFile = new File(text);
               
                if(!imgFile.exists()){throw new AddressFileUnreadableException(text);};
                
                BufferedImage bufImage = ImageIO.read(imgFile);
                iProcessor = new imgProcessor();
                iProcessor.setImg(bufImage); 
                Software.getInstance().setMain_ip(iProcessor);

                state_curr =Software.getInstance().getState();
            }
            else
            {
                Software.getInstance().setState(state_curr);
            } 
            System.out.println("successfully read img file from "+text);  
        } catch(AddressFileUnreadableException e){
        }catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Can't read such file, please check the address and file type !");  
        }
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        try{
            Software.getInstance().setState(state_last);
            System.out.println("open commond undo successfully !"); 
        }
        catch(Exception e)
        {
            System.out.println("open commond undo failed !"); 
        }
    }
}
