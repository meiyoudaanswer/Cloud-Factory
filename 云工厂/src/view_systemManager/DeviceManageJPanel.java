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

public class DeviceManageJPanel extends JPanel implements ActionListener{

	private JTable table;
	private Vector<Vector<Object>> vData;
	private Vector<String> vName;
	private JButton b1, b2, b3, b4, b5, b6, b7;
	private JTextField t1;
	private JComboBox<String> cb;
	private DeviceManageController controller = new DeviceManageController("DeviceManage");
//单例
	private static DeviceManageJPanel instance = null;
	public static DeviceManageJPanel getInstance() {
		if (instance == null)
			instance = new DeviceManageJPanel();
		instance.showTable();
		return instance;
	}

	private DeviceManageJPanel() {
		this.setLayout(new BorderLayout());
		JPanel jpanel = new JPanel();
		t1 = new JTextField(10);
		b1 = new JButton("新建");
		b2 = new JButton("删除");
		b3 = new JButton("修改");
		b4 = new JButton("查找");
		b5 = new JButton("确认");
		b6 = new JButton("返回");
		b7 = new JButton("远程开机/关机");

		jpanel.add(t1);
		jpanel.add(b1);
		jpanel.add(b2);
		jpanel.add(b3);
		jpanel.add(b4);
		jpanel.add(b5);
		jpanel.add(b7);
		jpanel.add(b6);
		this.add(jpanel, BorderLayout.NORTH);

		table = new JTable();
		vData = new Vector<Vector<Object>>();
		vName = new Vector<String>();
		vName.add("序号");
		vName.add("设备编号");
		vName.add("设备名称");
		vName.add("设备类别");
		vName.add("设备规格");
		vName.add("设备描述");
		vName.add("设备状态");
		vName.add("租用状态");
		vName.add("所属工厂");
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);

