package view_factoryManager;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
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

public class MyDeviceManageJPanel extends JPanel implements ActionListener{
	
	private JTable table;
	private Vector<Vector<Object>> vData;
	private Vector<String> vName, vName0;
	private static MyDeviceManageJPanel instance = null;
	private JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14;
	private JTextField t1;
	private JComboBox<String> cbDeviceType, cbProduct;
	private Device currentDevice;
	private User factoryManager = FactoryManagerJFrame.getInstance(null).getFactoryManager();
	private MyDeviceManageController controller = new MyDeviceManageController("MyDeviceManage", factoryManager);
	private DeviceManageController deviceManageController = new DeviceManageController("DeviceManage");

	public static MyDeviceManageJPanel getInstance() {
		if (instance == null)
			instance = new MyDeviceManageJPanel();
		instance.factoryManager = FactoryManagerJFrame.getInstance(null).getFactoryManager();
		instance.controller = new MyDeviceManageController("MyDeviceManage", instance.factoryManager);
		instance.showTable();
		return instance;
	}

	private MyDeviceManageJPanel() {
		this.setLayout(new BorderLayout());
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new GridLayout(2, 1));
		JPanel jpanel1 = new JPanel();
		JPanel jpanel2 = new JPanel();
		t1 = new JTextField(10);
		b1 = new JButton("新建");
		b2 = new JButton("删除");
		b3 = new JButton("修改");
		b4 = new JButton("查找");
		b5 = new JButton("确认");
		b6 = new JButton("返回");
		b7 = new JButton("远程开机/关机");
		b8 = new JButton("租用设备");
		b9 = new JButton("配置产能");
		b10 = new JButton("租用");
		b11 = new JButton("添加产品");
		b12 = new JButton("删除产品");
		b13 = new JButton("确定");
		b14 = new JButton("查看产能");

