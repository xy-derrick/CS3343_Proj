package Java.Code.Batch;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Java.Code.Command.Commands.Common.readImgFromLocal;
import Java.Code.Software.Software;

public class test {

    public static void main(String[] args) {
        Software mainSoftware = Software.getInstance();
        //loadImg
        ArrayList<String> imgs = new ArrayList<>();
        imgs.add("Raiden.png");
        imgs.add("HuTao.png");
        imgs.add("ZhongLi.png");
        imgs.add("Violet.png");
        imgs.add("Nahida.png");

        for(String imgPath: imgs){
            mainSoftware.setCommand(new readImgFromLocal(null,imgPath,"title"));
            mainSoftware.execute();
        }

       


        //batch add cmd
        ArrayList<Object>batchCommands = new ArrayList<>();
        batchCommands.add(new transCommand("Gray", null));

        batchCommands.add(new transCommand("FlipVertical", null));

        ArrayList<Object> tailorPara= new ArrayList<>();
        tailorPara.add(20);
        tailorPara.add(30);
        tailorPara.add(60);
        tailorPara.add(10);
        batchCommands.add(new transCommand("Tailor", tailorPara));

        ArrayList<Object> painter = new ArrayList<>();
        painter.add(40);
        batchCommands.add(new transCommand("Paint", painter));

        ArrayList<Object> localSave = new ArrayList<>();
        localSave.add("imgResult");
        batchCommands.add(new transCommand("localSave", localSave));


        mainSoftware.setCommand(new batchAdd(null,batchCommands));
        mainSoftware.execute();

        //batch add img
        ArrayList<Object>batchImgs = new ArrayList<>();
        batchImgs.add(2);
        batchImgs.add(0);
        batchImgs.add(1);
      

        mainSoftware.setCommand(new batchAdd(null,batchImgs));
        mainSoftware.execute();

        //batch ExImgHaveExist
        // batchImgs.clear();
        // batchImgs.add(2);
        // mainSoftware.setCommand(new batchAdd(null,batchReader.getInstance().parse(batchImgs)));
        // mainSoftware.execute();

        // batch delete img
        batchImgs.clear();
        batchImgs.add(2);
        mainSoftware.setCommand(new batchDelete(null, batchImgs));
        mainSoftware.execute();

        // //batch ExImgNotExist
        // batchImgs.clear();
        // batchImgs.add(2);
        // mainSoftware.setCommand(new batchDelete(null, batchImgs));
        // mainSoftware.execute();

        //batch delete cmd
        batchCommands = new ArrayList<>();
        batchCommands.add(new transCommand("Tailor", tailorPara));
        mainSoftware.setCommand(new batchDelete(null, batchCommands));
        mainSoftware.execute();

        //batch preview 
        if(batchPreview.checkPreviewable()) { //由于export的命令不会对图片本身造成影响，故提供checkPreviewable(), 
            //若返回false代表batch中的command均为export,没有预览的必要。在GUI中可以设置preview按钮为disable
            BufferedImage previewImg = batchPreview.getInstance().execute();
            //previewImg为提供的img
        }  
        //batch exec
        mainSoftware.setCommand(new batchExecute(null));
        mainSoftware.execute();

         //batch ExCommandNotExist
        //  batchCommands = new ArrayList<>();
        //  batchCommands.add(new transCommand("Tailor", tailorPara));
        //  mainSoftware.setCommand(new batchDelete(null, batchCommands));
        //  mainSoftware.execute();

        // batch ExNoBatchInput
        // mainSoftware.setCommand(new batchDelete(null, new ArrayList<>()));

        // mainSoftware.setCommand(new batchExecute(null, null));
        // mainSoftware.execute();

        ArrayList<BufferedImage> imgList = batchProcessor.getInstance().getBatchResult();


        
        
    }
}
