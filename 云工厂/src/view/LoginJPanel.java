package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.*;
import controller.*;
import view_dealer.*;
import view_factoryManager.*;
import view_systemManager.*;

public class LoginJPanel extends JPanel implements ActionListener{
	
	private JButton b1, b2;
	private JLabel l1, l2;
	private JTextField t1;
	private JPasswordField t2;
	private LoginController controller = new LoginController();
//单例模式
	private static LoginJPanel instance = null;
	public static LoginJPanel getInstance() {
		if (instance == null)
			instance = new LoginJPanel();
		return instance;
	}

	private LoginJPanel() {
		this.setLayout(new GridLayout(2, 1));
		JPanel p01 = new JPanel();
		p01.setLayout(new GridLayout(2, 1));
		JLabel l01 = new JLabel("云工厂智能平台");
		l01.setFont(new Font("楷体", 1, 40));
		JLabel l02 = new JLabel("登录");
		l02.setFont(new Font("宋体", 1, 24));
		l01.setHorizontalAlignment(SwingConstants.CENTER);
		l02.setHorizontalAlignment(SwingConstants.CENTER);
		p01.add(l01);
		p01.add(l02);
		JPanel p02 = new JPanel();
		p02.setLayout(new GridLayout(3, 1));
		l1 = new JLabel("账号");
		l2 = new JLabel("密码");
		t1 = new JTextField(20);
		t2 = new JPasswordField(20);
		b1 = new JButton("注册");
		b2 = new JButton("登录");

		JPanel p1 = new JPanel();
		p1.add(l1);
		p1.add(t1);
		p02.add(p1);

		JPanel p2 = new JPanel();
		p2.add(l2);
		p2.add(t2);
		p02.add(p2);

		JPanel p3 = new JPanel();
		p3.add(b1);
		p3.add(b2);
		p02.add(p3);

		this.add(p01);
		this.add(p02);
		p01.setOpaque(false);
		p02.setOpaque(false);
		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);

		b1.addActionListener(this);
		b2.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			t1.setText("");
			t2.setText("");
			LoginJFrame.changeJFrame(RegisterJFrame.getInstance());
		}
		if (e.getSource() == b2)
			login();
	}

	private void login() {
		if (t1.getText().equals("") || new String(t2.getPassword()).equals("")) {
			JOptionPane.showMessageDialog(null, "账号和密码不能为空");
			return;
		}
		String account = t1.getText();
		String password = new String(t2.getPassword());
		User user = controller.login(account, password);
		if (user == null) {
			JOptionPane.showMessageDialog(null, "账号或密码错误");
			return;
		}
		t1.setText("");
		t2.setText("");
	//权限识别
		if (user.getType().equalsIgnoreCase("systemManager"))
			LoginJFrame.changeJFrame(SystemManagerJFrame.getInstance());
		else if (user.getType().equalsIgnoreCase("factoryManager"))
			LoginJFrame.changeJFrame(FactoryManagerJFrame.getInstance(user));
		else if (user.getType().equalsIgnoreCase("dealer"))
			LoginJFrame.changeJFrame(DealerJFrame.getInstance(user));
	}

}