		JScrollPane scrollPane = new JScrollPane();
		this.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(table);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
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
			} else if (e.getSource() == b7) {
				changeState();
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
		b7.setVisible(false);

		vData.clear();
		Vector<Object> vRow = new Vector<Object>();
		vRow.add(null);
		vData.add(vRow);
		Vector<String> vName = new Vector<String>();
		vName.add("设备名称");
		vName.add("设备类别");
		vName.add("设备规格");
		vName.add("设备描述");
		DefaultTableModel model = new DefaultTableModel(vData, vName);
		table.setModel(model);
		table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cb));
		table.setRowHeight(30);
	}

	private void add() throws Exception {
		Object name = table.getValueAt(0, 0);
		Object type = table.getValueAt(0, 1);
		Object specification = table.getValueAt(0, 2);
		Object description = table.getValueAt(0, 3);
		if (name == null || type == null || specification == null || description == null) {
			JOptionPane.showMessageDialog(null, "请输入完整信息");
			return;
		}

		Device device = new Device(FactoryID.createId("Device"), name.toString(), type.toString(),
				specification.toString(), description.toString(), false, null);
		boolean res = controller.add(device);
		if (res == false) {
			JOptionPane.showMessageDialog(null, "设备名称重复，请更改");
			return;
		}
		controller.save();
		JOptionPane.showMessageDialog(null, "添加成功");
		showTable();
	}

	private void delete() throws Exception {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "请选择需要删除的设备");
			return;
		}
		int choice = JOptionPane.showConfirmDialog(null, "你确定要删除吗？", "确认对话框", JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.NO_OPTION)
			return;
		Device device = (Device) controller.queryAll().get(row);
		if (device.isPrivate()) {
			JOptionPane.showMessageDialog(null, "私有设备无法删除");
			return;
		}
		if (device.isRented()) {
			JOptionPane.showMessageDialog(null, "设备正在出租中，无法删除");
			return;
		}
		controller.delete(device.getName());
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

		Device device = (Device) controller.queryAll().get(row);
		if (device.isPrivate()) {
			JOptionPane.showMessageDialog(null, "私有设备无法修改");
			return;
		}
		if (column != 2 && column != 3 && column != 4 && column != 5) {
			showTable();
			JOptionPane.showMessageDialog(null, "您只能修改设备名称、类别、规格、描述");
			return;
		}
		if (table.getValueAt(row, column) == null) {
			JOptionPane.showMessageDialog(null, "请填入修改信息");
			return;
		}
		String changed = table.getValueAt(row, column).toString();

		boolean flag = true;
		switch (column) {
		case 2:
			if (device.getName() == changed)
				flag = false;
			else
				device.setName(changed);
			break;
		case 3:
			if (device.getType() == changed)
				flag = false;
			else
				device.setType(changed);
			break;
		case 4:
			if (device.getSpecification() == changed)
				flag = false;
			else
				device.setSpecification(changed);
			break;
		case 5:
			if (device.getDescription() == changed)
				flag = false;
			else
				device.setDescription(changed);
			break;
		}
		if (flag == false) {
			showTable();
			JOptionPane.showMessageDialog(null, "尚未修改信息\n（提示：您只能修改您选中的单元格中的信息）");
			return;
		}
		controller.replace(device.getName(), device);
		controller.save();
		t1.setText("");
		JOptionPane.showMessageDialog(null, "修改成功");
		showTable();
	}

	private void search() {
		if (t1.getText() == null || t1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "请输入设备名称");
			t1.requestFocus();
			return;
		}
		Object res = controller.search(t1.getText());
		if (res == null) {
			JOptionPane.showMessageDialog(null, "无此设备");
			t1.setText("");
			t1.requestFocus();
			return;
		}
		vData.clear();
		Device device = (Device) res;
		Vector<Object> vRow = new Vector<Object>();
		vRow.add(device.getId());
		vRow.add(device.getName());
		vRow.add(device.getType());
		vRow.add(device.getSpecification());
		vRow.add(device.getDescription());
		if (device.isOn() && !device.isProducing())
			vRow.add("闲置中");
		else if (device.isOn() && device.isProducing())
			vRow.add("生产中");
		else if (!device.isOn())
			vRow.add("已关闭");

		if (!device.isPrivate() && device.isRented())
			vRow.add("已被租用");
		else if (!device.isPrivate() && !device.isRented())
			vRow.add("未被租用");
		else if (device.isPrivate())
			vRow.add("工厂设备");
		if (device.isPrivate())
			vRow.add(device.getFactoryName());
		else
			vRow.add("superFactory");

		vData.add(vRow);
		Vector<String> vName = new Vector<String>();
		vName.add("设备编号");
		vName.add("设备名称");
		vName.add("设备类别");
		vName.add("设备规格");
		vName.add("设备描述");
		vName.add("设备状态");
		vName.add("租用状态");
		vName.add("所属工厂");
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
		b7.setVisible(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(35);
		table.getColumnModel().getColumn(3).setPreferredWidth(35);
		table.getColumnModel().getColumn(4).setPreferredWidth(35);
		table.getColumnModel().getColumn(5).setPreferredWidth(35);
		table.getColumnModel().getColumn(6).setPreferredWidth(35);
		JOptionPane.showMessageDialog(null, "查找成功");
	}

	private void changeState() throws Exception {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "请选择需要开机/关机的设备");
			return;
		}
		int choice = JOptionPane.showConfirmDialog(null, "你确定要开机/关机吗？", "确认对话框", JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.NO_OPTION)
			return;
		Device device = (Device) controller.queryAll().get(row);
		device.setOn(!device.isOn());
		controller.replace(device.getName(), device);
		controller.save();
		t1.setText("");
		if (device.isOn())
			JOptionPane.showMessageDialog(null, "开机成功");
		else
			JOptionPane.showMessageDialog(null, "关机成功");
		showTable();
	}

	private void loadType() {
		List<Object> list = new DeviceTypeManageController("DeviceTypeManage").queryAll();
		String[] s = new String[list.size()];
		int cnt = -1;
		for (Object obj : list) {
			cnt++;
			s[cnt] = ((DeviceType) obj).getName();
		}
		cb = new JComboBox<String>(s);
	}

	private void loadvDate() {
		vData.clear();
		List<Object> list = controller.queryAll();
		int cnt = 0;
		for (Object obj : list) {
			cnt++;
			Device device = (Device) obj;
			Vector<Object> vRow = new Vector<Object>();
			vRow.add(cnt);
			vRow.add(device.getId());
			vRow.add(device.getName());
			vRow.add(device.getType());
			vRow.add(device.getSpecification());
			vRow.add(device.getDescription());
			if (device.isOn() && !device.isProducing())
				vRow.add("闲置中");
			else if (device.isOn() && device.isProducing())
				vRow.add("生产中");
			else if (!device.isOn())
				vRow.add("已关闭");

			if (!device.isPrivate() && device.isRented())
				vRow.add("已被租用");
			else if (!device.isPrivate() && !device.isRented())
				vRow.add("未被租用");
			else if (device.isPrivate())
				vRow.add("工厂设备");
			if (device.isPrivate())
				vRow.add(device.getFactoryName());
			else
				vRow.add("superFactory");
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
		b7.setVisible(true);
		this.loadvDate();
		DefaultTableModel model = new DefaultTableModel(vData, vName);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(3).setPreferredWidth(35);
		table.getColumnModel().getColumn(4).setPreferredWidth(35);
		table.getColumnModel().getColumn(5).setPreferredWidth(35);
		table.getColumnModel().getColumn(6).setPreferredWidth(35);
		table.getColumnModel().getColumn(7).setPreferredWidth(35);

		this.loadType();
		table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cb));
		table.setRowHeight(30);
	}
	
}
