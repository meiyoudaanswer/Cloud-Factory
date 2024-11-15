package view_factoryManager;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.*;

public class FactoryManagerJFrame extends JFrame{
	
	private static FactoryManagerJFrame instance = null;
	private JPanel contentPane;
	private User factoryManager;

	public static FactoryManagerJFrame getInstance(User factoryManager) {
		if (instance == null)
			instance = new FactoryManagerJFrame();
		if (factoryManager != null) {
			instance.factoryManager = factoryManager;
			instance.setTitle("云工厂管理员  " + factoryManager.getName() + "的界面");
		}
		return instance;
	}

	public User getFactoryManager() {
		return factoryManager;
	}

	private FactoryManagerJFrame() {
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
		JPanel contentPane = FactoryManagerJFrame.getInstance(null).contentPane;
		contentPane.removeAll();
		contentPane.add(MenuJPanel.getInstance(), BorderLayout.WEST);
		contentPane.add(jpanel, BorderLayout.CENTER);
		FactoryManagerJFrame.getInstance(null).setContentPane(contentPane);
	}

}
