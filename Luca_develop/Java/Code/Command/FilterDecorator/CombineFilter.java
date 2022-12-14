package Code.Command.FilterDecorator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Code.Command.Base.Command;
import Code.Software.imgProcessor;

public class CombineFilter extends FilterDecorator {
	
    public CombineFilter(Command wrappee) {
        super(wrappee);
    }
    // need add para here: contrast 
    private BufferedImage filter (BufferedImage img){
    	File file = new File("C:\\Users\\Administrator\\Desktop\\R.jpg");
        try {
			BufferedImage img2=(BufferedImage)ImageIO.read(file);
			float iratio =(float)img.getHeight()/(float)img2.getHeight();
			float jratio=(float)img.getWidth()/(float)img2.getWidth();
	        for (int i = 0; i < img2.getHeight(); i++) { 
				for (int j = 0; j < img2.getWidth(); j++) {  
	                int pixel = img2.getRGB(j, i);
//	                int ori_pixel=img.getRGB((int)Math.round(j*jratio), (int)Math.round(i*iratio));
	                img.setRGB((int)Math.round(j*jratio), (int)Math.round(i*iratio), pixel); 
				}
	        }
		} catch (IOException e) {
			e.printStackTrace();
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
