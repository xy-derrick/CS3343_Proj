package Code.Command.EditDecorator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Code.Command.Commands.EditCommand;
import Code.Software.imgProcessor;

public class Rotate90DegreesCounterclockwise extends EditDecorator {
	protected BufferedImage imgcopy;
    public Rotate90DegreesCounterclockwise(EditCommand wrappee) {
        super(wrappee);

    }
    public Rotate90DegreesCounterclockwise(EditCommand wrappee,ArrayList<Object> args) {
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
            nimg.setRGB(j, img.getWidth()-i-1,img.getRGB(i, j));
        ip.setImg(nimg);

    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        super.undo();

    	System.out.println("Rotate 90 degree counterclockwise has been removed!");
    }
}
