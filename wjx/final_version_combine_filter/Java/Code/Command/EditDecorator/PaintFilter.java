package Code.Command.EditDecorator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Code.Command.Commands.EditCommand;
import Code.Exception.ArgsInvalidException;
import Code.Software.imgProcessor;

public class PaintFilter extends EditDecorator {
	Integer degree=20;
    public PaintFilter(EditCommand wrappee,ArrayList<Object> args) throws ArgsInvalidException{
        super(wrappee);
        this.degree =(int)args.get(0);
        if (degree >100) {
        	throw new ArgsInvalidException("degree can not bigger than 100!");
        }
    }
	
    public PaintFilter(EditCommand wrappee, Integer degree){
        super(wrappee);
        this.degree=degree; 
    }
    

    
    private BufferedImage filter (BufferedImage img) {
    	for (int y = 4; y < img.getHeight()-4; y++) {
            for (int x = 4; x < img.getWidth()-4; x++) {
            	int range = (int)(Math.random()*4)+1;
            	int sub = (range +1)/2;
            	int a = (int)(Math.random()*range)+1;
            	int b = (int)(Math.random()*range)+1;
                //d[x + y * this.img.w] = this.img.data[x+a-sub + (y+b-sub) * this.img.w];   
                img.setRGB(x,y,img.getRGB(x+a-sub, y+a-sub));
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
