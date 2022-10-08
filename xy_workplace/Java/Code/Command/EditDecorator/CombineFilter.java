package Java.Code.Command.EditDecorator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Java.Code.Command.Base.Command;
import Java.Code.Software.imgProcessor;

public class CombineFilter extends EditDecorator {
	private BufferedImage _img_; 
    public CombineFilter(Command wrappee, BufferedImage _img_) {
        super(wrappee);
        this._img_=_img_;
        
    }
    public CombineFilter(Command wrappee,ArrayList<Object> args) {
        super(wrappee);
        
    }
    // need add para here: contrast 
    private BufferedImage filter (BufferedImage img){
    	//File file = new File("C:\\Users\\Administrator\\Desktop\\R.jpg");
        //try {
		BufferedImage img2=this._img_;
		float iratio =(float)img.getHeight()/(float)img2.getHeight();
		float jratio=(float)img.getWidth()/(float)img2.getWidth();
        for (int i = 0; i < img2.getHeight(); i++) { 
			for (int j = 0; j < img2.getWidth(); j++) {  
                int pixel = img2.getRGB(j, i);
//	                int ori_pixel=img.getRGB((int)Math.round(j*jratio), (int)Math.round(i*iratio));
                img.setRGB((int)Math.round(j*jratio), (int)Math.round(i*iratio), pixel); 
			}
        }
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
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
