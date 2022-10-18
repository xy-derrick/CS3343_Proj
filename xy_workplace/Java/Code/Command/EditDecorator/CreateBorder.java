package Java.Code.Command.EditDecorator;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Java.Code.Command.Commands.EditCommand;
import Java.Code.Exception.ArgsInvalidException;
import Java.Code.Software.imgProcessor;
import Java.Code.Command.EditDecorator.CombineFilter;
import Java.Code.Software.Software;

public class CreateBorder extends EditDecorator {
	private Integer borderWidth=20;
	private Integer choice=0;
	private BufferedImage _img_;
    public CreateBorder(EditCommand wrappee,ArrayList<Object> args) throws ArgsInvalidException{
        super(wrappee);
        imgProcessor ip=super.wrappee.getIP();
        BufferedImage img=ip.getImg();
        this.borderWidth =(int)args.get(0);
        this.choice=(int)args.get(1);
        if (borderWidth>Math.min(img.getWidth(), img.getHeight())/2) {
        	throw new ArgsInvalidException("Border width can not bigger than img!");
        } else if (borderWidth<0) {
        	throw new ArgsInvalidException("Border width can not be less than 0!");
        }
        ArrayList<imgProcessor> mainIpLst=Software.getInstance().getImgProcessorList();
        if (this.choice> mainIpLst.size()-1||this.choice<0) {
        	System.out.println("Will use defult border!");
        	this._img_=getImageFromFile("C:\\Users\\Administrator\\Desktop\\border_source.jpg");
        } else {
        	this._img_=mainIpLst.get(this.choice).getImg();
        }
    }
	
    public CreateBorder(EditCommand wrappee, Integer borderWidth, Integer choice){
        super(wrappee);
        this.borderWidth=borderWidth; 
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
    	BufferedImage source=this._img_;
    	source=CombineFilter.resizeImage(source,img.getWidth(),this.borderWidth);
    	for (int j=0;j<this.borderWidth;j++) {
    		for (int i=j;i<img.getWidth()-j;i++){
    			img.setRGB(i,j,source.getRGB(i,j));
    			img.setRGB(i,img.getHeight()-j-1,source.getRGB(i,j));
    		}
    	}
    	source=CombineFilter.resizeImage(source,img.getHeight(),this.borderWidth);
    	for (int j=0;j<this.borderWidth;j++) {
    		for (int i=j;i<img.getHeight()-j;i++){
    			img.setRGB(img.getWidth()-j-1,i,source.getRGB(i,j));
    			img.setRGB(j,img.getHeight()-i-1,source.getRGB(i,j));
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
