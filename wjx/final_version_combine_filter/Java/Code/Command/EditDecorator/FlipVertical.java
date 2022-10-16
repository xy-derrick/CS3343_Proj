package Code.Command.EditDecorator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Code.Command.Commands.EditCommand;
import Code.Software.imgProcessor;
public class FlipVertical extends EditDecorator {
	protected BufferedImage imgcopy;
    public FlipVertical(EditCommand wrappee) {
        super(wrappee);

    }
    public FlipVertical(EditCommand wrappee,ArrayList<Object> args) {
        super(wrappee);

    }
    @Override
    public void execute() {
        // TODO Auto-generated method stub
        super.wrappee.execute();
    	imgProcessor ip=this.wrappee.getIP();
    	BufferedImage img=ip.getImg();
        BufferedImage nimg = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
        for (int i = 0; i < img.getWidth(); i++)
            for (int j = 0; j <img.getHeight(); j++)
            nimg.setRGB( i,img.getHeight()-1-j,img.getRGB(i, j));
        ip.setImg(nimg);

    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        super.undo();

    	System.out.println("Vertical flip has been removed!");
    }


}
