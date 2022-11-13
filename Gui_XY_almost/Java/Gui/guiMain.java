package Java.Gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import Java.Code.Batch.*;
import Java.Code.Command.Commands.EditCommand;
import Java.Code.Command.Commands.showOperationHint;
import Java.Code.Command.Commands.Common.*;
import Java.Code.Command.EditDecorator.*;
import Java.Code.Command.EditDecorator.GrayFilter;
import Java.Code.Command.Export.*;
import Java.Code.Command.Login.autoSave;
import Java.Code.Command.Login.loginController;
import Java.Code.Exception.ArgsInvalidException;
import Java.Code.Software.Software;
import Java.Code.Software.imgProcessor;
import Java.Gui.Loading.LoadingPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class guiMain extends JFrame {
	private static final long serialVersionUID = 1L;
	private Timer timer = null;
	static private guiMain guiMain = null;

	static public guiMain getInstance() throws InterruptedException, ArgsInvalidException, SQLException, IOException {
		if (guiMain == null) {
			guiMain = new guiMain();
			return guiMain;
		} else {
			return guiMain;
		}
	}

	static public void writeLog(String log) {
		Date d = new Date();
		Info.append(d.toString() + "\n" + log);
	}

	public guiMain() throws InterruptedException, ArgsInvalidException, SQLException, IOException {
		try {
			main_software = Software.getInstance();
		} catch (Exception e) {
			System.out.println("software initialization failed !");
		}
		LoadingPanel glasspane = new LoadingPanel();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		glasspane.setBounds(100, 100, (dimension.width), (dimension.height));
		this.setGlassPane(glasspane);
		glasspane.setText("Initializing, Please wait ...");
		glasspane.start();// start loading
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/extlib/ps.jpg"));
		this.setIconImage(imageIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
		this.setTitle("Photo Store");
		this.setResizable(true);
		this.setSize(1600, 900);
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		Thread.sleep(500);
		int loginRes = logInModule();
		init(loginRes);
		glasspane.setText("Welcome!");
		Thread.sleep(200);
		glasspane.stop(); // stop loading
	}

	static JTextArea Info = new JTextArea();
	JTabbedPane tabbedPane = new JTabbedPane();
	JButton btnCombine = new JButton("Combine");
	JButton btnGray = new JButton("Gray");
	JButton btnVintage = new JButton("Vintage");
	JButton btnCB = new JButton("Border");
	JButton btnHC = new JButton("Contrast");
	JButton btnPaint = new JButton("Paint");
	JButton btnMosaic = new JButton("Mosaic");
	JButton btnAnti = new JButton("AntiColor");
	JButton btnFlipHorizontal = new JButton("FlipHorizontal");
	JButton btnFlipVertical = new JButton("Flipvertical");
	JButton btnRotate180 = new JButton("Rotate180");
	JButton btnRotate90R = new JButton("Rotate90R");
	JButton btnRotate90L = new JButton("Rotate90L");
	JButton btnTailor = new JButton("Tailor");
	JButton btnZoom = new JButton("Zoom");
	JMenu fileMenu = new JMenu("File");
	JMenu editMenu = new JMenu("Edit");
	JMenu viewMenu = new JMenu("View");
	JMenu batchMenu = new JMenu("Batch");
	JMenu aboutMenu = new JMenu("About...");
	JMenuItem newMenuItem = new JMenuItem("Open");
	JMenuItem saveMenuItem = new JMenuItem("Save");
	JMenuItem saveAsMenuItem = new JMenuItem("Save as...");
	JMenuItem closeMenuItem = new JMenuItem("Close");
	JMenuItem closeAllMenuItem = new JMenuItem("Close All");
	JMenuItem exitMenuItem = new JMenuItem("Exit");
	JMenuItem aboutMenuItem = new JMenuItem("See Source Code");
	JMenuItem batchMenuItem = new JMenuItem("Start batch...");
	public static Software main_software = null;
	ArrayList<JComponent> JComFilterList = new ArrayList<>();
	ArrayList<JComponent> JComEditList = new ArrayList<>();
	ArrayList<Integer> point = new ArrayList<Integer>();

	private void init(int loginRes) throws ArgsInvalidException {
		TitledBorder border = new TitledBorder("Console");
		border.setBorder(new LineBorder(Color.black));
		Info.setBorder(border);
		Info.setBounds(1550, 0, 350, 790);
		Info.setLineWrap(true);
		this.add(Info);

		this.setLayout(null);
		btnCombine.setPreferredSize(new Dimension(100, 50));
		btnGray.setPreferredSize(new Dimension(100, 50));
		btnVintage.setPreferredSize(new Dimension(100, 50));
		btnCB.setPreferredSize(new Dimension(100, 50));
		btnHC.setPreferredSize(new Dimension(100, 50));
		btnPaint.setPreferredSize(new Dimension(100, 50));
		btnMosaic.setPreferredSize(new Dimension(100, 50));
		btnAnti.setPreferredSize(new Dimension(100, 50));
		btnFlipHorizontal.setPreferredSize(new Dimension(100, 50));
		btnFlipVertical.setPreferredSize(new Dimension(100, 50));
		btnRotate180.setPreferredSize(new Dimension(100, 50));
		btnRotate90R.setPreferredSize(new Dimension(100, 50));
		btnRotate90L.setPreferredSize(new Dimension(100, 50));
		btnTailor.setPreferredSize(new Dimension(100, 50));
		btnZoom.setPreferredSize(new Dimension(100, 50));
		btnCombine.addActionListener(evt -> {
			Integer degree = getMoveCount(0, 100, "Paint Degree");
			if (degree != null) {
				int[] res = imgProcessorChooser(true, "Another ImgProcessor index:");
				if (res != null) {
					try {
						System.out.println(res[0]);
						main_software.setCommand(new CombineFilter(new EditCommand(Software.getInstance().getMain_ip()),
								res[0], degree.floatValue()));
					} catch (ArgsInvalidException e1) {
						e1.printStackTrace();
					}
					main_software.execute();
					updatePane();
					writeLog("Combine filter done\n");
				}
			}
		});
		btnGray.addActionListener(evt -> {
			main_software.setCommand(new GrayFilter(new EditCommand(Software.getInstance().getMain_ip())));
			main_software.execute();
			updatePane();
			writeLog("Gray filter done\n");
		});
		btnVintage.addActionListener(evt -> {
			Integer noise = getMoveCount(0, 100, "Vintage Degree");
			if (noise != null) {
				try {

					main_software.setCommand(new VintageFilter(new EditCommand(Software.getInstance().getMain_ip()),
							noise.floatValue()));
				} catch (ArgsInvalidException e1) {
					e1.printStackTrace();
				}
				main_software.execute();
				updatePane();
				writeLog("Vintage filter done\n");
			}
		});
		btnCB.addActionListener(evt -> {
			String width = JOptionPane.showInputDialog("Border width(unit: pixel):");
			if (width != null) {
				int[] choice = imgProcessorChooser(true, "Another ImgProcessor index:");
				if (choice != null) {
					try {
						main_software.setCommand(new CreateBorder(new EditCommand(Software.getInstance().getMain_ip()),
								Integer.parseInt(width), (choice[0])));
					} catch (ArgsInvalidException e1) {
						e1.printStackTrace();
					}
					main_software.execute();
					updatePane();
					writeLog("Create Border done\n");
				}
			}
		});
		btnHC.addActionListener(evt -> {
			String contrast = JOptionPane.showInputDialog("Degree(from 1 to 2147483647):");
			if (contrast != null) {
				try {
					main_software.setCommand(new HighContrastFilter(
							new EditCommand(Software.getInstance().getMain_ip()), Integer.parseInt(contrast)));
					main_software.execute();
					updatePane();
					writeLog("Change Contrast filter done\n");
				} catch (ArgsInvalidException e1) {
					writeLog("Invalid args\n");
				} catch (NumberFormatException e) {
					writeLog("Too big number for Integer\n");
					writeLog("Change Contrast filter done\n");
				}
			}
		});
		btnPaint.addActionListener(evt -> {
			Integer degree = getMoveCount(0, 100, "Paint Degree");
			if (degree != null) {
				try {
					main_software
							.setCommand(new PaintFilter(new EditCommand(Software.getInstance().getMain_ip()), degree));
					main_software.execute();
					updatePane();
					writeLog("Paint filter done\n");
				} catch (ArgsInvalidException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnMosaic.addActionListener(evt -> {
			String size = JOptionPane.showInputDialog("Mosaic size (unit: pixel):");
			if (size != null) {
				try {
					main_software.setCommand(new MosaicFilter(new EditCommand(Software.getInstance().getMain_ip()),
							Integer.parseInt(size)));
					main_software.execute();
					updatePane();
					writeLog("Mosaic filter done\n");
				} catch (ArgsInvalidException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAnti.addActionListener(evt -> {
			main_software.setCommand(new Anticolor(new EditCommand(Software.getInstance().getMain_ip())));
			main_software.execute();
			updatePane();
			writeLog("Anticolor filter done\n");
		});

		btnFlipHorizontal.addActionListener(evt -> {
			main_software.setCommand(new FlipHorizontal(new EditCommand(Software.getInstance().getMain_ip())));
			main_software.execute();
			updatePane();
			writeLog("Filp horizontally done\n");
		});
		btnFlipVertical.addActionListener(evt -> {
			main_software.setCommand(new FlipVertical(new EditCommand(Software.getInstance().getMain_ip())));
			main_software.execute();
			updatePane();
			writeLog("Filp Vertically done\n");
		});
		btnRotate180.addActionListener(evt -> {
			main_software.setCommand(new Rotate180Degrees(new EditCommand(Software.getInstance().getMain_ip())));
			main_software.execute();
			updatePane();
			writeLog("Rotate 180 degree done\n");
		});
		btnRotate90R.addActionListener(evt -> {
			main_software
					.setCommand(new Rotate90DegreesClockwise(new EditCommand(Software.getInstance().getMain_ip())));
			main_software.execute();
			updatePane();
			writeLog("Rotate 90 degree to right done\n");
		});
		btnRotate90L.addActionListener(evt -> {
			main_software.setCommand(
					new Rotate90DegreesCounterclockwise(new EditCommand(Software.getInstance().getMain_ip())));
			main_software.execute();
			updatePane();
			writeLog("Rotate 90 degree to left done\n");
		});
		btnTailor.addActionListener(evt -> {
			Integer startX = point.get(point.size() - 4);
			Integer startY = point.get(point.size() - 3);
			Integer secondX = point.get(point.size() - 2);
			Integer secondY = point.get(point.size() - 1);
			JLabel info = new JLabel(
					"Please confirm that, will keep the rectangle area nuild by latest two points in Consle.");
			JOptionPane.showOptionDialog(this, info, "Confirmation", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE,
					null, null, null);
			writeLog("UpperLeft:" + Math.min(startX, secondX) + " " + Math.min(startY, secondY) + "\nWidth:"
					+ Math.abs(startX - secondX) + " Height:" + Math.abs(startY - secondY));
			main_software.setCommand(new Tailoring(new EditCommand(Software.getInstance().getMain_ip()), startX, startY,
					secondX, secondY));
			main_software.execute();
			updatePane();
			writeLog("Tailor done");
		});
		btnZoom.addActionListener(evt -> {
			Integer percent = getMoveCount(0, 100, "Target percent");
			try {
				main_software
						.setCommand(new Zoom(new EditCommand(Software.getInstance().getMain_ip()), (percent / 10f)));
				main_software.execute();
				updatePane();
				writeLog("Zoom done");
			} catch (ArgsInvalidException e1) {
				e1.printStackTrace();
			}
		});
		JComFilterList.add(btnCombine);
		JComFilterList.add(btnGray);
		JComFilterList.add(btnVintage);
		JComFilterList.add(btnCB);
		JComFilterList.add(btnHC);
		JComFilterList.add(btnPaint);
		JComFilterList.add(btnMosaic);
		JComFilterList.add(btnAnti);
		btnFlipVertical.setPreferredSize(new Dimension(100, 50));
		btnRotate180.setPreferredSize(new Dimension(100, 50));
		btnRotate90R.setPreferredSize(new Dimension(100, 50));
		btnRotate90L.setPreferredSize(new Dimension(100, 50));
		btnTailor.setPreferredSize(new Dimension(100, 50));
		btnZoom.setPreferredSize(new Dimension(100, 50));
		JComEditList.add(btnFlipHorizontal);
		JComEditList.add(btnFlipVertical);
		JComEditList.add(btnRotate180);
		JComEditList.add(btnRotate90R);
		JComEditList.add(btnRotate90L);
		JComEditList.add(btnTailor);
		JComEditList.add(btnZoom);
		JExpandablePanel Filterpanels = new JExpandablePanel(Color.black, "Filter", 20, JComFilterList);
		JExpandablePanel EditPanels = new JExpandablePanel(Color.black, "Edit", 20, JComEditList);
		Filterpanels.setBounds(0, 0, 100, 420);
		this.add(Filterpanels);
		EditPanels.setBounds(0, 420, 100, 370);
		this.add(EditPanels);
		tabbedPane.setBounds(100, 0, 1450, 800);
		tabbedPane.addChangeListener(evt -> {
			JTabbedPane pane = (JTabbedPane) evt.getSource();
			int selectedIndex = pane.getSelectedIndex();
			System.out.println(selectedIndex);
			main_software.setCommand(new changeImgProcessor(null, selectedIndex));
			main_software.execute();
		});
		this.add(tabbedPane);
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(batchMenu);
		menuBar.add(aboutMenu);
		newMenuItem.setToolTipText("Open an image");
		newMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean initflag = false;
				if (Software.getInstance().getImgProcessorList().size() == 0) {
					initflag = true;
				}
				JFileChooser chooser = new JFileChooser();
				ImgFileFilter imgFilter = new ImgFileFilter();
				chooser.addChoosableFileFilter(imgFilter);
				chooser.setFileFilter(imgFilter);
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.showOpenDialog(newMenuItem);
				String filepath =null;
				try {
					filepath = chooser.getSelectedFile().getAbsolutePath();
				}
				catch (Exception e1) {
					
				}
				if (filepath != null) {
					String filename = chooser.getSelectedFile().getName();
					main_software.setCommand(new readImgFromLocal(null, filepath, filename));
					main_software.execute();
					main_software.setCommand(new showOperationHint(null));
					main_software.execute();
					BufferedImage img = Software.getInstance().getMain_ip().getImg();
					createTabbedPane(img, chooser.getSelectedFile().getName());
					if (initflag == true) {
						enableOperation();
					}
					writeLog("Open file done\n");
				}
			}
		});
		aboutMenuItem.setToolTipText("Show information about to this project");
		aboutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec(
							"rundll32 url.dll,FileProtocolHandler " + "https://github.com//xy-derrick//CS3343_Proj");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		saveMenuItem.setToolTipText("Save to original image file");
		saveMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgProcessor ip = Software.getInstance().getMain_ip();
				File outputfile = new File(ip.getPath());
				try {
					ImageIO.write(ip.getImg(), ip.getName().substring(ip.getName().lastIndexOf(".") + 1), outputfile);
				} catch (IOException e1) {
					writeLog("Save fail!");
				}
			}
		});
		saveAsMenuItem.setToolTipText("Save this image as another type");
		saveAsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JList<String> typeList = new JList<String>();
				typeList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
				String[] typeNameList = { "bmp", "gif", "jpg", "png", "tiff", "zip" };
				typeList.setListData(typeNameList);
				JScrollPane chooserPanel = new JScrollPane(typeList);
				int dialogResponse = JOptionPane.showOptionDialog(saveAsMenuItem, chooserPanel, "Select a type...",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
				if (JOptionPane.OK_OPTION == dialogResponse) {
					Object chooserValue = typeList.getSelectedValue();
					String type = chooserValue.toString();
					imgProcessor ip = Software.getInstance().getMain_ip();
					switch (type) {
					case "bmp":
						String path = pathChooser(saveAsMenuItem);
						if (path != null) {
							System.out.println(path);
							main_software.setCommand(new bmpTransfer(ip, path));
						}
						break;
					case "gif":
						path = pathChooser(saveAsMenuItem);
						if (path != null) {
							System.out.println(path);
							main_software.setCommand(new gifTransfer(ip, path));
						}
						break;
					case "jpg":
						path = pathChooser(saveAsMenuItem);
						if (path != null) {
							System.out.println(path);
							main_software.setCommand(new jpgTransfer(ip, path));
						}
						break;
					case "png":
						path = pathChooser(saveAsMenuItem);
						if (path != null) {
							System.out.println(path);
							main_software.setCommand(new pngTransfer(ip, path));
						}
						break;
					case "tiff":
						path = pathChooser(saveAsMenuItem);
						if (path != null) {
							System.out.println(path);
							main_software.setCommand(new tiffTransfer(ip, path));
						}
						break;
					case "zip":
						path = pathChooser(saveAsMenuItem);
						if (path != null) {
							System.out.println(path);
							main_software.setCommand(new imagCompress(ip, path));
						}
						break;
					}
					main_software.execute();
				}
			}
		});
		closeMenuItem.setToolTipText("Close this image");
		closeMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main_software.setCommand(new closeImgProcessor(Software.getInstance().getMain_ip()));
				main_software.execute();
				if (Software.getInstance().getImgProcessorList().size() == 0) {
					unableOperation();
				}
				tabbedPane.remove(tabbedPane.getSelectedIndex());
			}
		});
		closeAllMenuItem.setToolTipText("Close all opened images");
		closeAllMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main_software.setCommand(new closeAllImgProcessors(Software.getInstance().getMain_ip()));
				main_software.execute();
				tabbedPane.removeAll();
				unableOperation();
			}
		});
		exitMenuItem.setToolTipText("Exit the program");
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				autoSave.onLogOutSuccessful(timer);
				main_software.setCommand(new existSoftware(null));
				main_software.execute();
			}
		});
		batchMenuItem.setToolTipText("Select a list of image and edit them in one time");
		batchMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] ipList = imgProcessorChooser(false, "Choose a list of ip");
				ArrayList<Object> batchImgs = new ArrayList<>();
				for (int ipindex : ipList) {
					batchImgs.add(ipindex);
				}
				main_software.setCommand(new batchAdd(null, batchImgs));
				main_software.execute();
				ArrayList<Object> batchCommands = new ArrayList<>();
				String[] commandList = commandChooser("Choose a list of ip");
				for (String command : commandList) {
					writeLog(command);
					batchCommands.add(new transCommand(command, transfer(command)));
				}
				main_software.setCommand(new batchAdd(null, batchCommands));
				main_software.execute();
				if (batchPreview.checkPreviewable()) {
					BufferedImage previewImg = batchPreview.getInstance().execute();
					JPanel previewPanel = new JPanel();
					JLabel label = new JLabel();
					label.setIcon(new ImageIcon(previewImg));
					previewPanel.add(label);
					JScrollPane scrollPane = new JScrollPane(previewPanel);
					scrollPane.setPreferredSize(new Dimension(800, 600));
					int dialogResponse = JOptionPane.showOptionDialog(null, scrollPane, "Preview",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
					if (JOptionPane.OK_OPTION == dialogResponse) {
						main_software.setCommand(new batchExecute(null));
						main_software.execute();
						for (int ipindex : ipList) {
							tabbedPane.setSelectedIndex(ipindex);
							updatePane();
						}
					}
				} else {
					main_software.setCommand(new batchExecute(null));
					main_software.execute();
				}
			}
		});
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(saveAsMenuItem);
		fileMenu.add(closeMenuItem);
		fileMenu.add(closeAllMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);
		JMenuItem undoMenuItem = new JMenuItem("Undo");
		undoMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Software.getInstance().undo();
				writeLog("Undo done\n");
				updatePane();
			}
		});
		JMenuItem redoMenuItem = new JMenuItem("Redo");
		redoMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Software.getInstance().redo();
				writeLog("Redo done\n");
				updatePane();
			}
		});
		editMenu.add(undoMenuItem);
		editMenu.add(redoMenuItem);
		aboutMenu.add(aboutMenuItem);
		batchMenu.add(batchMenuItem);
		this.setJMenuBar(menuBar);
		this.setVisible(true);
		if (loginRes == 1) {
			BufferedImage img = Software.getInstance().getMain_ip().getImg();
			createTabbedPane(img, "cloud data");
			writeLog("Load image from cloud data");
		} else {
			unableOperation();
		}
	}

	private int getMoveCount(int low, int high, String title) {
		int moveCount = 0;
		JSlider slider = createSlider(low, high);
		JPanel sliderPanel = createInputPanel(slider);
		int dialogResponse = JOptionPane.showOptionDialog(this, sliderPanel, title, JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, null, null);
		if (JOptionPane.OK_OPTION == dialogResponse) {
			moveCount = slider.getValue();
		} else {
			moveCount = 0;
		}
		return moveCount;
	}

	private JPanel createInputPanel(Component com) {
		JPanel panel = new JPanel();
		panel.add(com);
		return panel;
	}

	private JSlider createSlider(int low, int high) {
		JSlider slider = new JSlider(low, high);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(10);
		slider.setValue((low + high) / 2);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		return slider;
	}

	private JList createList(Boolean singleChoice) {
		JList list = new JList();
		if (singleChoice == false) {
			list.setSelectionMode(DefaultListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		} else {
			list.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		}
		List<String> nameList = new ArrayList<String>();
		ArrayList<imgProcessor> iplist = Software.getInstance().getImgProcessorList();
		for (imgProcessor ip : iplist) {
			nameList.add(iplist.indexOf(ip) + " " + ip.getName());
		}
		String[] namelist = nameList.toArray(new String[nameList.size()]);
		list.setListData(namelist);
		return list;
	}

	private JList createListFromInit() {
		JList list = new JList();
		list.setSelectionMode(DefaultListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		String[] commandlist = new String[] { "Gray", "Vintage", "Border", "Paint", "Mosaic", "FlipHorizontal",
				"FlipVertical", "Zoom", "Tailor", "Rotate90L", "Rotate90R", "Rotate180", "Rotate90R", "AntiColor",
				"jpgTransfer", "pngTransfer", "gifTransfer", "bmpTransfer", "tiffTransfer", "imagCompress",
				"localSave" };
		list.setListData(commandlist);
		return list;
	}

	private int[] imgProcessorChooser(Boolean singleChoice, String title) {
		JList chooser = createList(singleChoice);
		JScrollPane chooserPanel = new JScrollPane(chooser);
		int dialogResponse = JOptionPane.showOptionDialog(this, chooserPanel, title, JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, null, null);
		if (JOptionPane.OK_OPTION == dialogResponse) {
			int[] chooserValue = chooser.getSelectedIndices();
			return chooserValue;
		} else {
			return null;
		}
	}

	private String[] commandChooser(String title) {
		JList chooser = createListFromInit();
		JScrollPane chooserPanel = new JScrollPane(chooser);
		String[] commandlist = new String[] { "Gray", "Vintage", "Border", "Paint", "Mosaic", "FlipHorizontal",
				"FlipVertical", "Zoom", "Tailor", "Rotate90L", "Rotate90R", "Rotate180", "Rotate90R", "AntiColor",
				"jpgTransfer", "pngTransfer", "gifTransfer", "bmpTransfer", "tiffTransfer", "imagCompress",
				"localSave" };
		int dialogResponse = JOptionPane.showOptionDialog(this, chooserPanel, title, JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, null, null);
		if (JOptionPane.OK_OPTION == dialogResponse) {
			int[] chooserValue = chooser.getSelectedIndices();
			String[] res = new String[chooserValue.length];
			for (int i = 0; i < chooserValue.length; i++) {
				res[i] = commandlist[chooserValue[i]];
			}
			return res;
		} else {
			return null;
		}
	}

	private void updatePane() {
		Component panel = tabbedPane.getSelectedComponent();
		JPanel jp = (JPanel) ((JScrollPane) panel).getViewport().getComponent(0);
		JLabel label = new JLabel();
		JPopupMenu popup = new JPopupMenu();
		JMenuItem popupCopyItem = new JMenuItem("Create a copy");
		JMenuItem popupCloseItem = new JMenuItem("Close this img");
		popupCloseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main_software.setCommand(new closeImgProcessor(Software.getInstance().getMain_ip()));
				main_software.execute();
				if (Software.getInstance().getImgProcessorList().size() == 0) {
					unableOperation();
				}
				tabbedPane.remove(tabbedPane.getSelectedIndex());
			}
		});
		popupCopyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedImage img = Software.getInstance().getImgProcessorList().get(tabbedPane.getSelectedIndex())
						.getImg();
				main_software.setCommand(new createCopy(Software.getInstance().getMain_ip(), img));
				main_software.execute();
				String copyname = "copy_" + tabbedPane.getSelectedIndex();
				createTabbedPane(img, copyname);
				writeLog("Create a copy of img" + tabbedPane.getSelectedIndex());
			}
		});
		popup.add(popupCopyItem);
		popup.add(popupCloseItem);
		label.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					point.add(e.getX());
					point.add(e.getY());
					writeLog("Point marked:" + e.getX() + " " + e.getY() + "\n");
				}
				if (e.getButton() == MouseEvent.BUTTON3) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		label.setIcon(new ImageIcon(Software.getInstance().getMain_ip().getImg()));
		jp.removeAll();
		jp.add(label);
		jp.updateUI();
		jp.repaint();
	}

	private void unableOperation() {
		for (JComponent JB : JComFilterList) {
			JB.setEnabled(false);
		}
		for (JComponent JB : JComEditList) {
			JB.setEnabled(false);
		}
		saveAsMenuItem.setEnabled(false);
		closeMenuItem.setEnabled(false);
		closeAllMenuItem.setEnabled(false);
		saveMenuItem.setEnabled(false);
		batchMenu.setEnabled(false);
	}

	private void enableOperation() {
		for (JComponent JB : JComFilterList) {
			JB.setEnabled(true);
		}
		for (JComponent JB : JComEditList) {
			JB.setEnabled(true);
		}
		saveAsMenuItem.setEnabled(true);
		closeMenuItem.setEnabled(true);
		closeAllMenuItem.setEnabled(true);
		saveMenuItem.setEnabled(true);
		batchMenu.setEnabled(true);
	}

	private String pathChooser(JComponent co) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.showOpenDialog(co);
		return chooser.getSelectedFile().getAbsolutePath();
	}

	private ArrayList<Object> transfer(String cmdType) {
		ArrayList<Object> args = new ArrayList<Object>();
		switch (cmdType) {
		case "jpgTransfer":
			String path = pathChooser(null);
			args.add(path);
			return args;
		case "pngTransfer":
			path = pathChooser(null);
			args.add(path);
			return args;
		case "gifTransfer":
			path = pathChooser(null);
			args.add(path);
			return args;
		case "bmpTransfer":
			path = pathChooser(null);
			args.add(path);
			return args;
		case "tiffTransfer":
			path = pathChooser(null);
			args.add(path);
			return args;
		case "imagCompress":
			path = pathChooser(null);
			args.add(path);
			return args;
		case "localSave":
			path = pathChooser(null);
			args.add(path);
			return args;
		case "Combine":
			Integer degree = getMoveCount(0, 100, "Paint Degree");
			int[] res = imgProcessorChooser(true, "Another ImgProcessor index:");
			args.add(res[0]);
			args.add(degree.floatValue());
			return args;
		case "Gray":
			return args;
		case "Vintage":
			Integer noise = getMoveCount(0, 100, "Vintage Degree");
			args.add(noise.floatValue());
			return args;
		case "Border":
			String width = JOptionPane.showInputDialog("Border width(unit: pixel):");
			int[] choice = imgProcessorChooser(true, "Another ImgProcessor index:");
			args.add(Integer.parseInt(width));
			args.add(choice[0]);
			return args;
		case "Contrast":
			String contrast = JOptionPane.showInputDialog("Degree(from 1 to 2147483647):");
			args.add(Integer.parseInt(contrast));
			return args;
		case "Paint":
			Integer Paintdegree = getMoveCount(0, 100, "Paint Degree");
			args.add(Paintdegree);
			return args;
		case "Mosaic":
			String size = JOptionPane.showInputDialog("Mosaic size (unit: pixel):");
			args.add(Integer.parseInt(size));
			return args;
		case "Tailor":
			Integer startX = point.get(point.size() - 4);
			Integer startY = point.get(point.size() - 3);
			Integer secondX = point.get(point.size() - 2);
			Integer secondY = point.get(point.size() - 1);
			args.add(startX);
			args.add(startY);
			args.add(secondX);
			args.add(secondY);
			return args;
		case "Zoom":
			Integer percent = getMoveCount(0, 100, "Target percent");
			args.add(percent / 10f);
			return args;
		default:
			return args;
		}
	}

	private void createTabbedPane(BufferedImage img, String tabName) {
		JPopupMenu popup = new JPopupMenu();
		JMenuItem popupCopyItem = new JMenuItem("Create a copy");
		JMenuItem popupCloseItem = new JMenuItem("Close this img");
		JMenuItem zoominItem = new JMenuItem("Zoom in");
		popupCloseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main_software.setCommand(new closeImgProcessor(Software.getInstance().getMain_ip()));
				main_software.execute();
				if (Software.getInstance().getImgProcessorList().size() == 0) {
					unableOperation();
				}
				tabbedPane.remove(tabbedPane.getSelectedIndex());
			}
		});
		popupCopyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedImage img = Software.getInstance().getImgProcessorList().get(tabbedPane.getSelectedIndex())
						.getImg();
				main_software.setCommand(new createCopy(Software.getInstance().getMain_ip(), img));
				main_software.execute();
				String copyname = "copy_" + tabbedPane.getSelectedIndex();
				createTabbedPane(img, copyname);
				writeLog("Create a copy of img" + tabbedPane.getSelectedIndex());
			}
		});
		
		popup.add(popupCopyItem);
		popup.add(popupCloseItem);
		JPanel P = new JPanel();
		P.add(popup);
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(img));
		label.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					point.add(e.getX());
					point.add(e.getY());
					writeLog("Point marked:" + e.getX() + " " + e.getY() + "\n");
				}
				if (e.getButton() == MouseEvent.BUTTON3) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		P.add(label);
		JScrollPane scrollPane = new JScrollPane(P);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tabbedPane.addTab(tabName, scrollPane);
		tabbedPane.setSelectedIndex(Software.getInstance().getImgProcessorList().size() - 1);
		P.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (e.getButton() == MouseEvent.BUTTON3) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}

	private int logInModule() throws SQLException, IOException {
		int optionSelected = JOptionPane.showConfirmDialog(null,
				"Log in or register to download your record from cloud!", "Weclome", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (optionSelected != JOptionPane.OK_OPTION) {
			return 0;
		}
		int res = innerlogin();
		if (res == 0) {
			return 1;
		} else if (res == 1) {
			return 2;
		}
		return 3;
	}

	private int innerlogin() throws SQLException, IOException {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(250, 100));
		JLabel userLabel = new JLabel("User Name:");
		JTextField userText = new JTextField();
		JLabel passLabel = new JLabel("Password:");
		JPasswordField passText = new JPasswordField(20);
		panel.setLayout(null);
		userLabel.setBounds(30, 30, 80, 25);
		panel.add(userLabel);
		userText.setBounds(105, 30, 165, 25);
		panel.add(userText);
		passLabel.setBounds(30, 60, 80, 25);
		panel.add(passLabel);
		passText.setBounds(105, 60, 165, 25);
		panel.add(passText);
		String[] options = { "Register", "Log in" };
		int choice = JOptionPane.showOptionDialog(this, panel, "Welcome", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
		String userName = userText.getText();
		char[] passWord = passText.getPassword();
		String password = new String(passWord);
		if (choice == 1) {
			// log in get return value
			BufferedImage img = null;
			if (loginController.login(userName, password) == 1) {
				img = loginController.readLastData();
				if (img!=null) {
					main_software.setCommand(new readFromBuffer(null, img, "cloud_img"));
					main_software.execute();
					timer = autoSave.onLoginSuccessful();
					return 0;
				}
				return 2;
			} else {
				JOptionPane.showMessageDialog(null, "Log in fail. Check your account name and password", "Message",
						JOptionPane.WARNING_MESSAGE);
				innerlogin();
			}
		} else if (choice == 0) {
			if (loginController.register(userName, password) == 1) {
				return 1;
			}
			JOptionPane.showMessageDialog(null, "Register fail. Check your account name and password", "Message",
					JOptionPane.WARNING_MESSAGE);
			innerlogin();
		}
		return 2;
	}

}
