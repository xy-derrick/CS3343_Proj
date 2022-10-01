package Code.Command.Commands.Export;
import java.io.FileInputStream;

import Code.Command.Base.*;
import Code.Software.imgProcessor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class imagCompress extends Command{
    private String path;

    public imagCompress(imgProcessor receiver,String path) {
        super(receiver);
        this.path=path;
    }

  
 
    public void compress() throws IOException {
        FileInputStream file = new FileInputStream("C:\\Users\\chenkuangyu\\Desktop\\屏幕截图 2022-10-01 132127 - 副本.jpg");
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(path));//创建ZipoutputStream类对象
        
        out.putNextEntry(new ZipEntry("test.jpg"));

        int len;
        byte[] buffer = new byte[1024]; //字节数组大小可调节
        //读取fis字节流，转移到buffer字节数组中去，读取后fis为空
        while ((len = file.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        } 
		//关闭压缩包打包
        out.closeEntry();
        file.close();
        out.close();


        
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
