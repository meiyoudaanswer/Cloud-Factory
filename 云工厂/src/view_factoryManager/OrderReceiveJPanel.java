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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.*;
import controller.*;

public class OrderReceiveJPanel extends JPanel implements ActionListener{
	
//单例
	private static OrderReceiveJPanel instance = null;
	public static OrderReceiveJPanel getInstance() {
		if (instance == null)
			instance = new OrderReceiveJPanel();
//		instance.factoryManager = FactoryManagerJFrame.getInstance(null).getFactoryManager();
//		instance.controller = new OrderReceiveController("OrderReceive", instance.factoryManager);
//		instance.showTable();
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
