package ui;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.ActionMap;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dal.MySQLHelper;

public class FuzzyQuery extends JPanel {
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

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	int chooseRow;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public FuzzyQuery() {
		init();
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 580, 468);
		add(scrollPane);

		table = new JTable(model1);

		table.setFont(new Font("ËÎÌå", Font.PLAIN, 20));
		table.setRowHeight(25);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.requestFocus();
		scrollPane.setViewportView(table);

		ActionMap am = table.getActionMap();
		am.getParent().remove("selectNextRowCell");
		table.setActionMap(am);

		JLabel lblNewLabel = new JLabel("\u68C0\u7D22");
		lblNewLabel.setFont(new Font("ËÎÌå", Font.PLAIN, 16));
		lblNewLabel.setBounds(121, 549, 54, 15);
		add(lblNewLabel);

		textField = new JTextField();

		textField.setBounds(185, 546, 405, 21);
		add(textField);
		textField.setColumns(10);

		JRadioButton rdbtnAnd = new JRadioButton("and");
		rdbtnAnd.setBounds(6, 546, 48, 23);
		add(rdbtnAnd);

		JRadioButton rdbtnOr = new JRadioButton("or");
		rdbtnOr.setSelected(true);
		rdbtnOr.setBounds(56, 546, 37, 23);
		add(rdbtnOr);
		ButtonGroup g1 = new ButtonGroup();
		g1.add(rdbtnAnd);
		g1.add(rdbtnOr);

		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				switch (arg0.getKeyCode()) {
				case KeyEvent.VK_ENTER:
					String[] strings = textField.getText().toString().split(" ");
					int strlen = strings.length;
					String opera = "";
					if (rdbtnAnd.isSelected())
						opera = "and";
					else {
						opera = "or";
					}
					StringBuilder sBuilder = new StringBuilder();
					sBuilder.append("Select * from query1 where ");
					int i = 0;
					if (strlen > 0) {
						sBuilder.append("ËÑË÷Ïî LIKE '%");
						sBuilder.append(strings[i++]);
						sBuilder.append("%'");
					}
					while (i < strlen) {
						sBuilder.append(" ");
						sBuilder.append(opera);
						sBuilder.append(" ËÑË÷Ïî LIKE '%");
						sBuilder.append(strings[i++]);
						sBuilder.append("%'");
					}
					loadTable(sBuilder.toString());
					break;
				default:
					break;
				}
			}
		});

	}

	void init() {
		infoList1 = new MySQLHelper().query("SELECT * from query1");
		for (Map<String, Object> info : infoList1) {
			for (String column : info.keySet()) {
				if (!column.equals("ID") && !column.equals("ËÑË÷Ïî"))
					columns1.add(column);
			}
			break;
		}

		model1 = new DefaultTableModel(table1Columns, columns1);
	}

	void loadTable(String sql) {
		columns2 = new Vector<String>();
		table2Columns = new Vector<>();
		infoList2 = new MySQLHelper().query(sql);
		for (Map<String, Object> info : infoList2) {
			for (String column : info.keySet()) {
				if (!column.equals("ID") && !column.equals("ËÑË÷Ïî"))
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
