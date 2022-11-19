package Java.Gui;

import javax.swing.*;

public class loginGui extends JFrame {
	private JFrame frame = new JFrame("Welcom");
	private JPanel panel = new JPanel();
	private JLabel userLabel = new JLabel("User:"); // 创建UserJLabel
	private JTextField userText = new JTextField(); // 获取登录名
	private JLabel passLabel = new JLabel("Password:"); // 创建PassJLabel
	private JPasswordField passText = new JPasswordField(20); // 密码框隐藏
	private JButton loginButton = new JButton("login"); // 创建登录按钮
	private JButton registerButton = new JButton("register"); // 创建注册按钮

	public loginGui() {
		// 设置窗体的位置及大小
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null); // 在屏幕中居中显示
		frame.add(panel); // 添加面板
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置X号后关闭
		placeComponents(panel); // 往窗体里放其他控件
		frame.setVisible(true); // 设置窗体可见
	}

	/**
	 * 面板具体布局
	 * 
	 * @param panel
	 */
	private void placeComponents(JPanel panel) {

		panel.setLayout(null); // 设置布局为 null

		// 创建 UserJLabel
		userLabel.setBounds(30, 30, 80, 25);
		panel.add(userLabel);
		// 创建文本域用于用户输入
		userText.setBounds(105, 30, 165, 25);
		panel.add(userText);

		// 创建PassJLabel
		passLabel.setBounds(30, 60, 80, 25);
		panel.add(passLabel);
		// 密码输入框 隐藏
		passText.setBounds(105, 60, 165, 25);
		panel.add(passText);

		// 创建登录按钮
		loginButton.setBounds(25, 100, 80, 25);
		panel.add(loginButton);
		registerButton.setBounds(190, 100, 80, 25);
		panel.add(registerButton);
	}

}
