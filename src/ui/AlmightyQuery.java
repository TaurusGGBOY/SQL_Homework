package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dal.MySQLHelper;

public class AlmightyQuery extends JPanel {
	private JTable table;
	private HashSet<String> nameSet = new HashSet<>();
	DefaultTableModel model1;
	DefaultTableModel model2;
	List<Map<String, Object>> infoList1 = new ArrayList<Map<String, Object>>();
	List<Map<String, Object>> infoList2 = new ArrayList<Map<String, Object>>();

	Vector<Vector<String>> table1Columns = new Vector<>();
	Vector<Vector<String>> table2Columns = new Vector<>();
	Vector<String> columns1 = new Vector<>();
	Vector<String> columns2 = new Vector<>();

	JComboBox<String> comboBox = new JComboBox<>();
	JComboBox<String> comboBox_1 = new JComboBox<>();
	JComboBox<String> comboBox_2 = new JComboBox<>();

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	int chooseRow;
	private JTable table_1;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public AlmightyQuery() {
		init();
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 580, 402);
		add(scrollPane);

		table = new JTable(model1);
		table.setFont(new Font("宋体", Font.PLAIN, 20));
		table.setRowHeight(25);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.requestFocus();
		scrollPane.setViewportView(table);

		ActionMap am = table.getActionMap();
		am.getParent().remove("selectNextRowCell");
		table.setActionMap(am);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(13, 459, 289, 132);
		add(scrollPane_1);

		table_1 = new JTable(model2);
		scrollPane_1.setViewportView(table_1);

		JLabel lblNewLabel = new JLabel("\u903B\u8F91\u8FD0\u7B97\u7B26");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel.setBounds(309, 459, 85, 15);
		add(lblNewLabel);

		JLabel label = new JLabel("\u5B57\u6BB5");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(404, 459, 44, 15);
		add(label);

		JLabel label_1 = new JLabel("\u8FD0\u7B97\u7B26");
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		label_1.setBounds(474, 459, 53, 15);
		add(label_1);

		JLabel label_2 = new JLabel("\u503C");
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));
		label_2.setBounds(545, 459, 23, 15);
		add(label_2);

		comboBox.setBounds(312, 507, 75, 21);
		add(comboBox);

		comboBox_1.setBounds(389, 507, 83, 21);
		add(comboBox_1);

		comboBox_2.setBounds(474, 507, 53, 21);
		add(comboBox_2);

		JButton button = new JButton("\u6DFB\u52A0");

		button.setBounds(312, 568, 82, 23);
		add(button);

		JButton button_1 = new JButton("\u5220\u9664");

		button_1.setBounds(399, 568, 85, 23);
		add(button_1);

		textField = new JTextField();
		textField.setBounds(529, 507, 61, 21);
		add(textField);
		textField.setColumns(10);

		JButton button_2 = new JButton("\u67E5\u8BE2");

		button_2.setBounds(489, 568, 101, 23);
		add(button_2);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!textField.getText().toString().isEmpty()) {
					String[] strings = { comboBox.getSelectedItem().toString(), comboBox_1.getSelectedItem().toString(),
							comboBox_2.getSelectedItem().toString(), textField.getText().toString() };
					model2.addRow(strings);
					textField.setText("");
					try {
						table_1.setValueAt("", 0, 0);
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				new MySQLHelper().FitTableColumns(table_1);
			}
		});

		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table_1.getSelectedRow() >= 0) {
					model2.removeRow(table_1.getSelectedRow());
					try {
						table_1.setValueAt("", 0, 0);
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				new MySQLHelper().FitTableColumns(table_1);
			}
		});

		button_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (table_1.getRowCount() <= 0) {
					loadTable("select * from query1");
					return;
				}
				StringBuilder sBuilder = new StringBuilder();
				sBuilder.append("select * from query1 where ");
				for (int i = 0; i < table_1.getRowCount(); i++) {
					sBuilder.append(table_1.getValueAt(i, 0).toString());
					sBuilder.append(" ");
					sBuilder.append(table_1.getValueAt(i, 1).toString());
					sBuilder.append(" ");
					sBuilder.append(table_1.getValueAt(i, 2).toString());
					sBuilder.append(" '");
					sBuilder.append(table_1.getValueAt(i, 3).toString());
					sBuilder.append("' ");
				}
				loadTable(sBuilder.toString());
				try {
					table_1.setValueAt("", 0, 0);
				} catch (Exception e2) {
					// TODO: handle exception
				}
				new MySQLHelper().FitTableColumns(table_1);
			}
		});
	}

	void init() {
		infoList1 = new MySQLHelper().query("SELECT * from query1");
		for (Map<String, Object> info : infoList1) {
			for (String column : info.keySet()) {
				if (!column.equals("ID") && !column.equals("搜索项")) {
					columns1.add(column);
					comboBox_1.addItem(column);
				}
			}
			break;
		}

		model1 = new DefaultTableModel(table1Columns, columns1);

		columns2.add("逻辑运算符");
		columns2.add("字段名");
		columns2.add("运算符");
		columns2.add("值");
		model2 = new DefaultTableModel(table2Columns, columns2);

		comboBox.addItem("and");
		comboBox.addItem("or");

		comboBox_2.addItem("<");
		comboBox_2.addItem("<=");
		comboBox_2.addItem(">");
		comboBox_2.addItem(">=");
		comboBox_2.addItem("=");
		comboBox_2.addItem("<>");
	}

	void loadTable(String sql) {
		columns2 = new Vector<String>();
		table2Columns = new Vector<>();
		infoList2 = new MySQLHelper().query(sql);
		for (Map<String, Object> info : infoList2) {
			for (String column : info.keySet()) {
				if (!column.equals("ID") && !column.equals("搜索项"))
					columns2.add(column);
			}
			break;
		}

		for (int i = 0; i < columns2.size() / 2; i++) {
			String temp = columns2.get(i);
			columns2.set(i, columns2.get(columns2.size() - i - 1));
			columns2.set(columns2.size() - i - 1, temp);
		}
		Vector<String> temp = new Vector<>();
		for (Map<String, Object> info : infoList2) {
			temp = new Vector<>();
			for (String column : columns2) {
				temp.add(String.valueOf(info.get(column)));
			}
			table2Columns.add(temp);
		}
		DefaultTableModel model_temp = new DefaultTableModel(table2Columns, columns2);
		table.setModel(model_temp);
		new MySQLHelper().FitTableColumns(table);
	}

}
