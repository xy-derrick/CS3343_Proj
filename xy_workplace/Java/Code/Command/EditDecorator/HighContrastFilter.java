package Java.Code.Command.EditDecorator;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Java.Code.Command.Base.Command;
import Java.Code.Software.imgProcessor;

public class HighContrastFilter extends EditDecorator {
	private int contrast;
    public HighContrastFilter(Command wrappee,int contrast ) {
        super(wrappee);
        this.contrast=contrast;
    }
    public HighContrastFilter(Command wrappee) {
        super(wrappee);
        this.contrast=1000000;
    }
    public HighContrastFilter(Command wrappee,ArrayList<Object> args) {
        super(wrappee);
        this.contrast=(Integer)args.get(0);
    }
    // need add para here: contrast 
    private BufferedImage filter (BufferedImage img) {
    	int contrast_average = 128;
    	int pix;
		for (int i = 0; i < img.getHeight(); i++) { 
			for (int j = 0; j < img.getWidth(); j++) {  
                int pixel = img.getRGB(j, i); 
                Color color = new Color(pixel);
                if (color.getRed() < contrast_average) {
                    pix = color.getRed()- Math.abs(this.contrast);
                    if (pix < 0) pix = 0;
                }
                else {
                    pix = color.getRed() + Math.abs(this.contrast);
                    if (pix > 255) pix = 255;
                }
                int red= pix;
                if (color.getGreen() < contrast_average) {
                    pix = color.getGreen()- Math.abs(this.contrast);
                    if (pix < 0) pix = 0;
                }
                else
                {
                    pix = color.getGreen() + Math.abs(this.contrast);
                    if (pix > 255) pix = 255;
                }
                int green= pix;   
                if (color.getBlue() < contrast_average) {
                    pix = color.getBlue()- Math.abs(this.contrast);
                    if (pix < 0) pix = 0;
                }
                else {
                    pix = color.getBlue() + Math.abs(this.contrast);
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
