package Java.Gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class HeaderPanel extends JPanel {
	private int height = 50;
	private Color bgColor;
	private boolean isShow;

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(this.getWidth(), height);
	}

	@Override
	public Dimension getSize() {
		return new Dimension(this.getWidth(), height);
	}

	public HeaderPanel(Color bgColor) {
		this.bgColor = bgColor;
		this.isShow = true;
	}

	public HeaderPanel(Color bgColor, String title, int height) {
		this(bgColor);
		this.height = height;
		// this.add(new JLabel("Filter"));
		Border blackline = BorderFactory.createLineBorder(Color.black);
		TitledBorder border = new TitledBorder(title);
		border.setBorder(new LineBorder(Color.black));
		setBorder(border);
	}
}
