package Java.Code.Command.EditDecorator;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Java.Code.Command.Commands.EditCommand;
import Java.Code.Exception.ArgsInvalidException;
import Java.Code.Software.imgProcessor;

public class PaintFilter extends EditDecorator {
	Integer degree=20;
    public PaintFilter(EditCommand wrappee,ArrayList<Object> args) {
        super(wrappee);
    }
	
    public PaintFilter(EditCommand wrappee, Integer degree) throws ArgsInvalidException {
        super(wrappee);
        if (degree >100) {
        	throw new ArgsInvalidException("degree can not bigger than 100!");
        }
        this.degree=degree; 
    }
    
    private BufferedImage getImageFromFile(String path){
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi;
    }
    
    private BufferedImage filter (BufferedImage img) {
    	for (int y = this.degree; y < img.getHeight()-this.degree; y++) {
            for (int x = this.degree; x < img.getWidth()-this.degree; x++) {
            	int range = (int)(Math.random()*this.degree)+1;
            	int sub = (range +1)/2;
            	int a = (int)(Math.random()*range)+1;
            	int b = (int)(Math.random()*range)+1;
                img.setRGB(x,y,img.getRGB(x+a-sub, y+a-sub));
            }
        }
    	BufferedImage top= getImageFromFile("C:\\Users\\Administrator\\Desktop\\border_t.jpg");
    	top=CombineFilter.resizeImage(top,img.getWidth(),this.degree);
    	BufferedImage bot= getImageFromFile("C:\\Users\\Administrator\\Desktop\\border_b.jpg");
    	bot=CombineFilter.resizeImage(bot,img.getWidth(),this.degree);
    	BufferedImage left= getImageFromFile("C:\\Users\\Administrator\\Desktop\\border_l.jpg");
    	left=CombineFilter.resizeImage(left,this.degree,img.getHeight());
    	BufferedImage right= getImageFromFile("C:\\Users\\Administrator\\Desktop\\border_r.jpg");
    	right=CombineFilter.resizeImage(right,this.degree,img.getHeight());
  
    	for (int i=0;i<img.getWidth();i++) {
    		for (int j=0;j<this.degree;j++) {
    			img.setRGB(i,j,top.getRGB(i, j));
    		}
    	}
    	for (int i=0;i<img.getWidth();i++) {
    		for (int j=img.getHeight()-this.degree;j<this.degree;j++) {
    			img.setRGB(i,j,bot.getRGB(i, j-img.getHeight()+this.degree));
    		}
    	}
    	for (int i=0;i<this.degree;i++) {
    		for (int j=this.degree;j<img.getHeight()-this.degree;j++) {
    			img.setRGB(i,j,left.getRGB(i, j-this.degree));
    		}
    	}
    	for (int i=img.getWidth()-this.degree;i<img.getWidth();i++) {
    		for (int j=this.degree;j<img.getHeight()-this.degree;j++) {
    			img.setRGB(i,j,right.getRGB(i-img.getWidth()+this.degree, j-this.degree));
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