		jpanel1.add(t1);
		jpanel1.add(b1);
		jpanel1.add(b2);
		jpanel1.add(b3);
		jpanel1.add(b4);
		jpanel2.add(b5);
		jpanel2.add(b7);
		jpanel2.add(b8);
		jpanel2.add(b9);
		jpanel2.add(b14);
		jpanel2.add(b10);
		jpanel2.add(b11);
		jpanel2.add(b12);
		jpanel2.add(b13);
		jpanel2.add(b6);
		jpanel.add(jpanel1);
		jpanel.add(jpanel2);
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
		vName.add("设备来源");
		vName.add("所属工厂");
		vName0 = new Vector<String>();
		vName0.add("产品名称");
		vName0.add("产品产能（件/小时）");
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
		b8.addActionListener(this);
		b9.addActionListener(this);
		b10.addActionListener(this);
		b11.addActionListener(this);
		b12.addActionListener(this);
		b13.addActionListener(this);
		b14.addActionListener(this);
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
			} else if (e.getSource() == b8) {
				showRentTable();
			} else if (e.getSource() == b9) {
				locateDevice_1();
			} else if (e.getSource() == b10) {
				rent();
			} else if (e.getSource() == b11) {
				addLine();
			} else if (e.getSource() == b12) {
				deleteLine();
			} else if (e.getSource() == b13) {
				addProductionCapacity();
			} else if (e.getSource() == b14) {
				locateDevice_2();
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	private void locateDevice_1() {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "请选择需要配置产能的设备");
			return;
		}
		currentDevice = (Device) controller.queryAll().get(row);
		if (currentDevice.getProductionCapacity().size() > 0) {
			JOptionPane.showMessageDialog(null, "该设备已经配置过产能了");
			return;
		}
		showProductionCapacityTable_1();
	}

	private void showProductionCapacityTable_1() {
		t1.setText("");
		t1.setVisible(false);
		b1.setVisible(false);
		b2.setVisible(false);
		b3.setVisible(false);
		b4.setVisible(false);
		b5.setVisible(false);
		b7.setVisible(false);
		b8.setVisible(false);
		b9.setVisible(false);
		b10.setVisible(false);
		b11.setVisible(true);
		b12.setVisible(true);
		b13.setVisible(true);
		b14.setVisible(false);
		vData.clear();
		Vector<Object> vRow = new Vector<Object>();
		vRow.add(null);
		vData.add(vRow);
		DefaultTableModel model = new DefaultTableModel(vData, vName0);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cbProduct));
		table.setRowHeight(30);
	}

	private void locateDevice_2() {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "请选择需要查看的设备");
			return;
		}
		if (currentDevice.getProductionCapacity().size() == 0) {
			JOptionPane.showMessageDialog(null, "该设备尚未配置产能");
			return;
		}
		currentDevice = (Device) controller.queryAll().get(row);
		showProductionCapacityTable_2();
	}

	private void showProductionCapacityTable_2() {
		t1.setVisible(false);
		b1.setVisible(false);
		b2.setVisible(false);
		b3.setVisible(false);
		b4.setVisible(false);
		b5.setVisible(false);
		b7.setVisible(false);
		b8.setVisible(false);
		b9.setVisible(false);
		b10.setVisible(false);
		b11.setVisible(false);
		b12.setVisible(false);
		b13.setVisible(false);
		b14.setVisible(false);

		vData.clear();
		Map<String, Integer> map = currentDevice.getProductionCapacity();
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			Vector<Object> vRow = new Vector<Object>();
			vRow.add(entry.getKey());
			vRow.add(entry.getValue());
			vData.add(vRow);
		}
		DefaultTableModel model = new DefaultTableModel(vData, vName0);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cbProduct));
		table.setRowHeight(30);
	}

	private void addLine() {
		Vector<Object> vRow = new Vector<Object>();
		vRow.add(null);
		vData.add(vRow);
		DefaultTableModel model = new DefaultTableModel(vData, vName0);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cbProduct));
		table.setRowHeight(30);
	}

	private void deleteLine() {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "请选择需要删除的产品产能项");
			return;
		}
		vData.remove(row);
		DefaultTableModel model = new DefaultTableModel(vData, vName0);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cbProduct));
		table.setRowHeight(30);
	}

	private void addProductionCapacity() throws Exception {
		int row = vData.size();
		int column = vName0.size();
		boolean flag = true;
		for (int i = 0; i < row; ++i)
			for (int j = 0; j < column; ++j)
				if (table.getValueAt(i, j) == null) {
					flag = false;
					break;
				}
		if (flag == false) {
			JOptionPane.showMessageDialog(null, "请填写完整信息");
			return;
		}

		for (int i = 0; i < row; ++i) {
			String productName = table.getValueAt(i, 0).toString();
			int num = Integer.parseInt(table.getValueAt(i, 1).toString());
			currentDevice.addProductionCapacity(productName, num);
		}
		controller.replace(currentDevice.getName(), currentDevice);
		controller.save();
		this.showTable();
		JOptionPane.showMessageDialog(null, "产能配置成功");
	}

	private void showAddTable() {
		t1.setVisible(false);
		b1.setVisible(false);
		b2.setVisible(false);
		b3.setVisible(false);
		b4.setVisible(false);
		b5.setVisible(true);
		b7.setVisible(false);
		b8.setVisible(false);
		b9.setVisible(false);
		b10.setVisible(false);
		b11.setVisible(false);
		b12.setVisible(false);
		b13.setVisible(false);
		b14.setVisible(false);

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
		table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cbDeviceType));
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
				specification.toString(), description.toString(), true, factoryManager.getFactoryName());
		boolean res = controller.add(device);
		if (res == false) {
			JOptionPane.showMessageDialog(null, "设备名称重复，请更改");
			return;
		}
		controller.save();
		JOptionPane.showMessageDialog(null, "添加成功");
		showTable();
	}

	private void showRentTable() {
		t1.setVisible(false);
		b1.setVisible(false);
		b2.setVisible(false);
		b3.setVisible(false);
		b4.setVisible(false);
		b5.setVisible(false);
		b7.setVisible(false);
		b8.setVisible(false);
		b9.setVisible(false);
		b10.setVisible(true);
		b11.setVisible(false);
		b12.setVisible(false);
		b13.setVisible(false);
		b14.setVisible(false);

		vData.clear();
		List<Object> list = deviceManageController.queryAll();
		int cnt = 0;
		for (Object obj : list) {
			Device device = (Device) obj;
			if (device.isPrivate() || device.isRented())
				continue;
			cnt++;
			Vector<Object> vRow = new Vector<Object>();
			vRow.add(cnt);
			vRow.add(device.getId());
			vRow.add(device.getName());
			vRow.add(device.getType());
			vRow.add(device.getSpecification());
			vRow.add(device.getDescription());
			if (device.isOn())
				vRow.add("闲置中");
			else
				vRow.add("已关闭");
			vRow.add(device.getFactoryName());
			vData.add(vRow);
		}
		Vector<String> vName = new Vector<String>();
		vName.add("序号");
		vName.add("设备编号");
		vName.add("设备名称");
		vName.add("设备类别");
		vName.add("设备规格");
		vName.add("设备描述");
		vName.add("设备状态");
		vName.add("所属工厂");
		DefaultTableModel model = new DefaultTableModel(vData, vName);
		table.setModel(model);
		table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cbDeviceType));
		table.setRowHeight(30);
	}

	private void rent() throws Exception {
		int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "请选择需要租用的设备");
			return;
		}
		int choice = JOptionPane.showConfirmDialog(null, "你确定要租用这台设备吗？", "确认对话框", JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.NO_OPTION)
			return;
		String deviceName = table.getValueAt(row, 2).toString();
		Device device = (Device) deviceManageController.search(deviceName);
		device.setFactoryName(factoryManager.getFactoryName());
		device.setRented(true);
		deviceManageController.replace(deviceName, device);
		controller.save();
		JOptionPane.showMessageDialog(null, "租用成功");
		showRentTable();
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
		b8.setVisible(false);
		b9.setVisible(false);
		b10.setVisible(false);
		b11.setVisible(false);
		b12.setVisible(false);
		b13.setVisible(false);
		b14.setVisible(false);
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
		cbDeviceType = new JComboBox<String>(s);
		list = new ProductManageController("ProductManage").queryAll();
		s = new String[list.size()];
		cnt = -1;
		for (Object obj : list) {
			cnt++;
			s[cnt] = ((Product) obj).getName();
		}
		cbProduct = new JComboBox<String>(s);
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

			if (device.isPrivate())
				vRow.add("自有设备");
			else
				vRow.add("租用设备");
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
		b8.setVisible(true);
		b9.setVisible(true);
		b10.setVisible(false);
		b11.setVisible(false);
		b12.setVisible(false);
		b13.setVisible(false);
		b14.setVisible(true);
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
		table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cbDeviceType));
		table.setRowHeight(30);
	}

}
