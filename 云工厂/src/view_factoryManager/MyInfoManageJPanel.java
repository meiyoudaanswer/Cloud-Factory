package view_factoryManager;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.table.*;

import controller.*;
import model.*;

public class MyInfoManageJPanel extends JPanel implements ActionListener{
	
	private JTable table;
	private Vector<Vector<Object>> vData;
	private Vector<String> vName;
	private static MyInfoManageJPanel instance = null;
	private JButton b1;
	private JComboBox<String> cb;
	private UserManageController controller = new UserManageController("UserManage");
	private User user = FactoryManagerJFrame.getInstance(null).getFactoryManager();

	public static MyInfoManageJPanel getInstance() {
		if (instance == null)
			instance = new MyInfoManageJPanel();
		instance.user = FactoryManagerJFrame.getInstance(null).getFactoryManager();
		instance.showTable();
		return instance;
	}

	private MyInfoManageJPanel() {
		this.setLayout(new BorderLayout());
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new BorderLayout());
		JLabel l1 = new JLabel("个人信息：");
		b1 = new JButton("修改信息");
		jpanel.add(l1, BorderLayout.CENTER);
		jpanel.add(b1, BorderLayout.EAST);
		this.add(jpanel, BorderLayout.NORTH);
		cb = new JComboBox<String>(new String[] { "factoryManager", "dealer" });

		table = new JTable();
		vData = new Vector<Vector<Object>>();
		vName = new Vector<String>();
		vName.add("登录账号");
		vName.add("姓名");
		vName.add("联系方式");
		vName.add("角色类型");

		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);

		JScrollPane scrollPane = new JScrollPane();
		this.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(table);

		this.showTable();

		b1.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {

		try {
			if (e.getSource() == b1) {
				replace();
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	private void replace() throws Exception {
		int row = table.getSelectedRow();
		int column = table.getSelectedColumn();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "请选择需要修改单元格");
			return;
		}
		if (column == 0 || column == 3) {
			showTable();
			JOptionPane.showMessageDialog(null, "账号和角色类型无法修改");
			return;
		}
		if (table.getValueAt(row, column) == null) {
			JOptionPane.showMessageDialog(null, "请填入修改信息");
			return;
		}
		String changed = table.getValueAt(row, column).toString();
		boolean flag = true;
		switch (column) {
		case 1:
			if (user.getName() == changed)
				flag = false;
			else
				user.setName(changed);
			break;
		case 2:
			if (user.getContactInfo() == changed)
				flag = false;
			else
				user.setContactInfo(changed);
			break;
		}
		if (flag == false) {
			showTable();
			JOptionPane.showMessageDialog(null, "尚未修改信息\n（提示：您只能修改您选中的单元格中的信息）");
			return;
		}
		controller.replace(user.getAccount(), user);
		controller.save();
		JOptionPane.showMessageDialog(null, "修改成功");
		showTable();
	}

	private void loadvDate() {
		vData.clear();
		Vector<Object> vRow = new Vector<Object>();
		vRow.add(user.getAccount());
		vRow.add(user.getName());
		vRow.add(user.getContactInfo());
		vRow.add(user.getType());
		vData.add(vRow);

	}

	private void showTable() {
		this.loadvDate();
		DefaultTableModel model = new DefaultTableModel(vData, vName);
		table.setModel(model);
		table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cb));
		table.setRowHeight(30);
	}

}
