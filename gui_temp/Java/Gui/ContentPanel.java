package Java.Gui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

public class ContentPanel extends JPanel {

    public ContentPanel(Color theme) {
        //this.setBorder(BorderFactory.createLineBorder(theme, 1));
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        this.setPreferredSize(new Dimension(100, 500));
    }

    public void createContent(JComponent c) {
        this.add(c, BorderLayout.CENTER);
    }
}