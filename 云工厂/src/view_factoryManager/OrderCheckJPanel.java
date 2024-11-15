package view_factoryManager;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.*;
import controller.*;

public class OrderCheckJPanel extends JPanel implements ActionListener{
	
//单例模式
	private static OrderCheckJPanel instance = null;
	public static OrderCheckJPanel getInstance() {
		if (instance == null)
			instance = new OrderCheckJPanel();
//		instance.factoryManager = FactoryManagerJFrame.getInstance(null).getFactoryManager();
//		instance.controller = new OrderCheckController("OrderCheckManage", instance.factoryManager);
//		instance.clearTable();
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
