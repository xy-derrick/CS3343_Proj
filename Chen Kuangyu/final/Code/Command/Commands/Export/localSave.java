package Code.Command.Commands.Export;
import java.io.File;


import Code.Command.Base.*;
import Code.Software.imgProcessor;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class localSave extends Command implements CommandNoncancelabe{
    private String path;
    private static int newName=1;

    public localSave(imgProcessor receiver,String path) {
        super(receiver);
        this.path=path;
    }

    public localSave(imgProcessor receiver, ArrayList<Object> args) {
        super(receiver);
        this.path=(String)args.get(0);
    }
  
    public String getName(String localPath)
    {
        String fName = localPath.trim();  
        return fName.substring(fName.lastIndexOf("\\")+1); 
    }

    public String getType(String name)
    {
        return name.substring(name.lastIndexOf(".")+1);
    }
  
 
    public void save() throws IOException {
        /*
         *当前图片存入本地
         */

        //得到原始文件名
        String localPath=iProcessor.getPath();
        String name=getName(localPath);

        //存入本地
        String type=getType(name);
        File outputfile = new File(path+"\\"+String.valueOf(newName)+name);
        ImageIO.write(iProcessor.getImg(), type, outputfile);

        System.out.println("Save to local successfully!\n");

        
    }



    
    @Override
    public void execute() {
        // ignore receiver
        try {
            save();
            newName++;
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
