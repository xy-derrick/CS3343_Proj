package Code.Command.EditDecorator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Code.Command.Commands.EditCommand;
import Code.Command.Base.Command;
import Code.Software.imgProcessor;
public class Rotate90DegreesClockwise extends EditDecorator {
	protected BufferedImage imgcopy;
    public Rotate90DegreesClockwise(EditCommand wrappee) {
        super(wrappee);

    }
    public Rotate90DegreesClockwise(EditCommand wrappee,ArrayList<Object> args) {
        super(wrappee);

    }
    @Override
    public void execute() {
        // TODO Auto-generated method stub
        super.wrappee.execute();
    	imgProcessor ip=this.wrappee.getIP();
        BufferedImage img=ip.getImg();
        BufferedImage nimg = new BufferedImage(img.getHeight(),img.getWidth(),img.getType());
        for (int i = 0; i < img.getWidth(); i++)
			for (int j = 0; j < img.getHeight(); j++)
            nimg.setRGB(img.getHeight()-j-1,i, img.getRGB(i,j));
        ip.setImg(nimg);

    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        super.undo();
    	System.out.println("Rotate 90 degree clockwise has been removed!");
    }
    
}
