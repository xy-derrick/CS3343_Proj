package Java.Gui;
//
//import java.awt.*;
//import javax.swing.*;
//
//public class guiMain extends JFrame {
//    public static void main(String args[]) {
//        guiMain demo = new guiMain();
//    }
//
//    public guiMain() {
//        init();
//        this.setSize(1000, 600);
//        this.setVisible(true);
//    }
//    JButton j1;
//    JButton j2;
//    JButton j3;
//    JPanel j4;
//    JComboBox j5;
//    JTextField j6;
//    JButton j7;
//    JList j8;
//    JTextArea j9;
//    public void init() {
//        j1 = new JButton("打开");
//        j2 = new JButton("保存");
//        j3 = new JButton("另存为");
//        j4 = new JPanel();
//        String[] str = { "java笔记", "C#笔记", "HTML5笔记" };
//        j5 = new JComboBox(str);
//        j6 = new JTextField();
//        j7 = new JButton("清空");
//        j8 = new JList(str);
//        j9 = new JTextArea();
//        j9.setBackground(Color.PINK);// 为了看出效果，设置了颜色
//        GridBagLayout layout = new GridBagLayout();
//        this.setLayout(layout);
//        this.add(j1);// 把组件添加进jframe
//        this.add(j2);
//        this.add(j3);
//        this.add(j4);
//        this.add(j5);
//        this.add(j6);
//        this.add(j7);
//        this.add(j8);
//        this.add(j9);
//        GridBagConstraints s = new GridBagConstraints();// 定义一个GridBagConstraints，
//        // 是用来控制添加进的组件的显示位置
//        s.fill = GridBagConstraints.BOTH;
//        // 该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
//        // NONE：不调整组件大小。
//        // HORIZONTAL：加宽组件，使它在水平方向上填满其显示区域，但是不改变高度。
//        // VERTICAL：加高组件，使它在垂直方向上填满其显示区域，但是不改变宽度。
//        // BOTH：使组件完全填满其显示区域。
//        s.gridwidth = 1;// 该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
//        s.weightx = 0;// 该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
//        s.weighty = 0;// 该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
//        layout.setConstraints(j1, s);// 设置组件
//        s.gridwidth = 1;
//        s.weightx = 0;
//        s.weighty = 0;
//        layout.setConstraints(j2, s);
//        s.gridwidth = 1;
//        s.weightx = 0;
//        s.weighty = 0;
//        layout.setConstraints(j3, s);
//        s.gridwidth = 0;// 该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
//        s.weightx = 0;// 不能为1，j4是占了4个格，并且可以横向拉伸，
//        // 但是如果为1，后面行的列的格也会跟着拉伸,导致j7所在的列也可以拉伸
//        // 所以应该是跟着j6进行拉伸
//        s.weighty = 0;
//        layout.setConstraints(j4, s);
//        s.gridwidth = 2;
//        s.weightx = 0;
//        s.weighty = 0;
//        layout.setConstraints(j5, s);
//        ;
//        s.gridwidth = 4;
//        s.weightx = 1;
//        s.weighty = 0;
//        layout.setConstraints(j6, s);
//        ;
//        s.gridwidth = 0;
//        s.weightx = 0;
//        s.weighty = 0;
//        layout.setConstraints(j7, s);
//        ;
//        s.gridwidth = 2;
//        s.weightx = 0;
//        s.weighty = 1;
//        layout.setConstraints(j8, s);
//        ;
//        s.gridwidth = 5;
//        s.weightx = 0;
//        s.weighty = 1;
//        layout.setConstraints(j9, s);
//    }
//
//}


import java.awt.Component;
import java.awt.Image;
import Java.Code.Command.Commands.EditCommand;
import Java.Code.Command.Commands.showOperationHint;
import Java.Code.Command.Commands.Common.changeImgProcessor;
import Java.Code.Command.Commands.Common.closeAllImgProcessors;
import Java.Code.Command.Commands.Common.closeImgProcessor;
import Java.Code.Command.Commands.Common.createCopy;
import Java.Code.Command.Commands.Common.existSoftware;
import Java.Code.Command.Commands.Common.readImgFromLocal;
import Java.Code.Command.EditDecorator.Anticolor;
import Java.Code.Command.EditDecorator.CombineFilter;
import Java.Code.Command.EditDecorator.CreateBorder;
import Java.Code.Command.EditDecorator.GrayFilter;
import Java.Code.Command.EditDecorator.HighContrastFilter;
import Java.Code.Command.EditDecorator.MosaicFilter;
import Java.Code.Command.EditDecorator.PaintFilter;
import Java.Code.Command.EditDecorator.VintageFilter;
import Java.Code.Exception.ArgsInvalidException;
import Java.Code.Software.Software;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;
  
