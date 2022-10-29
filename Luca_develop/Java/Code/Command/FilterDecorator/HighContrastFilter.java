package Code.Command.FilterDecorator;

import java.awt.Color;
import java.awt.image.BufferedImage;
import Code.Command.Base.Command;
import Code.Software.imgProcessor;

public class HighContrastFilter extends FilterDecorator {
	
    public HighContrastFilter(Command wrappee) {
        super(wrappee);
    }
    // need add para here: contrast 
    private BufferedImage filter (BufferedImage img) {
    	int contrast_average = 128;
    	int contrast=100000;
    	int pix;
		for (int i = 0; i < img.getHeight(); i++) { 
			for (int j = 0; j < img.getWidth(); j++) {  
                int pixel = img.getRGB(j, i); 
                Color color = new Color(pixel);
                if (color.getRed() < contrast_average) {
                    pix = color.getRed()- Math.abs(contrast);
                    if (pix < 0) pix = 0;
                }
                else {
                    pix = color.getRed() + Math.abs(contrast);
                    if (pix > 255) pix = 255;
                }
                int red= pix;
                if (color.getGreen() < contrast_average) {
                    pix = color.getGreen()- Math.abs(contrast);
                    if (pix < 0) pix = 0;
                }
                else
                {
                    pix = color.getGreen() + Math.abs(contrast);
                    if (pix > 255) pix = 255;
                }
                int green= pix;   
                if (color.getBlue() < contrast_average) {
                    pix = color.getBlue()- Math.abs(contrast);
                    if (pix < 0) pix = 0;
                }
                else {
                    pix = color.getBlue() + Math.abs(contrast);
                    if (pix > 255) pix = 255;
                }
                int blue= pix;  
                color = new Color(red,green,blue);
                int x=color.getRGB();
                img.setRGB(j,i,x);
			}
    	}
    	return img;
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
