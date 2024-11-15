package view_dealer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import view.*;

public class MenuJPanel extends JPanel implements ActionListener{
	
	JButton b1, b2, b3;
	private static MenuJPanel instance = null;

	public static MenuJPanel getInstance() {
		if (instance == null)
			instance = new MenuJPanel();
		return instance;
	}

	private MenuJPanel() {
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setLayout(new GridLayout(13, 1));
		JLabel l1 = new JLabel("个人中心");
		this.add(l1);
		b1 = new JButton("个人信息");
		this.add(b1);
		JLabel l2 = new JLabel("订单管理");
		this.add(l2);
		b2 = new JButton("我的订单");
		this.add(b2);

		b3 = new JButton("退出登录");
		b3.setBackground(Color.gray);
		this.add(new JPanel());
		this.add(new JPanel());
		this.add(new JPanel());
		this.add(new JPanel());
		this.add(new JPanel());
		this.add(new JPanel());
		this.add(new JPanel());
		this.add(new JPanel());
		this.add(b3);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			DealerJFrame.changePanel(MyInfoManageJPanel.getInstance());
		} else if (e.getSource() == b2) {
			DealerJFrame.changePanel(MyOrderManageJPanel.getInstance());
		} else if (e.getSource() == b3) {
			LoginJFrame.getInstance().setVisible(true);
			DealerJFrame.getInstance(null).setVisible(false);
		}
	}

}
