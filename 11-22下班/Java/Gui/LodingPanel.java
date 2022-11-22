//package Java.Gui;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.RenderingHints;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.*;
//
//public class LodingPanel extends JPanel {
//	private Timer timer;
//	private int delay;
//	private int startAngle;
//	private int arcAngle = 0;
//	private int orientation;
//	public static final int CLOCKWISE = 0;
//	public static final int ANTICLOCKWISE = 1;
//	private static JDialog jdialog;
//
//	public LodingPanel() {
//		this.delay = 50;
//		// this.setLayout(new BorderLayout());
//		this.orientation = CLOCKWISE;
//		init();
//	}
//
//	public LodingPanel(int delay) {
//		this.delay = delay;
//		this.orientation = CLOCKWISE;
//		init();
//	}
//
//	public LodingPanel(int delay, int orientation) {
//		this.delay = delay;
//		this.orientation = orientation;
//		init();
//	}
//
//	@Override
//	public void show() {
//		this.timer.start();
//	}
//
//	/**
//	 * @param orientation set the direction of rotation
//	 * 
//	 * @beaninfo enum: CLOCKWISE LodingPanel.CLOCKWISE ANTICLOCKWISE
//	 *           LodingPanel.ANTICLOCKWISE
//	 */
//	public void setOrientation(int orientation) {
//		this.orientation = orientation;
//	}
//
//	private void init() {
//		this.timer = new Timer(delay, new ReboundListener());
//	}
//
//	@Override
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		drawArc(g);
//	}
//
//	private void drawArc(Graphics g) {
//		Graphics2D g2d = (Graphics2D) g.create();
//		// 抗锯齿
//		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		int width = getWidth();
//		int height = getHeight();
//		// 设置画笔颜色
//		g2d.setColor(Color.WHITE);
//		g2d.drawArc(width / 2 - 110, height / 2 - 110, 20 + 200, 20 + 200, 0, 360);
//		g2d.setColor(Color.RED);
//		g2d.fillArc(width / 2 - 110, height / 2 - 110, 20 + 200, 20 + 200, startAngle, arcAngle);
//		g2d.setColor(Color.WHITE);
//		g2d.fillArc(width / 2 - 105, height / 2 - 105, 20 + 190, 20 + 190, 0, 360);
//		g2d.dispose();
//	}
//
//	private class ReboundListener implements ActionListener {
//		private int o = 0;
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			if (startAngle < 360) {
//				switch (orientation) {
//				case CLOCKWISE:
//					startAngle = startAngle + 5;
//					break;
//				case ANTICLOCKWISE:
//					startAngle = startAngle - 5;
//					break;
//				default:
//					startAngle = startAngle + 5;
//					break;
//				}
//			} else {
//				startAngle = 0;
//			}
//			if (o == 0) {
//				if (arcAngle >= 355) {
//					o = 1;
//					orientation = ANTICLOCKWISE;
//				} else {
//					if (orientation == CLOCKWISE) {
//						arcAngle += 5;
//					}
//				}
//			} else {
//				if (arcAngle <= 5) {
//					o = 0;
//					orientation = CLOCKWISE;
//				} else {
//					if (orientation == ANTICLOCKWISE) {
//						arcAngle -= 5;
//					}
//				}
//			}
//			repaint();
//		}
//	}
//
//	public static void startLoading() {
//		JDialog JD = new JDialog();
//		LodingPanel LP = new LodingPanel();
//		JD.add(LP);
//		LP.setBackground(Color.WHITE);
//		LP.show();
//		JD.setModal(true);
//		JD.setSize(260, 270);
//		JD.setLocationRelativeTo(null);
//		// JD.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		// JD.setUndecorated(true);
//		jdialog = JD;
//		JD.setVisible(true);
//	}
//
//	public static void endLoading() {
//		jdialog.setVisible(false);
//		jdialog.dispose();
//	}
//}
