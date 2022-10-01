package Code.Command.Commands.Export;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import Code.Command.Base.*;
import Code.Software.imgProcessor;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class imagCompress extends Command{
    private String path;

    public imagCompress(imgProcessor receiver,String path) {
        super(receiver);
        this.path=path;
    }

    public InputStream getImageStream(BufferedImage bimage){
        InputStream is = null;
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ImageOutputStream imOut;
        try {
        imOut = ImageIO.createImageOutputStream(bs);
        ImageIO.write(bimage, "png",imOut);
        is= new ByteArrayInputStream(bs.toByteArray());
        } catch (IOException e) {
        e.printStackTrace();
        }
        return is;
        }
 
    public void compress() throws IOException {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(path));//创建ZipoutputStream类对象
        zip(out,getImageStream(iProcessor.getImg()),"");
        out.close();//close

        
    }

    private void zip(ZipOutputStream out,InputStream in,String base) throws IOException {//方法重载
       

        out.putNextEntry(new ZipEntry(base));//创建新的进入点
        int b;
        System.out.println(base);
        while ((b=in.read())!=-1){//如果没有达到流的尾部
            out.write(b);//将字节写入当前ZIP条目
        }
        in.close();//close
    }



    
    @Override
    public void execute() {
        // ignore receiver
        try {
            compress();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
    
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }
}
