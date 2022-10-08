package Java.Code.Command.EditDecorator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Java.Code.Command.Base.Command;
import Java.Code.Software.imgProcessor;

public class VintageFilter extends EditDecorator {
	 
	public VintageFilter(Command wrappee) {
		super(wrappee);
	}
	public VintageFilter(Command wrappee,ArrayList<Object> args) {
		super(wrappee);
	}
	
	private  int clamp(int c)  {  
		return c > 255 ? 255 :( (c < 0) ? 0: c);  
	} 
	 
	private double noise() {  
		return Math.random()*0.5 + 0.5;  
	}  
	 
	private double colorBlend(double scale, double dest, double src) {  
		return (scale * dest + (1.0 - scale) * src);  
	} 
	 
	private BufferedImage filter(BufferedImage img) {
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
	        	int c=img.getRGB(x, y);
	            int ta = (c >> 24) & 0xff; 
	            int tr = (c >> 16) & 0xFF;
	            int tg = (c >> 8) & 0xFF;
	            int tb = (c >> 0) & 0xFF;
	            int fr = (int)colorBlend(noise(), (tr * 0.393) + (tg * 0.769) + (tb * 0.189), tr);  
	            int fg = (int)colorBlend(noise(), (tr * 0.349) + (tg * 0.686) + (tb * 0.168), tg);  
	            int fb = (int)colorBlend(noise(), (tr * 0.272) + (tg * 0.534) + (tb * 0.131), tb);  
	            int rgb=(ta << 24) | (clamp(fr) << 16) | (clamp(fg) << 8) | clamp(fb);
	            img.setRGB(x, y, rgb);              
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
