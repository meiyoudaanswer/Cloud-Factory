package view_systemManager;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SystemManagerJFrame extends JFrame{
	
	private static SystemManagerJFrame instance = null;
	private JPanel contentPane;

	public static SystemManagerJFrame getInstance() {
		if (instance == null)
			instance = new SystemManagerJFrame();
		return instance;
	}

	private SystemManagerJFrame() {
		this.setTitle("系统管理员界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(MenuJPanel.getInstance(), BorderLayout.WEST);
		JPanel p = new JPanel();
		contentPane.add(p, BorderLayout.CENTER);
		this.setContentPane(contentPane);
		this.setVisible(true);
	}

	public static void changePanel(JPanel jpanel) {
		JPanel contentPane = SystemManagerJFrame.getInstance().contentPane;
		contentPane.removeAll();
		contentPane.add(MenuJPanel.getInstance(), BorderLayout.WEST);
		contentPane.add(jpanel, BorderLayout.CENTER);
		SystemManagerJFrame.getInstance().setContentPane(contentPane);
	}

}
