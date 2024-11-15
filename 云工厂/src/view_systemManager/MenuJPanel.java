package view_systemManager;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.*;

public class MenuJPanel extends JPanel implements ActionListener{
	
	JButton b1, b2, b3, b4, b5, b6, b7, b8;
	private static MenuJPanel instance = null;

	public static MenuJPanel getInstance() {
		if (instance == null)
			instance = new MenuJPanel();
		return instance;
	}

	private MenuJPanel() {
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setLayout(new GridLayout(13, 1));
		JLabel l1 = new JLabel("系统设置");
		this.add(l1);
		b1 = new JButton("用户管理");
		this.add(b1);
		JLabel l2 = new JLabel("云工厂");
		this.add(l2);
		b2 = new JButton("云工厂信息");
		this.add(b2);
		JLabel l3 = new JLabel("产品管理");
		this.add(l3);
		b3 = new JButton("产品类别管理");
		this.add(b3);
		b4 = new JButton("产品信息管理");
		this.add(b4);
		JLabel l4 = new JLabel("产能中心");
		this.add(l4);
		b5 = new JButton("设备类型管理");
		this.add(b5);
		b6 = new JButton("设备管理");
		this.add(b6);
		JLabel l5 = new JLabel("订单管理");
		this.add(l5);
		b7 = new JButton("订单基本信息");
		this.add(b7);
		b8 = new JButton("退出登录");
		b8.setBackground(Color.gray);
		this.add(b8);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			SystemManagerJFrame.changePanel(UserManageJPanel.getInstance());
		} else if (e.getSource() == b2) {
			SystemManagerJFrame.changePanel(FactoryManageJPanel.getInstance());
		} else if (e.getSource() == b3) {
			SystemManagerJFrame.changePanel(ProductTypeManageJPanel.getInstance());
		} else if (e.getSource() == b4) {
			SystemManagerJFrame.changePanel(ProductManageJPanel.getInstance());
		} else if (e.getSource() == b5) {
			SystemManagerJFrame.changePanel(DeviceTypeManageJPanel.getInstance());
		} else if (e.getSource() == b6) {
			SystemManagerJFrame.changePanel(DeviceManageJPanel.getInstance());
		} else if (e.getSource() == b7) {
			SystemManagerJFrame.changePanel(OrderManageJPanel.getInstance());
		} else if (e.getSource() == b8) {
			LoginJFrame.getInstance().setVisible(true);
			SystemManagerJFrame.getInstance().setVisible(false);
		}

	}

}
