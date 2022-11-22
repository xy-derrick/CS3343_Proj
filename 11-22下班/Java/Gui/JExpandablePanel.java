package Java.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JExpandablePanel extends JPanel {
	private HeaderPanel headerPanel;
	private ContentPanel contentPanel;

	public JExpandablePanel(Color theme, String title, int height, ArrayList<JComponent> JComList) {
		super();
		this.setLayout(new BorderLayout());
		headerPanel = new HeaderPanel(theme, title, height);
		headerPanel.addMouseListener(new PanelAction());
		contentPanel = new ContentPanel(theme);
		for (JComponent JCom : JComList) {
			contentPanel.createContent(JCom);
		}
		this.add(headerPanel, BorderLayout.NORTH);
		this.add(contentPanel, BorderLayout.CENTER);
		setOpaque(false);
	}

	class PanelAction extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			HeaderPanel hp = (HeaderPanel) e.getSource();
			if (contentPanel.isShowing()) {
				contentPanel.setVisible(false);
				hp.setShow(false);
			} else {
				contentPanel.setVisible(true);
				hp.setShow(true);
			}
			hp.getParent().validate();
			hp.getParent().repaint();
		}
	}
}