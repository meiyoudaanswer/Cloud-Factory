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

public class DeviceTypeManageJPanel extends JPanel implements ActionListener{
	
	private JTable table;
	private Vector<Vector<Object>> vData;
	private Vector<String> vName;
	private static DeviceTypeManageJPanel instance = null;
	private JButton b1, b2, b3, b4, b5, b6;
	private JTextField t1;
	private DeviceTypeManageController controller = new DeviceTypeManageController("DeviceTypeManage");
	private DeviceManageController deviceManageController = new DeviceManageController("DeviceManage");

	public static DeviceTypeManageJPanel getInstance() {
		if (instance == null)
			instance = new DeviceTypeManageJPanel();
		return instance;
	}

	private DeviceTypeManageJPanel() {
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

		table = new JTable();
		vData = new Vector<Vector<Object>>();
		vName = new Vector<String>();
		vName.add("序号");
		vName.add("类型名称");
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
		vData.add(vRow);
		Vector<String> vName = new Vector<String>();
		vName.add("类型名称");
		DefaultTableModel model = new DefaultTableModel(vData, vName);
		table.setModel(model);
		table.setRowHeight(30);
	}

	private void add() throws Exception {
		Object name = table.getValueAt(0, 0);
		if (name == null) {
			JOptionPane.showMessageDialog(null, "请输入完整信息");
			return;
		}

		DeviceType deviceType = new DeviceType(name.toString());
		boolean res = controller.add(deviceType);
		if (res == false) {
			JOptionPane.showMessageDialog(null, "设备类型名称重复，请更改");
			return;
		}
		controller.save();
		JOptionPane.showMessageDialog(null, "添加成功");
		showTable();
	}

	private void delete() throws Exception {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "请选择需要删除的设备类型");
			return;
		}
		DeviceType deviceType = (DeviceType) controller.queryAll().get(row);
		if (controller.queryAllCitedDevice(deviceType.getName()).size() != 0) {
			JOptionPane.showMessageDialog(null, "该设备类型已被引用，无法删除");
			return;
		}
		int choice = JOptionPane.showConfirmDialog(null, "你确定要删除吗？", "确认对话框", JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.NO_OPTION)
			return;

		controller.delete(deviceType.getName());
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
		if (column == 0) {
			showTable();
			JOptionPane.showMessageDialog(null, "序号无法修改");
			return;
		}
		if (table.getValueAt(row, column) == null) {
			JOptionPane.showMessageDialog(null, "请填入修改信息");
			return;
		}
		String changed = table.getValueAt(row, column).toString();
		DeviceType deviceType = (DeviceType) controller.queryAll().get(row);

		if (deviceType.getName() == changed) {
			showTable();
			JOptionPane.showMessageDialog(null, "尚未修改信息\n（提示：您只能修改您选中的单元格中的信息）");
			return;
		}

		// 修改 被引用的设备 的设备类型
		for (Object obj : controller.queryAllCitedDevice(deviceType.getName())) {
			Device device = (Device) obj;
			device.setType(changed);
			deviceManageController.replace(device.getName(), device);
		}
		deviceManageController.save();

		controller.replace(deviceType.getName(), new DeviceType(changed));
		controller.save();
		t1.setText("");
		JOptionPane.showMessageDialog(null, "修改成功");
		showTable();
	}

	private void search() {
		if (t1.getText() == null || t1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入设备类型名称");
			t1.requestFocus();
			return;
		}
		Object res = controller.search(t1.getText());
		if (res == null) {
			JOptionPane.showMessageDialog(null, "无此设备类型");
			t1.setText("");
			t1.requestFocus();
			return;
		}
		vData.clear();
		DeviceType deviceType = (DeviceType) res;
		Vector<Object> vRow = new Vector<Object>();
		vRow.add(deviceType.getName());
		vData.add(vRow);
		Vector<String> vName = new Vector<String>();
		vName.add("类型名称");
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
			DeviceType deviceType = (DeviceType) obj;
			Vector<Object> vRow = new Vector<Object>();
			vRow.add(cnt);
			vRow.add(deviceType.getName());
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
		table.setRowHeight(30);
	}

}
