package Code.Command.Commands.Common;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import Code.Command.Base.Command;
import Code.Exception.ImgProcessorSelectedIsNullException;
import Code.Software.imgProcessor;

public class displayImg extends Command{
    MyFrame frame = null;

    public displayImg(imgProcessor receiver) {
        super(receiver);
    }

    public displayImg(imgProcessor receiver,ArrayList<Object> args) {
        super(receiver);
    }
    
    
    @Override
    public void execute() {
        // example for reading info from img
        try{
            if(iProcessor == null){throw new NullPointerException();}
            if(frame == null){frame = new MyFrame();}
            frame.setVisible(true);   
            System.out.println("Img display .........................."); 
        }
        catch(NullPointerException e)
        {
            System.out.println("No image finded in the processor !"); 
        }
 
    }
        
    @Override
    public void undo() {
        try{
            frame.setVisible(false);  
            System.out.println("display commond undo successfully !"); 
        }
        catch(Exception e)
        {
            System.out.println("display commond undo failed !"); 
        }
    }

    public class MyFrame extends JFrame {

        public static final String TITLE = "Image Processor";

        public static final int WIDTH = 900;
        public static final int HEIGHT = 750;

        public MyFrame() {
            super();
            initFrame();
        }

        private void initFrame() {
            setTitle(TITLE);
            setSize(WIDTH, HEIGHT);

            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            MyPanel panel = new MyPanel(this);
            setContentPane(panel);
        }

    }

    public class MyPanel extends JPanel {

        private MyFrame frame;

        public MyPanel(MyFrame frame) {
            super();
            this.frame = frame;
        }

        /**
         * 绘制面板的内容: 创建 JPanel 后会调用一次该方法绘制内容,
         * 之后如果数据改变需要重新绘制, 可调用 updateUI() 方法触发
         * 系统再次调用该方法绘制更新 JPanel 的内容。
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 重新调用 Graphics 的绘制方法绘制时将自动擦除旧的内容
            drawImage(g);

        }

        private void drawImage(Graphics g) {
            BufferedImage img = iProcessor.getImg(); 
            frame.setTitle("display");
            Graphics2D g2d = (Graphics2D) g.create();
            // 绘制图片（如果宽高传的不是图片原本的宽高, 则图片将会适当缩放绘制）
            g2d.drawImage(img, 50, 50, img.getWidth(this), img.getHeight(this), this);

            g2d.dispose();
        }


    }
}

