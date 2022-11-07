package Code.Command.Commands.HuiyuBAI;


import java.awt.image.BufferedImage;

import Code.Exception.ArgsInvalidException;



public class batchPreview {
    private static batchPreview pr = new batchPreview();
    private batchPreview(){};
    public static batchPreview getInstance(){return pr;}
    private static batchProcessor bp = batchProcessor.getInstance();
    public BufferedImage execute(){
        // TODO Auto-generated method stub
        try {

            return bp.preview();
        } catch (ArgsInvalidException e) {
            
        }
        return null;
    }
    public static Boolean checkPreviewable(){
        return bp.checkPreviewable();
    }

}
