package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;

import dal.MySQLHelper;

public class CombineQuery extends JPanel {
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
	JSpinner spinner;
	JSpinner spinner_1;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	int chooseRow;

	/**
	 * Create the panel.
	 */
	public CombineQuery() {
		init();
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 580, 468);
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

		JLabel label = new JLabel("\u8BED\u8A00");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(10, 523, 54, 15);
		add(label);

		JLabel label_1 = new JLabel("\u7F16\u8F91\u5BA4");
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		label_1.setBounds(10, 575, 54, 15);
		add(label_1);

		JLabel label_2 = new JLabel("\u7C7B\u578B");
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));
		label_2.setBounds(232, 523, 54, 15);
		add(label_2);

		JLabel label_3 = new JLabel("\u51FA\u7248\u65E5\u671F");
		label_3.setFont(new Font("宋体", Font.PLAIN, 16));
		label_3.setBounds(232, 571, 64, 15);
		add(label_3);

		JLabel label_4 = new JLabel("\u81F3");
		label_4.setFont(new Font("宋体", Font.PLAIN, 16));
		label_4.setBounds(432, 575, 29, 15);
		add(label_4);

		JButton button = new JButton("\u68C0\u7D22");

		button.setFont(new Font("宋体", Font.PLAIN, 16));
		button.setBounds(480, 519, 110, 23);
		add(button);

		comboBox.setBounds(86, 521, 102, 21);
		comboBox.addItem("");
		add(comboBox);

		comboBox_1.setBounds(86, 569, 102, 21);
		comboBox_1.addItem(" ");
		add(comboBox_1);

		comboBox_2.setBounds(306, 521, 95, 21);
		comboBox_2.addItem(" ");
		add(comboBox_2);

		SpinnerDateModel model_query = new SpinnerDateModel();
		model_query.setCalendarField(Calendar.DATE);
		spinner = new JSpinner(model_query);
		spinner.setValue(new Date());
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd-MM-yyyy");
		spinner.setEditor(editor);
		spinner.setBounds(306, 569, 95, 22);
		add(spinner);

		SpinnerDateModel model_query1 = new SpinnerDateModel();
		model_query1.setCalendarField(Calendar.DATE);
		spinner_1 = new JSpinner(model_query1);
		spinner_1.setValue(new Date());
		JSpinner.DateEditor editor1 = new JSpinner.DateEditor(spinner_1, "dd-MM-yyyy");
		spinner_1.setEditor(editor1);
		spinner_1.setBounds(480, 569, 110, 22);
		add(spinner_1);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StringBuilder sBuilder = new StringBuilder();
				sBuilder.append("select * from query1 where 时间 >= '");
				sBuilder.append(new SimpleDateFormat("yyyy-MM-dd").format(spinner.getValue()));
				sBuilder.append("' and 时间<='");
				sBuilder.append(new SimpleDateFormat("yyyy-MM-dd").format(spinner_1.getValue()));
				sBuilder.append("' ");
				if (!comboBox.getSelectedItem().toString().trim().isEmpty()) {
					sBuilder.append("and ");
					sBuilder.append(label.getText().toString());
					sBuilder.append(" LIKE '%");
					sBuilder.append(comboBox.getSelectedItem().toString());
					sBuilder.append("%' ");
				}
				if (!comboBox_1.getSelectedItem().toString().trim().isEmpty()) {
					sBuilder.append("and ");
					sBuilder.append(label_1.getText().toString());
					sBuilder.append(" LIKE '%");
					sBuilder.append(comboBox_1.getSelectedItem().toString());
					sBuilder.append("%' ");
				}
				if (!comboBox_2.getSelectedItem().toString().trim().isEmpty()) {
					sBuilder.append("and ");
					sBuilder.append(label_2.getText().toString());
					sBuilder.append(" LIKE '%");
					sBuilder.append(comboBox_2.getSelectedItem().toString());
					sBuilder.append("%' ");
				}
				loadTable(sBuilder.toString());
			}
		});

	}

	void buildSql() {

	}

	void init() {
		infoList1 = new MySQLHelper().query("SELECT * from query1");
		HashSet<String> set = new HashSet<>();
		HashSet<String> columnSet = new HashSet<>();
		for (Map<String, Object> info : infoList1) {
			for (String column : info.keySet()) {
				if (!column.equals("ID") && !column.equals("搜索项")) {
					if (!columnSet.contains(column)) {
						columns1.add(column);
						columnSet.add(column);
					}
					if (!set.contains(String.valueOf(info.get(column)))) {
						switch (column) {
						case "语言":
							comboBox.addItem(String.valueOf(info.get(column)));
							break;
						case "编辑室":
							comboBox_1.addItem(String.valueOf(info.get(column)));
							break;
						case "类型":
							comboBox_2.addItem(String.valueOf(info.get(column)));
							break;
						default:
							break;
						}
						set.add(String.valueOf(info.get(column)));

					}
				}

			}
		}

		model1 = new DefaultTableModel(table1Columns, columns1);
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