public class guiMain extends JFrame{
	static private guiMain guiMain = null;
	static public guiMain getInstance(){
        if(guiMain==null)
        {
        	guiMain = new guiMain();
            return guiMain;
        }
        else
        {
            return guiMain;
        }
    }

    public guiMain(){
    	FlatLightLaf.setup();
        init();
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/extlib/ps.jpg"));
        this.setIconImage(imageIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
        this.setTitle("Photo Store");
        this.setResizable(true);
        this.setSize(1600, 900);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    SpringLayout springLayout = new SpringLayout();
    JLabel Info =new JLabel();
	JTabbedPane tabbedPane=new JTabbedPane();
    JButton btnCombine=new JButton("Combine");
    JButton btnGray=new JButton("Gray");
    JButton btnVintage=new JButton("Vintage");
    JButton btnCB=new JButton("Border");
    JButton btnHC=new JButton("Contrast");
    JButton btnPaint=new JButton("Paint");
    JButton btnMosaic=new JButton("Mosaic");
    JButton btnAnti=new JButton("AntiColor");
    JMenu fileMenu = new JMenu("File");
    JMenu editMenu = new JMenu("Edit");
    JMenu viewMenu = new JMenu("View");
    JMenu aboutMenu = new JMenu("About...");
    JMenuItem newMenuItem = new JMenuItem("Open");
    JMenuItem closeMenuItem = new JMenuItem("Close");
    JMenuItem closeAllMenuItem = new JMenuItem("Close All");
    JMenuItem exitMenuItem = new JMenuItem("Exit");
    JMenuItem aboutMenuItem=new JMenuItem("See Source Code");
    public static Software main_software = null;
    
    private void init(){
    	this.setLayout(springLayout);
    	Info.setBounds(0,400 ,100 ,400);
    	this.add(Info);
    	try
        {
            main_software = Software.getInstance();
            System.out.println("\nWelcome to Img Process Software !\n");
            	main_software.setCommand(new showOperationHint(null));
                main_software.execute();     
        }
        catch(Exception e)
        {
            System.out.println("software initialization failed !");
        } 
        this.setLayout(null);
        btnCombine.setBounds(0, 0, 100, 50);
        btnCombine.addActionListener(evt -> {
            try {
            	Integer degree= getMoveCount(0,100,"Paint Degree");
            	String ipnum = JOptionPane.showInputDialog("Another ImgProcessor index:"); 
				main_software.setCommand(new CombineFilter(new EditCommand(Software.getInstance().getMain_ip()),Integer.parseInt(ipnum),degree.floatValue()));
			} catch (ArgsInvalidException e1) {
				e1.printStackTrace();
			}
            main_software.execute();
            updatePane();
          });
        btnGray.setBounds(0, 50, 100, 50);
        btnGray.addActionListener(evt -> {
			main_software.setCommand(new GrayFilter(new EditCommand(Software.getInstance().getMain_ip())));
            main_software.execute();
            updatePane();
          });
        btnVintage.setBounds(0, 100, 100, 50);
        btnVintage.addActionListener(evt -> {
            try {
            	Integer noise= getMoveCount(0,100,"Vintage Degree");
            	main_software.setCommand(new VintageFilter(new EditCommand(Software.getInstance().getMain_ip()),noise.floatValue()));
			} catch (ArgsInvalidException e1) {
				e1.printStackTrace();
			}
            main_software.execute();
            updatePane();
          });
        btnCB.setBounds(0, 150, 100, 50);
        btnCB.addActionListener(evt -> {
            try {
            	String width = JOptionPane.showInputDialog("Border width(unit: pixel):");  
            	String choice = JOptionPane.showInputDialog("Another ImgProcessor index:"); 
				main_software.setCommand(new CreateBorder(new EditCommand(Software.getInstance().getMain_ip()),Integer.parseInt(width),Integer.parseInt(choice)));
			} catch (ArgsInvalidException e1) {
				e1.printStackTrace();
			}
            main_software.execute();
            updatePane();
          });
        btnHC.setBounds(0, 200, 100, 50); 
        btnHC.addActionListener(evt -> {
            try {
            	String contrast = JOptionPane.showInputDialog("Degree(from 1 to inf):");  
				main_software.setCommand(new HighContrastFilter(new EditCommand(Software.getInstance().getMain_ip()),Integer.parseInt(contrast)));
			} catch (ArgsInvalidException e1) {
				e1.printStackTrace();
			}
            main_software.execute();
            updatePane();
          });
        btnPaint.setBounds(0, 250, 100, 50);
        btnPaint.addActionListener(evt -> {
            try {
            	Integer degree= getMoveCount(0,100,"Paint Degree");
				main_software.setCommand(new PaintFilter(new EditCommand(Software.getInstance().getMain_ip()),degree));
			} catch (ArgsInvalidException e1) {
				e1.printStackTrace();
			}
            main_software.execute();
            updatePane();
          });
        btnMosaic.setBounds(0, 300, 100, 50);
        btnMosaic.addActionListener(evt -> {
            try {
            	String size = JOptionPane.showInputDialog("Mosaic size (unit: pixel):");  
				main_software.setCommand(new MosaicFilter(new EditCommand(Software.getInstance().getMain_ip()),Integer.parseInt(size)));
			} catch (ArgsInvalidException e1) {
				e1.printStackTrace();
			}
            main_software.execute();
            updatePane();
          });
        btnAnti.setBounds(0, 350, 100, 50);
        btnAnti.addActionListener(evt -> {
            main_software.setCommand(new Anticolor(new EditCommand(Software.getInstance().getMain_ip())));
            main_software.execute();
            updatePane();
          });
        this.add(btnCombine);
        this.add(btnGray);
        this.add(btnVintage);
        this.add(btnCB);
        this.add(btnHC);
        this.add(btnPaint);
        this.add(btnMosaic);
        this.add(btnAnti);
        tabbedPane.setBounds(100, 0, 1500,900);
        tabbedPane.addChangeListener(evt -> {
            JTabbedPane pane = (JTabbedPane) evt.getSource();
            int selectedIndex = pane.getSelectedIndex();
            System.out.println(selectedIndex);
            main_software.setCommand(new changeImgProcessor(null,selectedIndex));
            main_software.execute();
          });
        this.add(tabbedPane);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(aboutMenu);
        newMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e) {
        		boolean initflag=false;
        		if (Software.getInstance().getImgProcessorList().size()==0) {
        			initflag=true;
                }
        		JFileChooser chooser = new JFileChooser();          
        		chooser.showOpenDialog(newMenuItem);
        		String filepath = chooser.getSelectedFile().getAbsolutePath();
        		String filename=chooser.getSelectedFile().getName();
        		main_software.setCommand(new readImgFromLocal(null,filepath,filename));
                main_software.execute();
                main_software.setCommand(new showOperationHint(null));
                main_software.execute();
                BufferedImage img=Software.getInstance().getMain_ip().getImg();
                createTabbedPane(img,chooser.getSelectedFile().getName());
                if (initflag==true) {
                	enableOperation();
                }
        	}
        });
        aboutMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e) {
        		try {
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"https://github.com//xy-derrick//CS3343_Proj");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        	}
        });
        closeMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e) {
        		main_software.setCommand(new closeImgProcessor(Software.getInstance().getMain_ip()));
                main_software.execute();
                if (Software.getInstance().getImgProcessorList().size()==0) {
                	unableOperation();
                }
                tabbedPane.remove(tabbedPane.getSelectedIndex());
        	}
        });
        
        closeAllMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e) {
        		main_software.setCommand(new closeAllImgProcessors(Software.getInstance().getMain_ip()));
                main_software.execute();
                tabbedPane.removeAll();
                unableOperation();
        	}
        });
       
        exitMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e) {
        		main_software.setCommand(new existSoftware(null));
     	        main_software.execute();
        	}
        });
	       
        fileMenu.add(newMenuItem);
        fileMenu.add(closeMenuItem);
        fileMenu.add(closeAllMenuItem);
        fileMenu.addSeparator();      
        fileMenu.add(exitMenuItem);

        JMenuItem undoMenuItem = new JMenuItem("Undo");
        undoMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e) {
        		Software.getInstance().undo();
        	}
        });
        JMenuItem redoMenuItem = new JMenuItem("Redo");
        redoMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e) {
        		Software.getInstance().redo();
        	}
        });
        editMenu.add(undoMenuItem);
        editMenu.add(redoMenuItem);


        JMenuItem inMenuItem = new JMenuItem("Zoom in");
        JMenuItem outMenuItem = new JMenuItem("Zoom out");
        viewMenu.add(inMenuItem);
        viewMenu.add(outMenuItem);
        aboutMenu.add(aboutMenuItem);
        this.setJMenuBar(menuBar);
        this.setVisible(true);
        unableOperation();
    }
    

    private int getMoveCount(int low, int high,String title) {
    	int moveCount=0;
    	JSlider slider = createSlider(low,high);
    	JPanel sliderPanel= createSliderPanel(slider);
    	int dialogResponse=JOptionPane.showOptionDialog(this, sliderPanel, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
    	if (JOptionPane.OK_OPTION==dialogResponse) {
    		moveCount=slider.getValue();
    	}
    	else {
    		moveCount=0;	
    	}
    	return moveCount;
    }
    
    private JPanel createSliderPanel(JSlider slider) {
    	JPanel panel=new JPanel();
    	panel.add(slider);
		return panel;
	}
    
	private JSlider createSlider(int low, int high){
    	JSlider slider= new JSlider(low,high);
    	slider.setMajorTickSpacing(20);
    	slider.setMinorTickSpacing(10);
    	slider.setValue((low+high)/2);
    	slider.setPaintTicks(true);
        slider.setPaintLabels(true);
    	return slider;
    }
	
	private void updatePane() {
		Component panel=tabbedPane.getSelectedComponent();
	    ((JPanel) panel).removeAll();
	    JLabel label = new JLabel();
	    label.setIcon(new ImageIcon(Software.getInstance().getMain_ip().getImg()));
	    ((JPanel)panel).add(label);
	    ((JPanel)panel).repaint();
		((JPanel)panel).updateUI();
	}

	private void unableOperation() {
		for(Component co:this.getRootPane().getContentPane().getComponents()) {
			if (co.getClass().getName()== "javax.swing.JButton" ) {
				co.setEnabled(false);
			}
		}
		closeMenuItem.setEnabled(false);
		closeAllMenuItem.setEnabled(false);
	}
	
	private void enableOperation() {
		for(Component co:this.getRootPane().getContentPane().getComponents()) {
			if (co.getClass().getName()== "javax.swing.JButton" ) {
				co.setEnabled(true);
			}
		}
		closeMenuItem.setEnabled(true);
		closeAllMenuItem.setEnabled(true);
	}
	
	private void createTabbedPane(BufferedImage img,String tabName) {
		JPopupMenu popup=new JPopupMenu();
        JMenuItem popupCopyItem=new JMenuItem("Create a copy");
        popupCopyItem.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		BufferedImage img=Software.getInstance().getImgProcessorList().get(tabbedPane.getSelectedIndex()).getImg();
        		main_software.setCommand(new createCopy(Software.getInstance().getMain_ip(),img));
                main_software.execute();
                String copyname= "copy_"+tabbedPane.getSelectedIndex();
                createTabbedPane(img,copyname);
                Info.setText("Create a copy of img"+tabbedPane.getSelectedIndex());
                Info.repaint();
        	}
        });
        popup.add(popupCopyItem);
		JPanel P=new JPanel();            
		P.add(popup);
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(img));
		P.add(label);
		tabbedPane.addTab(tabName,P);
		tabbedPane.setSelectedIndex(Software.getInstance().getImgProcessorList().size()-1);
		P.addMouseListener(new MouseAdapter() {	 
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if(e.getButton()==MouseEvent.BUTTON3){
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}
 
}



