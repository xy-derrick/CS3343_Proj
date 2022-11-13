package Java.Gui;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.*;

public class FPanel extends javax.swing.JPanel {
	private Dimension preferredSize = new Dimension();
	private Rectangle2D[] rects = new Rectangle2D[50];

	public FPanel() {
		for (int i = 0; i < rects.length; i++) {
			rects[i] = new Rectangle2D.Double(Math.random() * .8, Math.random() * .8, Math.random() * .2,
					Math.random() * .2);
		}
		addMouseWheelListener( new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				updatePreferredSize(e.getWheelRotation(), e.getPoint()); 
			} }); 
	}

	public void updatePreferredSize(int wheelRotation, Point stablePoint) {
		double scaleFactor = findScaleFactor(wheelRotation);
		scaleBy(scaleFactor);
		Point offset = findOffset(stablePoint, scaleFactor);
		offsetBy(offset);
		getParent().doLayout();
	}

	private double findScaleFactor(int wheelRotation) {
		double d = wheelRotation/2.95 ;
		return (d > 0) ? 1 / d : -d;
	}

	private void scaleBy(double scaleFactor) {
		int w = (int) (getWidth() * scaleFactor);
		int h = (int) (getHeight() * scaleFactor);
		preferredSize.setSize(w, h);
	}

	private Point findOffset(Point stablePoint, double scaleFactor) {
		int x = (int) (stablePoint.x * scaleFactor) - stablePoint.x;
		int y = (int) (stablePoint.y * scaleFactor) - stablePoint.y;
		return new Point(x, y);
	}

	private void offsetBy(Point offset) {
		Point location = getLocation();
		setLocation(location.x - offset.x, location.y - offset.y);
	}

	public Dimension getPreferredSize() {
		return preferredSize;
	}

}
