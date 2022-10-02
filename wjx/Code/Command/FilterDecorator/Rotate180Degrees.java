package Code.Command.FilterDecorator;

import java.awt.image.BufferedImage;
import Code.Command.Base.Command;
import Code.Software.imgProcessor;


public class Rotate180Degrees extends FilterDecorator {
	protected BufferedImage imgcopy;
    public Rotate180Degrees(Command wrappee) {
        super(wrappee);
        // TODO Auto-generated constructor stub
        this.imgcopy=this.wrappee.getIP().getImg();
    }
    @Override
    public void execute() {
        // TODO Auto-generated method stub
    	imgProcessor ip=this.wrappee.getIP();
        BufferedImage img=ip.getImg();
        BufferedImage nimg = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
        for (int i = 0; i <img.getWidth(); i++)
            for (int j = 0; j < img.getHeight(); j++)
                nimg.setRGB( img.getWidth() - i-1,img.getHeight()-j-1, img.getRGB(i, j));
        ip.setImg(nimg);

    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        imgProcessor ip=this.wrappee.getIP();
    	ip.setImg(this.imgcopy);
    	System.out.println("Rotate 180 degree has been removed!");
    }

}
