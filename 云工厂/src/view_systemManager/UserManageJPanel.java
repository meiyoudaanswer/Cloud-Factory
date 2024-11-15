package view_systemManager;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class UserManageJPanel extends JPanel implements ActionListener{
	
	private JTable table;
	private Vector<Vector<Object>> vData;
	private Vector<String> vName;
	private static UserManageJPanel instance = null;
	private JButton b1, b2, b3, b4, b5, b6;
	private JTextField t1;
	private JComboBox<String> cb;
	private UserManageController controller = new UserManageController("UserManage");

	public static UserManageJPanel getInstance() {
		if (instance == null)
			instance = new UserManageJPanel();
		instance.showTable();
		return instance;
	}

	private UserManageJPanel() {
		this.setLayout(new BorderLayout());
		JPanel jpanel = new JPanel();
		t1 = new JTextField(10);
		b1 = new JButton("新建");
		b2 = new JButton("删除");
		b3 = new JButton("修改");
		b4 = new JButton("查找");
		b5 = new JButton("确认");
		b6 = new JButton("返回");
		jpanel.add(t1);
		jpanel.add(b1);
		jpanel.add(b2);
		jpanel.add(b3);
		jpanel.add(b4);
		jpanel.add(b5);
		jpanel.add(b6);
		this.add(jpanel, BorderLayout.NORTH);
		cb = new JComboBox<String>(new String[] { "factoryManager", "dealer" });

		table = new JTable();
		vData = new Vector<Vector<Object>>();
		vName = new Vector<String>();
		vName.add("序号");
		vName.add("登录账号");
		vName.add("姓名");
		vName.add("联系方式");
		vName.add("角色名称");
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);

		JScrollPane scrollPane = new JScrollPane();
		this.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(table);

		this.showTable();

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {

		try {
			if (e.getSource() == b1) {
				showAddTable();
			} else if (e.getSource() == b2) {
				delete();
			} else if (e.getSource() == b3) {
				replace();
			} else if (e.getSource() == b4) {
				search();
			} else if (e.getSource() == b5) {
				add();
			} else if (e.getSource() == b6) {
				showTable();
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	private void showAddTable() {
		t1.setVisible(false);
		b1.setVisible(false);
		b2.setVisible(false);
		b3.setVisible(false);
		b4.setVisible(false);
		b5.setVisible(true);

		vData.clear();
		Vector<Object> vRow = new Vector<Object>();
		vRow.add(null);
		vRow.add(null);
		vRow.add(null);
		vRow.add(null);
		vRow.add(null);
		vData.add(vRow);
		Vector<String> vName = new Vector<String>();
		vName.add("登录账号");
		vName.add("密码");
		vName.add("姓名");
		vName.add("联系方式");
		vName.add("角色类型");
		DefaultTableModel model = new DefaultTableModel(vData, vName);
		table.setModel(model);
		table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(cb));
		table.setRowHeight(30);
	}

	private void add() throws Exception {
		Object account = table.getValueAt(0, 0);
		Object password = table.getValueAt(0, 1);
		Object name = table.getValueAt(0, 2);
		Object contactInfo = table.getValueAt(0, 3);
		Object type = table.getValueAt(0, 4);
		if (account == null || password == null || name == null || contactInfo == null || type == null) {
			JOptionPane.showMessageDialog(null, "请输入完整信息");
			return;
		}
		User user = new User(account.toString(), password.toString(), name.toString(), contactInfo.toString(),
				type.toString());
		boolean res = controller.add(user);
		if (res == false) {
			JOptionPane.showMessageDialog(null, "账号重复，请更改");
			return;
		}
		controller.save();
		JOptionPane.showMessageDialog(null, "添加成功");
		showTable();
	}

	private void delete() throws Exception {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "请选择需要删除的用户");
			return;
		}
		int choice = JOptionPane.showConfirmDialog(null, "你确定要删除吗？", "确认对话框", JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.NO_OPTION)
			return;
		User user = (User) controller.queryAll().get(row);
		controller.delete(user.getAccount());
		controller.save();
		t1.setText("");
		JOptionPane.showMessageDialog(null, "删除成功");
		showTable();
	}

	private void replace() throws Exception {
		int row = table.getSelectedRow();
		int column = table.getSelectedColumn();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "请选择需要修改单元格");
			return;
		}
		if (column == 0 || column == 1) {
			showTable();
			JOptionPane.showMessageDialog(null, "序号和账号信息无法修改");
			return;
		}
		if (table.getValueAt(row, column) == null) {
			JOptionPane.showMessageDialog(null, "请填入修改信息");
			return;
		}
		String changed = table.getValueAt(row, column).toString();
		User user = (User) controller.queryAll().get(row);
		boolean flag = true;
		switch (column) {
		case 2:
			if (user.getName() == changed)
				flag = false;
			else
				user.setName(changed);
			break;
		case 3:
			if (user.getContactInfo() == changed)
				flag = false;
			else
				user.setContactInfo(changed);
			break;
		case 4:
			if (user.getType() == changed)
				flag = false;
			else
				user.setType(changed);
			break;
		}
		if (flag == false) {
			showTable();
			JOptionPane.showMessageDialog(null, "尚未修改信息\n（提示：您只能修改您选中的单元格中的信息）");
			return;
		}
		controller.replace(user.getAccount(), user);
		controller.save();
		t1.setText("");
		JOptionPane.showMessageDialog(null, "修改成功");
		showTable();
	}

	private void search() {
		if (t1.getText() == null || t1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入用户账号或姓名");
			t1.requestFocus();
			return;
		}
		Object res = controller.search(t1.getText());
		if (res == null) {
			JOptionPane.showMessageDialog(null, "无此用户");
			t1.setText("");
			t1.requestFocus();
			return;
		}
		vData.clear();
		User user = (User) res;
		Vector<Object> vRow = new Vector<Object>();
		vRow.add(user.getAccount());
		vRow.add(user.getName());
		vRow.add(user.getContactInfo());
		vRow.add(user.getType());
		vData.add(vRow);
		Vector<String> vName = new Vector<String>();
		vName.add("登录账号");
		vName.add("姓名");
		vName.add("联系方式");
		vName.add("角色类型");
		DefaultTableModel model = new DefaultTableModel(vData, vName);
		table.setModel(model);
		table.setRowHeight(30);
		t1.setText("");
		t1.setVisible(false);
		b1.setVisible(false);
		b2.setVisible(false);
		b3.setVisible(false);
		b4.setVisible(false);
		b5.setVisible(false);
		JOptionPane.showMessageDialog(null, "查找成功");
	}

	private void loadvDate() {
		vData.clear();
		List<Object> list = controller.queryAll();
		int cnt = 0;
		for (Object obj : list) {
			cnt++;
			User user = (User) obj;
			Vector<Object> vRow = new Vector<Object>();
			vRow.add(cnt);
			vRow.add(user.getAccount());
			vRow.add(user.getName());
			vRow.add(user.getContactInfo());
			vRow.add(user.getType());
			vData.add(vRow);
		}
	}

	private void showTable() {
		t1.setText("");
		t1.setVisible(true);
		b1.setVisible(true);
		b2.setVisible(true);
		b3.setVisible(true);
		b4.setVisible(true);
		b5.setVisible(false);
		this.loadvDate();
		DefaultTableModel model = new DefaultTableModel(vData, vName);
		table.setModel(model);
		table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(cb));
		table.setRowHeight(30);
	}

}
