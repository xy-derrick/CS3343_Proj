package Code.Command.EditDecorator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Code.Command.Commands.EditCommand;
import Code.Exception.ArgsInvalidException;
import Code.Software.imgProcessor;

public class MosaicFilter extends EditDecorator {
	private int mosaicSize=1;
	
    public MosaicFilter(EditCommand wrappee,Integer size ) throws ArgsInvalidException {
        super(wrappee);
        BufferedImage temp=super.wrappee.getIP().getImg();
        if (size > Math.min(temp.getHeight(),temp.getWidth())||size<0) {
        	throw new ArgsInvalidException("Mosaic size can not bigger than the img or less than 0!");
        }
        this.mosaicSize=size; 
    }
	
    public MosaicFilter(EditCommand wrappee,ArrayList<Object> args) {
        super(wrappee);
        this.mosaicSize=(Integer)args.get(0);
    }
	
   
    

    
    private BufferedImage filter (BufferedImage img) {
    	int pixel = img.getRGB(0, 0); 
		for (int i = 0; i < img.getHeight(); i++) { 
			for (int j = 0; j < img.getWidth(); j++) {  
                if (i%this.mosaicSize==0||j%this.mosaicSize==0) {
                	pixel = img.getRGB(j, i); 
                }
                img.setRGB(j,i,pixel);
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
