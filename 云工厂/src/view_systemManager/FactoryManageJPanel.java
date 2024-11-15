package view_systemManager;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.*;
import controller.*;

public class FactoryManageJPanel extends JPanel implements ActionListener{
	
	private JTable table;
	private Vector<Vector<Object>> vData;
	private Vector<String> vName;
	private static FactoryManageJPanel instance = null;
	private JButton b4, b6, b7;
	private JTextField t1;
	private FactoryManageController controller = new FactoryManageController("FactoryManage");
	private UserManageController userController = new UserManageController("UserManage");

	public static FactoryManageJPanel getInstance() {
		if (instance == null)
			instance = new FactoryManageJPanel();
		instance.showTable();
		return instance;
	}

	private FactoryManageJPanel() {
		this.setLayout(new BorderLayout());
		JPanel jpanel = new JPanel();
		t1 = new JTextField(10);

		b4 = new JButton("查找");
		b6 = new JButton("返回");
		b7 = new JButton("远程开启/关停");

		jpanel.add(t1);
		jpanel.add(b4);
		jpanel.add(b7);
		jpanel.add(b6);
		this.add(jpanel, BorderLayout.NORTH);

		table = new JTable();
		vData = new Vector<Vector<Object>>();
		vName = new Vector<String>();
		vName.add("序号");
		vName.add("工厂名称");
		vName.add("工厂简介");
		vName.add("负责人");
		vName.add("联系方式");
		vName.add("登录账号");
		vName.add("工厂状态");
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);

		JScrollPane scrollPane = new JScrollPane();
		this.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(table);

		b4.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {

		try {
			if (e.getSource() == b4) {
				search();
			} else if (e.getSource() == b6) {
				showTable();
			} else if (e.getSource() == b7) {
				changeState();
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	private void search() {
		if (t1.getText() == null || t1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入工厂名称");
			t1.requestFocus();
			return;
		}
		Object res = controller.search(t1.getText());
		if (res == null) {
			JOptionPane.showMessageDialog(null, "无此工厂");
			t1.setText("");
			t1.requestFocus();
			return;
		}
		vData.clear();
		Factory factory = (Factory) res;
		Vector<Object> vRow = new Vector<Object>();
		vRow.add(factory.getName());
		vRow.add(factory.getDescription());
		vRow.add(factory.getAdminName());
		User factoryAdmin = (User) userController.search(factory.getAdminName());
		vRow.add(factoryAdmin.getContactInfo());
		vRow.add(factoryAdmin.getAccount());
		if (factory.isOn())
			vRow.add("正常");
		else
			vRow.add("关停");

		vData.add(vRow);
		Vector<String> vName = new Vector<String>();
		vName.add("工厂名称");
		vName.add("工厂简介");
		vName.add("负责人");
		vName.add("联系方式");
		vName.add("登录账号");
		vName.add("工厂状态");
		DefaultTableModel model = new DefaultTableModel(vData, vName);
		table.setModel(model);
		table.setRowHeight(30);
		t1.setText("");
		t1.setVisible(false);
		b4.setVisible(false);
		b7.setVisible(false);
		JOptionPane.showMessageDialog(null, "查找成功");
	}

	private void changeState() throws Exception {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "请选择需要开启/关停的工厂");
			return;
		}
		int choice = JOptionPane.showConfirmDialog(null, "你确定要开启/关停吗？", "确认对话框", JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.NO_OPTION)
			return;
		Factory factory = (Factory) controller.queryAll().get(row);
		factory.setOn(!factory.isOn());
		controller.replace(factory.getName(), factory);
		controller.save();
		t1.setText("");
		if (factory.isOn())
			JOptionPane.showMessageDialog(null, "开启成功");
		else
			JOptionPane.showMessageDialog(null, "关停成功");
		showTable();
	}

	private void loadvDate() {
		vData.clear();
		List<Object> list = controller.queryAll();
		int cnt = 0;
		for (Object obj : list) {
			cnt++;
			Factory factory = (Factory) obj;
			Vector<Object> vRow = new Vector<Object>();
			vRow.add(cnt);
			vRow.add(factory.getName());
			vRow.add(factory.getDescription());
			vRow.add(factory.getAdminName());
			User factoryAdmin = (User) userController.search(factory.getAdminName());
			vRow.add(factoryAdmin.getContactInfo());
			vRow.add(factoryAdmin.getAccount());
			if (factory.isOn())
				vRow.add("正常");
			else
				vRow.add("关停");
			vData.add(vRow);
		}
	}

	private void showTable() {
		t1.setText("");
		t1.setVisible(true);
		b4.setVisible(true);
		b7.setVisible(true);
		this.loadvDate();
		DefaultTableModel model = new DefaultTableModel(vData, vName);
		table.setModel(model);
		table.setRowHeight(30);
	}

}
