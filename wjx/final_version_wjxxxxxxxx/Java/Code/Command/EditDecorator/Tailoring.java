package Code.Command.EditDecorator;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Code.Command.Commands.EditCommand;
import Code.Command.Base.Command;
import Code.Software.imgProcessor;


public class Tailoring extends EditDecorator {
	protected BufferedImage imgcopy;
    private int x,y,w,h;
    private int xReal,yReal,wReal,hReal;




    public Tailoring(EditCommand wrappee,ArrayList<Object> args) {
        super(wrappee);
        this.x=(int)args.get(0);
        this.y=(int)args.get(1);
        this.w=(int)args.get(2);
        this.h=(int)args.get(3);
    }
    public Tailoring(EditCommand wrappee,Integer x,Integer y,Integer w,Integer h) {
        super(wrappee);
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
    }
    

    //public int findError(int x,int y,int w,int h) throws OutOfRangeException, NegtiveInputException, CropOutOfPictureSizeException{
    //    e = 0;
    //    if (x>=imgcopy.getWidth()||y>=imgcopy.getWidth())
    //        throw new OutOfRangeException();
    //    else if (w<0||h<0)
    //        throw new NegtiveInputException();
    //    else if (x+w>=imgcopy.getHeight()||y+h>=imgcopy.getHeight())
    ///        throw new CropOutOfPictureSizeException();
    //    else{
    //        e =1;
    //    }
    //    return e;     
    //}

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
        System.out.println("Xreal:"+xReal+"Yreal:"+yReal+"Wreal:"+wReal+"Hreal:"+hReal);
        ip.setImg(img.getSubimage(xReal,yReal,wReal,hReal));

            
    

    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

        super.undo();
    	System.out.println("Rotate 180 degree has been removed!");
    }

}
