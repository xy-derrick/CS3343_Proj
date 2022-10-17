package Java.Code.Command.EditDecorator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Java.Code.Command.Commands.EditCommand;
import Java.Code.Exception.ArgsInvalidException;
import Java.Code.Software.imgProcessor;


public class Tailoring extends EditDecorator {
	protected BufferedImage imgcopy;
    private int x,y,w,h;
    private int xReal,yReal,wReal,hReal;




    public Tailoring(EditCommand wrappee,ArrayList<Object> args)throws ArgsInvalidException {
        super(wrappee);
        
        this.x=(int)args.get(0);
        this.y=(int)args.get(1);
        this.w=(int)args.get(2);
        this.h=(int)args.get(3);

        if ((this.x>=100)||(this.x<0)||(this.y>=100)||(this.y<0)) {
        	throw new ArgsInvalidException("a,b must be [0,100)");
        }
        if ((this.w>=100)||(this.w<0)||(this.w+this.x>100)) {
        	throw new ArgsInvalidException("c must be (0,100], and a+c<=100.Now a = "+this.x+" ,so that the range of c is (0,"+(100-this.x)+"].\n");
        }
        if ((this.h>=100)||(this.h<0)||(this.h+this.h>100)) {
        	throw new ArgsInvalidException("d must be (0,100], and b+d<=100.Now b = "+this.y+" ,so that the range of d is (0,"+(100-this.y)+"].\n");
        }
        
    }
    public Tailoring(EditCommand wrappee,Integer x,Integer y,Integer w,Integer h) {
        super(wrappee);
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
    }
    

    @Override
    public void execute(){
        // TODO Auto-generated method stub
        super.wrappee.execute();
    	imgProcessor ip=super.wrappee.getIP();
    	BufferedImage img=ip.getImg();
        xReal = (Integer)x*img.getWidth()/100;
        yReal = (Integer)y*img.getHeight()/100;
        wReal = (Integer)w*img.getWidth()/100;
        hReal = (Integer)h*img.getHeight()/100;

        ip.setImg(img.getSubimage(xReal,yReal,wReal,hReal));

            
    

    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

        super.undo();
    	System.out.println("Rotate 180 degree has been removed!");
    }

}
