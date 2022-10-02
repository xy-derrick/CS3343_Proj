package Code.Command.FilterDecorator;

import java.awt.Color;
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
    	File file = new File("C:\\Users\\Administrator\\Desktop\\desktop2.jpg");
        try {
			BufferedImage img2=(BufferedImage)ImageIO.read(file);
			int height=img.getHeight()<img2.getHeight()?img.getHeight():img2.getHeight();
			int width=img.getWidth()<img2.getWidth()?img.getWidth():img2.getWidth();
	        for (int i = 0; i < height; i=i+2) { 
				for (int j = 0; j < width; j=j+2) {  
	                int pixel = img2.getRGB(j, i);
	                img.setRGB(j, i, pixel); 
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
