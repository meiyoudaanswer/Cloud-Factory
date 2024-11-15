package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import controller.*;

public class LoginJFrame extends JFrame implements ActionListener{
	
	private JMenuItem mi21, mi22;
//单例模式	
	private static LoginJFrame instance = null;
	public static LoginJFrame getInstance() {
		if (instance == null)
			instance = new LoginJFrame();
		return instance;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginJFrame frame = LoginJFrame.getInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private LoginJFrame() {
	//初始页面
		this.setTitle("Liya制作");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.setContentPane(LoginJPanel.getInstance());
		SystemLoadDownController systemLoadDownController = new SystemLoadDownController();

		JMenuBar mb = new JMenuBar();
		JMenu m2 = new JMenu("作者有话说");
		
		mi21 = new JMenuItem("开心的话");
		mi22 = new JMenuItem("难过的话");
		
		ImageIcon background = new ImageIcon("img/background.jpg");
		Image imge = background.getImage();
		background.setImage(imge.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING));
		JLabel l = new JLabel(background);
		l.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().add(l, new Integer(Integer.MIN_VALUE));
		((JPanel) this.getContentPane()).setOpaque(false);
		

		JMenu m11 = new JMenu("系统管理员");
		JMenu m12 = new JMenu("云工厂管理员");
		JMenu m13 = new JMenu("经销商");
		JMenuItem mi111 = new JMenuItem("用户管理");
		JMenuItem mi112 = new JMenuItem("云工厂信息");
		JMenuItem mi113 = new JMenuItem("产品类别管理");
		JMenuItem mi114 = new JMenuItem("产品信息管理");
		JMenuItem mi115 = new JMenuItem("设备类别管理");
		JMenuItem mi116 = new JMenuItem("设备管理");
		JMenuItem mi117 = new JMenuItem("订单基本信息（未实现）");

		JMenuItem mi121 = new JMenuItem("个人信息");
		JMenuItem mi122 = new JMenuItem("我的设备");
		JMenuItem mi123 = new JMenuItem("我的订单（未实现）");
		JMenuItem mi124 = new JMenuItem("订单接单（未实现）");
		JMenuItem mi125 = new JMenuItem("订单排产（未实现）");

		JMenuItem mi131 = new JMenuItem("个人信息");
		JMenuItem mi132 = new JMenuItem("我的订单（未实现）");



		m11.add(mi111);
		m11.add(mi112);
		m11.add(mi113);
		m11.add(mi114);
		m11.add(mi115);
		m11.add(mi116);
		m11.add(mi117);
		m12.add(mi121);
		m12.add(mi122);
		m12.add(mi123);
		m12.add(mi124);
		m12.add(mi125);
		m13.add(mi131);
		m13.add(mi132);
		m2.add(mi21);
		m2.add(mi22);
		mb.add(m2);
		this.setJMenuBar(mb);
		mi21.addActionListener(this);
		mi22.addActionListener(this);
		try {
			systemLoadDownController.Init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void changeJFrame(JFrame jframe) {
		jframe.setVisible(true);	
		LoginJFrame.getInstance().setVisible(false);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mi21)
			JOptionPane.showMessageDialog(null, "居然能做出来，好厉害^w^");
		if (e.getSource() == mi22)
			JOptionPane.showMessageDialog(null, "时间太赶根本写不完啊——TAT");
	}

}
