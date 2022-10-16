package Code.Command.EditDecorator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.Image;
import Code.Command.Base.Command;
import Code.Command.Commands.EditCommand;
import Code.Exception.ArgsInvalidException;
import Code.Software.Software;
import Code.Software.imgProcessor;

public class CombineFilter extends EditDecorator {
	private BufferedImage _img_; 
    private Float transparent;
    public CombineFilter(EditCommand wrappee, Integer ipnum, Float transparent) {
        super(wrappee);     
    }
    public CombineFilter(EditCommand wrappee,ArrayList<Object> args) throws ArgsInvalidException {
        super(wrappee);
        Integer ipnum = (Integer)args.get(0);
        System.out.println(ipnum+"ipnum");
        this.transparent=(Float)args.get(1);
        System.out.println(transparent+"transparent");
        ArrayList<imgProcessor> mainIpLst=Software.getInstance().getImgProcessorList();
        if (ipnum> mainIpLst.size()-1) {
        	throw new ArgsInvalidException("ImgProcess Index is out of range!");
        }
        if ((this.transparent>100)||(this.transparent<0)) {
        	throw new ArgsInvalidException("Transparent must be [0,100]");
        }
        this._img_=mainIpLst.get(ipnum).getImg();
    }
    // need add para here: contrast 
    private BufferedImage filter (BufferedImage img) {
        Graphics2D g2d = img.createGraphics();
        Float alpha = (Float)transparent/100;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
        g2d.drawImage(resizeImage(_img_,img.getWidth(),img.getHeight()), 0, 0, img.getWidth(), img.getHeight(), null);
        g2d.dispose();
        return img;
   }
   
   public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
       Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_AREA_AVERAGING);
       BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
       outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
       return outputImage;
   }

    
    @Override
    public void execute() {
    	super.wrappee.execute();
    	imgProcessor ip=super.wrappee.getIP();
    	BufferedImage img=ip.getImg();
        ip.setImg(filter(img));
       
    }

    @Override
    public void undo() {
    	super.undo();
    }
}
