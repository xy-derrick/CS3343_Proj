package Code.Command.Commands.Export;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import Code.Command.Base.*;
import Code.Software.imgProcessor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;


public class imagCompress extends Command implements CommandNoncancelabe{
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
    
    public String getType(String name) {
        return name.substring(name.lastIndexOf(".") + 1);
    }

    public void compress() throws IOException {
        /*
         * 将图片文件读入到文件流中，
         * 创建zip文件以及文件中的图片目标文件
         * 将文件流写入zip
         */
        String localPath = iProcessor.getPath();
        int seq=zipSeq.getInstance().getSeq();

        // 得到原始文件名以及类型
        String name = getName(localPath);
        String type = getType(name);

        // 从bufferedimag得到二进制输入流
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(iProcessor.getImg(), type, os);
        ByteArrayInputStream file = new ByteArrayInputStream(os.toByteArray());

        //对path进行处理以防止复写
        path=path+"\\"+name.substring(0, name.lastIndexOf("."))+String.valueOf(seq)+".zip";

        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(path));// 创建ZipoutputStream类对象

        // 新增zip入口
        out.putNextEntry(new ZipEntry(name));

        int len;
        byte[] buffer = new byte[1024]; // 字节数组大小可调节
        // 读取file字节流，转移到buffer字节数组中去，读取后file为空
        while ((len = file.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        // 关闭压缩包打包
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
            zipSeq.getInstance().plus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }
}
