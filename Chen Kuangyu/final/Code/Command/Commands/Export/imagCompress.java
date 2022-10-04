package Code.Command.Commands.Export;
import java.io.FileInputStream;

import Code.Command.Base.*;
import Code.Software.imgProcessor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class imagCompress extends Command{
    private String path;

    public imagCompress(imgProcessor receiver,String path) {
        super(receiver);
        this.path=path;
    }

    public imagCompress(imgProcessor receiver, ArrayList<Object> args) {
        super(receiver);
        this.path=(String)args.get(0);
    }
  
    public String getName(String localPath)
    {
        String fName = localPath.trim();  
        return fName.substring(fName.lastIndexOf("\\")+1); 
    }
  
 
    public void compress() throws IOException {
        /*
         * 将图片文件读入到文件流中，
         * 创建zip文件以及文件中的图片目标文件
         * 将文件流写入zip
         */
        String localPath=iProcessor.getPath();
        
        FileInputStream file = new FileInputStream(localPath);
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(path));//创建ZipoutputStream类对象

        //得到原始文件名
        String name=getName(localPath);
        out.putNextEntry(new ZipEntry(name));

        int len;
        byte[] buffer = new byte[1024]; //字节数组大小可调节
        //读取file字节流，转移到buffer字节数组中去，读取后file为空
        while ((len = file.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        } 
		//关闭压缩包打包
        out.closeEntry();
        file.close();
        out.close();
        System.out.println("Create zip file successfully!\n");


        
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
