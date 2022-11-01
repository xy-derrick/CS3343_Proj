package Java.Gui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import Java.Code.Command.Commands.EditCommand;
import Java.Code.Command.Commands.showOperationHint;
import Java.Code.Command.Commands.Common.changeImgProcessor;
import Java.Code.Command.Commands.Common.closeAllImgProcessors;
import Java.Code.Command.Commands.Common.closeImgProcessor;
import Java.Code.Command.Commands.Common.createCopy;
import Java.Code.Command.Commands.Common.displayImg;
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
import Java.Code.Command.Export.*;
import Java.Code.Exception.ArgsInvalidException;
import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;
import Java.Gui.Loading.InfiniteProgressPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;
  
public class guiMain extends JFrame{
	static private guiMain guiMain = null;
	static public guiMain getInstance() throws InterruptedException{
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

    public guiMain() throws InterruptedException{
    	FlatLightLaf.setup();
	    InfiniteProgressPanel glasspane = new InfiniteProgressPanel();
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    glasspane.setBounds(100, 100, (dimension.width) / 2, (dimension.height) / 2);
	    this.setGlassPane(glasspane);
	    ImageIcon imageIcon = new ImageIcon(getClass().getResource("/extlib/ps.jpg"));
        this.setIconImage(imageIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
        this.setTitle("Photo Store");
        this.setResizable(true);
        this.setSize(1600, 900);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    glasspane.start();
	    this.setVisible(true);
	    Thread.sleep(1000);
        init();
        glasspane.stop();
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
    JMenuItem saveMenuItem = new JMenuItem("Save as...");
    JMenuItem closeMenuItem = new JMenuItem("Close");
    JMenuItem closeAllMenuItem = new JMenuItem("Close All");
    JMenuItem exitMenuItem = new JMenuItem("Exit");
    JMenuItem aboutMenuItem=new JMenuItem("See Source Code");
    public static Software main_software = null;
    ArrayList<JComponent> JComFilterList = new ArrayList<>();
    
    private void init(){
    	
    	
    	this.setLayout(springLayout);
    	Info.setBounds(0,400 ,100 ,400);
    	Info.setVerticalTextPosition(JLabel.TOP);
    	Info.setHorizontalTextPosition(JLabel.LEFT);
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
        btnCombine.setPreferredSize(new Dimension(100,50));
        btnGray.setPreferredSize(new Dimension(100,50));
        btnVintage.setPreferredSize(new Dimension(100,50));
        btnCB.setPreferredSize(new Dimension(100,50));
        btnHC.setPreferredSize(new Dimension(100,50));
        btnPaint.setPreferredSize(new Dimension(100,50));
        btnAnti.setPreferredSize(new Dimension(100,50));
        btnCombine.addActionListener(evt -> {
        	Integer degree= getMoveCount(0,100,"Paint Degree");
        	if (degree!=null) {
            	String[] res=imgProcessorChooser(true,"Another ImgProcessor index:");
            	if (res!=null) {
            		try {
                    	System.out.println(res[0].toString());
        				main_software.setCommand(new CombineFilter(new EditCommand(Software.getInstance().getMain_ip()),Integer.parseInt(res[0].split(" ")[0]),degree.floatValue()));
        			} catch (ArgsInvalidException e1) {
        				e1.printStackTrace();
        			}
                    main_software.execute();
                    updatePane();
            	}
        	}
          });
        btnGray.addActionListener(evt -> {
			main_software.setCommand(new GrayFilter(new EditCommand(Software.getInstance().getMain_ip())));
            main_software.execute();
            updatePane();
          });
        btnVintage.addActionListener(evt -> {
        	Integer noise= getMoveCount(0,100,"Vintage Degree");
        	if (noise!=null) {
        		try {
                	
                	main_software.setCommand(new VintageFilter(new EditCommand(Software.getInstance().getMain_ip()),noise.floatValue()));
    			} catch (ArgsInvalidException e1) {
    				e1.printStackTrace();
    			}
                main_software.execute();
                updatePane();
        	}
          });
        btnCB.addActionListener(evt -> {
        	String width = JOptionPane.showInputDialog("Border width(unit: pixel):");  
        	if (width!=null) {
        		String[] choice=imgProcessorChooser(true,"Another ImgProcessor index:");
        		if (choice !=null) {
        			try {
          				main_software.setCommand(new CreateBorder(new EditCommand(Software.getInstance().getMain_ip()),Integer.parseInt(width),Integer.parseInt(choice[0].split(" ")[0])));
          			} catch (ArgsInvalidException e1) {
          				e1.printStackTrace();
          			}
                    main_software.execute();
                    updatePane();
        		}
        	}
          });
        btnHC.addActionListener(evt -> {
        	String contrast = JOptionPane.showInputDialog("Degree(from 1 to inf):");  
        	if (contrast!=null) {
        		try {
      				main_software.setCommand(new HighContrastFilter(new EditCommand(Software.getInstance().getMain_ip()),Integer.parseInt(contrast)));
      			} catch (ArgsInvalidException e1) {
      				e1.printStackTrace();
      			}
                main_software.execute();
                updatePane();
        	}
          });
        btnPaint.addActionListener(evt -> {
        	Integer degree= getMoveCount(0,100,"Paint Degree");
        	if (degree!=null) {
        		try {	
        			main_software.setCommand(new PaintFilter(new EditCommand(Software.getInstance().getMain_ip()),degree));
    			} catch (ArgsInvalidException e1) {
    				e1.printStackTrace();
    			}
                main_software.execute();
                updatePane();
        	}
          });
        btnMosaic.setPreferredSize(new Dimension(100,50));
        btnMosaic.addActionListener(evt -> {
        	String size = JOptionPane.showInputDialog("Mosaic size (unit: pixel):");  
        	if (size!=null) {
        		try {
        			main_software.setCommand(new MosaicFilter(new EditCommand(Software.getInstance().getMain_ip()),Integer.parseInt(size)));
    			} catch (ArgsInvalidException e1) {
    				e1.printStackTrace();
    			}
                main_software.execute();
                updatePane();
        	}
          });
        btnAnti.addActionListener(evt -> {
            main_software.setCommand(new Anticolor(new EditCommand(Software.getInstance().getMain_ip())));
            main_software.execute();
            updatePane();
          });
        JComFilterList.add(btnCombine);
        JComFilterList.add(btnGray);
        JComFilterList.add(btnVintage);
        JComFilterList.add(btnCB);
        JComFilterList.add(btnHC);
        JComFilterList.add(btnPaint);
        JComFilterList.add(btnMosaic);
        JComFilterList.add(btnAnti);
      	JExpandablePanel panels = new JExpandablePanel(Color.black, "Filter", 20, JComFilterList);
      	panels.setBounds(0, 0, 100, 420);
      	this.add(panels);
        tabbedPane.setBounds(100, 0, 1450,800);
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
        		ImgFileFilter imgFilter = new ImgFileFilter(); //excel过滤器  
        		chooser.addChoosableFileFilter(imgFilter);
        		chooser.setFileFilter(imgFilter);
        		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        		chooser.showOpenDialog(newMenuItem);
        		String filepath = chooser.getSelectedFile().getAbsolutePath();
        		if (filepath!=null) {
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
        saveMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e) {
        		JList<String> typeList= new JList<String>();
        		typeList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        		String[] typeNameList={".bmp",".gif",".jpg","png","tiff"};
        		typeList.setListData(typeNameList);
        		JScrollPane chooserPanel = new JScrollPane(typeList);
        		int dialogResponse=JOptionPane.showOptionDialog(saveMenuItem, chooserPanel,"Select a type...", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        		if (JOptionPane.OK_OPTION==dialogResponse) {
        			Object chooserValue=typeList.getSelectedValue();
        			String type=chooserValue.toString();
        			switch (type) {
	        			case ".bmp":
	        				String path= pathChooser();
	        				if (path!=null) {
	        					imgProcessor ip=Software.getInstance().getMain_ip();
	        					System.out.println(path);
//	        					main_software.setCommand(new displayImg(ip));
//	        					main_software.execute();
	        					main_software.setCommand(new bmpTransfer(ip,path));
	        				}
	        				break;
	        			case ".gif":
	        				main_software.setCommand(new closeImgProcessor(Software.getInstance().getMain_ip()));
	        				break;
	        			case ".jpg":
	        				main_software.setCommand(new closeImgProcessor(Software.getInstance().getMain_ip()));
	        				break;
	        			case ".png":
	        				main_software.setCommand(new closeImgProcessor(Software.getInstance().getMain_ip()));
	        				break;
	        			case ".tiff":
	        				main_software.setCommand(new closeImgProcessor(Software.getInstance().getMain_ip()));
	        				break;
        			}
        			main_software.execute();
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
        fileMenu.add(saveMenuItem);
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
    	JPanel sliderPanel= createInputPanel(slider);
    	int dialogResponse=JOptionPane.showOptionDialog(this, sliderPanel, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
    	if (JOptionPane.OK_OPTION==dialogResponse) {
    		moveCount=slider.getValue();
    	}
    	else {
    		moveCount=0;	
    	}
    	return moveCount;
    }
    
    private JPanel createInputPanel(Component com) {
    	JPanel panel=new JPanel();
    	panel.add(com);
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
	
	private JList<String> createList(Boolean singleChoice) {
		JList<String> list= new JList<String>();
		if (singleChoice==false) {
			list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		} else {
			list.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		}
		List<String> nameList=new ArrayList<String>();
		ArrayList<imgProcessor> iplist=Software.getInstance().getImgProcessorList();
		for (imgProcessor ip: iplist) {
			nameList.add(iplist.indexOf(ip)+" "+ip.getName());
		}
		String[] namelist=nameList.toArray(new String[nameList.size()]);
		list.setListData(namelist);
		return list;
	}
	

	private String[] imgProcessorChooser(Boolean singleChoice, String title){
		JList<String> chooser=createList(singleChoice);
		JScrollPane chooserPanel = new JScrollPane(chooser);
    	int dialogResponse=JOptionPane.showOptionDialog(this, chooserPanel, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
    	if (JOptionPane.OK_OPTION==dialogResponse) {
    		if (singleChoice==true) {
    			Object chooserValue=chooser.getSelectedValue();
    			String[] res=new String[1];
    			res[0]=chooserValue.toString();
    			return res;
    		} else {
    			Object[] chooserValue=chooser.getSelectedValues();
        		String[] res=new String[chooserValue.length+1];
        		for (int i = 0; i < chooserValue.length; i++) {
        			res[i]=chooserValue[i].toString();
        		}
        		return res;
    		}
    		
    	} else {
    		return null;
    	}
	} 
	
	private void updatePane() {
		Component panel=tabbedPane.getSelectedComponent();
	    JPanel jp=(JPanel) ((JScrollPane) panel).getViewport().getComponent(0);
	    JLabel label = new JLabel();
	    label.setIcon(new ImageIcon(Software.getInstance().getMain_ip().getImg()));
	    jp.removeAll();
	    jp.add(label);
	    jp.updateUI();
	    jp.repaint();
	}

	private void unableOperation() {
		for(JComponent JB :JComFilterList) {
			JB.setEnabled(false);
		}
		saveMenuItem.setEnabled(false);
		closeMenuItem.setEnabled(false);
		closeAllMenuItem.setEnabled(false);
	}
	
	private void enableOperation() {
		for(JComponent JB :JComFilterList) {
			JB.setEnabled(true);
		}
		saveMenuItem.setEnabled(true);
		closeMenuItem.setEnabled(true);
		closeAllMenuItem.setEnabled(true);
	}
	
	private String pathChooser() {
		JFileChooser chooser = new JFileChooser();  
		ImgFileFilter imgFilter = new ImgFileFilter(); //excel过滤器  
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.showOpenDialog(saveMenuItem);
		return chooser.getSelectedFile().getAbsolutePath();
	}
	
	private void createTabbedPane(BufferedImage img,String tabName) {
		JPopupMenu popup=new JPopupMenu();
        JMenuItem popupCopyItem=new JMenuItem("Create a copy");
        JMenuItem popupCloseItem=new JMenuItem("Close this img");
        popupCloseItem.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		main_software.setCommand(new closeImgProcessor(Software.getInstance().getMain_ip()));
                main_software.execute();
                if (Software.getInstance().getImgProcessorList().size()==0) {
                	unableOperation();
                }
                tabbedPane.remove(tabbedPane.getSelectedIndex());
        	}
        });
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
        popup.add(popupCloseItem);
		JPanel P=new JPanel();            
		P.add(popup);
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(img));
		P.add(label);
		JScrollPane scrollPane = new JScrollPane(P);
		tabbedPane.addTab(tabName,scrollPane);
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



