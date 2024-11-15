package view_dealer;

import java.awt.*;
import javax.swing.*;
import model.*;

public class DealerJFrame extends JFrame{
	
	private JPanel contentPane;
	private User dealer;
//单例模式
	private static DealerJFrame instance = null;
	public static DealerJFrame getInstance(User dealer) {
		if (instance == null)
			instance = new DealerJFrame();
		if (dealer != null) {
			instance.dealer = dealer;
			instance.setTitle("经销商  " + dealer.getName() + "的界面");
		}
		return instance;
	}

	public User getDealer() {
		return dealer;
	}

	private DealerJFrame() {
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
		JPanel contentPane = DealerJFrame.getInstance(null).contentPane;
		contentPane.removeAll();
		contentPane.add(MenuJPanel.getInstance(), BorderLayout.WEST);
		contentPane.add(jpanel, BorderLayout.CENTER);
		DealerJFrame.getInstance(null).setContentPane(contentPane);
	}

}
