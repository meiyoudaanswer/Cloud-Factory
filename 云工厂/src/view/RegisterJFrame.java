package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.*;
import controller.*;

public class RegisterJFrame extends JFrame implements ActionListener, ItemListener{
	
	private JPanel contentPane;
	JRadioButton rb1, rb2;
	JTextField t1, t2, t3, t4, t6, t7;
	JButton b1, b2;
	private RegisterController controller = new RegisterController();

	private static RegisterJFrame instance = null;

	public static RegisterJFrame getInstance() {
		if (instance == null)
			instance = new RegisterJFrame();
		return instance;
	}

	private RegisterJFrame() {
		this.setTitle("注册");
		setBounds(100, 100, 500, 600);
		setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		this.setContentPane(contentPane);

		ImageIcon background = new ImageIcon("img/background.jpg");
		Image imge = background.getImage();
		background.setImage(imge.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING));
		JLabel l = new JLabel(background);
		l.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().add(l, new Integer(Integer.MIN_VALUE));
		((JPanel) this.getContentPane()).setOpaque(false);
		
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(7, 1));
		JPanel p11 = new JPanel();
		JPanel p12 = new JPanel();
		JPanel p13 = new JPanel();
		JPanel p14 = new JPanel();
		JPanel p15 = new JPanel();
		JPanel p16 = new JPanel();
		JPanel p17 = new JPanel();
		JLabel l1 = new JLabel("登录账号");
		JLabel l2 = new JLabel("登录密码");
		JLabel l3 = new JLabel("真实姓名");
		JLabel l4 = new JLabel("联系方式");
		JLabel l5 = new JLabel("注册类型");
		JLabel l6 = new JLabel("工厂名称");
		JLabel l7 = new JLabel("工厂简介");
		t1 = new JTextField(20);
		t2 = new JTextField(20);
		t3 = new JTextField(20);
		t4 = new JTextField(20);
		ButtonGroup bg = new ButtonGroup();
		rb1 = new JRadioButton("云工厂管理员", true);
		rb2 = new JRadioButton("经销商", false);
		bg.add(rb1);
		bg.add(rb2);
		t6 = new JTextField(20);
		t7 = new JTextField(20);
		p11.add(l1);
		p11.add(t1);
		p12.add(l2);
		p12.add(t2);
		p13.add(l3);
		p13.add(t3);
		p14.add(l4);
		p14.add(t4);
		p15.add(l5);
		p15.add(rb1);
		p15.add(rb2);
		p16.add(l6);
		p16.add(t6);
		p17.add(l7);
		p17.add(t7);
		p1.add(p11);
		p1.add(p12);
		p1.add(p13);
		p1.add(p14);
		p1.add(p15);
		p1.add(p16);
		p1.add(p17);
		contentPane.add(p1, BorderLayout.CENTER);

		JPanel p2 = new JPanel();
		b1 = new JButton("注册");
		b2 = new JButton("返回");
		p2.add(b1);
		p2.add(b2);
		contentPane.add(p2, BorderLayout.SOUTH);

		p11.setOpaque(false);
		p12.setOpaque(false);
		p13.setOpaque(false);
		p14.setOpaque(false);
		p15.setOpaque(false);
		p16.setOpaque(false);
		p17.setOpaque(false);
		p1.setOpaque(false);
		p2.setOpaque(false);
		rb1.setOpaque(false);
		rb2.setOpaque(false);

		b1.addActionListener(this);
		b2.addActionListener(this);
		rb1.addItemListener(this);
		rb2.addItemListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == b1) {
				register();
			} else if (e.getSource() == b2) {
				this.setVisible(false);
				LoginJFrame.getInstance().setVisible(true);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	private void register() throws Exception {
		if (rb2.isSelected()) {
			if (t1.getText().isEmpty() || t2.getText().isEmpty() || t3.getText().isEmpty() || t4.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入完整信息");
				return;
			}
			Dealer dealer = new Dealer(t1.getText(), t2.getText(), t3.getText(), t4.getText(), "dealer");
			boolean res = controller.add(dealer);
			if (res == false) {
				JOptionPane.showMessageDialog(null, "账号重复，请更改");
				return;
			}
		} else if (rb1.isSelected()) {
			if (t1.getText().isEmpty() || t2.getText().isEmpty() || t3.getText().isEmpty() || t4.getText().isEmpty()
					|| t6.getText().isEmpty() || t7.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入完整信息");
				return;
			}
			FactoryManager factoryManager = new FactoryManager(t1.getText(), t2.getText(), t3.getText(), t4.getText(),
					"factoryManager", t6.getText(), t7.getText());
			boolean res = controller.add(factoryManager);
			if (res == false) {
				JOptionPane.showMessageDialog(null, "账号重复，请更改");
				return;
			}
		}
		controller.save();
		t1.setText("");
		t2.setText("");
		t3.setText("");
		t4.setText("");
		t6.setText("");
		t7.setText("");
		this.setVisible(false);
		LoginJFrame.getInstance().setVisible(true);
		JOptionPane.showMessageDialog(null, "注册成功");
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == rb1) {
			t6.setBackground(Color.white);
			t6.setEditable(true);
			t7.setBackground(Color.white);
			t7.setEditable(true);
		} else if (e.getSource() == rb2) {
			t6.setBackground(Color.LIGHT_GRAY);
			t6.setEditable(false);
			t7.setBackground(Color.LIGHT_GRAY);
			t7.setEditable(false);
		}

	}

}
