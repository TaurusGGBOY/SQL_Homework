package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dal.MySQLHelper;

public class KeyInput extends JPanel {
	private JTextField textField_printNum;
	private String clickedInfo;
	private JTable table;
	private String clickName;
	private String clickNum;
	private String clickID;
	private JTextField textField_name;
	private HashSet<String> idSet = new HashSet<>();
	private HashSet<String> numSet = new HashSet<>();
	private HashSet<String> nameSet = new HashSet<>();
	DefaultTableModel model1;
	DefaultTableModel model2;
	private JTextField textField_number;
	List<Map<String, Object>> infoList1 = new ArrayList<Map<String, Object>>();
	List<Map<String, Object>> infoList2 = new ArrayList<Map<String, Object>>();
	private JTextField textField_tip;
	private JTextField textField_price;

	Vector<Vector<String>> table1Columns = new Vector<>();
	Vector<Vector<String>> table2Columns = new Vector<>();
	Vector<String> columns1 = new Vector<>();
	Vector<String> columns2 = new Vector<>();
	private JTable table_1;
	JSpinner spinner_num;
	JSpinner spinner_in;
	JSpinner spinner_query;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	JScrollPane scrollPane_1;
	int chooseRow;
	private JLabel label_tip;

	/**
	 * Create the panel.
	 */
	public KeyInput() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				table.requestFocus();
			}
		});
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 580, 210);
		add(scrollPane);

		loadTable1();
		table = new JTable(model1);

		table.setFont(new Font("宋体", Font.PLAIN, 20));
		table.setRowHeight(25);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.requestFocus();
		scrollPane.setViewportView(table);

		textField_printNum = new JTextField();
		textField_printNum.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_printNum.setBounds(160, 372, 70, 27);
		textField_printNum.setEditable(false);
		add(textField_printNum);
		textField_printNum.setColumns(10);

		JButton btnNewButton = new JButton("\u4FDD\u5B58");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton.setBounds(484, 256, 106, 34);
		add(btnNewButton);

		JButton button = new JButton("\u67E5\u8BE2");

		button.setFont(new Font("宋体", Font.PLAIN, 16));
		button.setBounds(466, 512, 106, 34);
		add(button);

		JLabel lblId = new JLabel("\u4E66\u540D");
		lblId.setFont(new Font("宋体", Font.PLAIN, 16));
		lblId.setBounds(10, 292, 39, 29);
		add(lblId);

		textField_name = new JTextField();
		textField_name.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_name.setColumns(10);
		textField_name.setBounds(46, 294, 185, 27);
		textField_name.setEditable(false);
		add(textField_name);

		label_tip = new JLabel("");
		label_tip.setForeground(Color.BLUE);
		label_tip.setFont(new Font("宋体", Font.PLAIN, 20));
		label_tip.setBounds(147, 256, 327, 27);
		add(label_tip);

		textField_number = new JTextField();
		textField_number.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_number.setColumns(10);
		textField_number.setBounds(46, 331, 185, 27);
		textField_number.setEditable(false);
		add(textField_number);

		JLabel lblNum = new JLabel("\u4E66\u53F7");
		lblNum.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNum.setBounds(10, 331, 39, 29);
		add(lblNum);

		JLabel lblNewLabel_bookNum = new JLabel("\u5171 \u672C");
		lblNewLabel_bookNum.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_bookNum.setBounds(10, 11, 54, 15);
		add(lblNewLabel_bookNum);

		JButton button_2 = new JButton("\u5173\u95ED");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_2.setFont(new Font("宋体", Font.PLAIN, 16));
		button_2.setBounds(466, 556, 106, 34);
		add(button_2);

		JLabel label_2 = new JLabel("\u5165\u5E93\u518C\u6570");
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));
		label_2.setBounds(258, 292, 66, 29);
		add(label_2);

		JLabel label_3 = new JLabel("\u5165\u5E93\u65E5\u671F");
		label_3.setFont(new Font("宋体", Font.PLAIN, 16));
		label_3.setBounds(383, 292, 64, 29);
		add(label_3);

		JLabel label_4 = new JLabel("\u5B9A\u4EF7");
		label_4.setFont(new Font("宋体", Font.PLAIN, 16));
		label_4.setBounds(10, 366, 32, 29);
		add(label_4);

		JLabel label_6 = new JLabel("\u5370\u6570");
		label_6.setFont(new Font("宋体", Font.PLAIN, 16));
		label_6.setBounds(125, 366, 39, 29);
		add(label_6);

		JLabel label_8 = new JLabel("\u5907\u6CE8");
		label_8.setFont(new Font("宋体", Font.PLAIN, 16));
		label_8.setBounds(268, 330, 64, 29);
		add(label_8);

		textField_tip = new JTextField();
		textField_tip.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_tip.setColumns(10);
		textField_tip.setBounds(334, 331, 256, 64);
		add(textField_tip);

		SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(new Integer(1), new Integer(1), null,
				new Integer(1));
		spinner_num = new JSpinner();
		spinner_num.setModel(spinnerNumberModel);
		spinner_num.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ENTER:
					table.requestFocus(true);
					save();
					setSetting();
					query();
					table_1.setRowSelectionInterval(table_1.getRowCount() - 1, table_1.getRowCount() - 1);
					scrollPane_1.getVerticalScrollBar().setValue(scrollPane_1.getVerticalScrollBar().getMaximum());
					label_tip.setText("保存成功");
					break;
				default:
					break;
				}
			}
		});
		spinner_num.setBounds(334, 297, 39, 22);
		add(spinner_num);

		SpinnerDateModel model_in = new SpinnerDateModel();
		spinner_in = new JSpinner(model_in);
		spinner_in.setValue(new Date());
		JSpinner.DateEditor editor1 = new JSpinner.DateEditor(spinner_in, "dd-MM-yyyy");
		spinner_in.setEditor(editor1);
		spinner_in.setBounds(457, 297, 133, 22);
		add(spinner_in);

		textField_price = new JTextField();
		textField_price.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_price.setColumns(10);
		textField_price.setBounds(45, 372, 70, 27);
		textField_price.setEditable(false);
		add(textField_price);

		JLabel label = new JLabel("\u56FE\u4E66\u5165\u5E93\u660E\u7EC6\u67E5\u8BE2");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(10, 409, 133, 29);
		add(label);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 448, 446, 142);
		add(scrollPane_1);

		JLabel label_1 = new JLabel("\u5165\u5E93");
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		label_1.setBounds(10, 256, 54, 29);
		add(label_1);

		SpinnerDateModel model_query = new SpinnerDateModel();
		model_query.setCalendarField(Calendar.DATE);
		spinner_query = new JSpinner(model_query);
		spinner_query.setValue(new Date());
		JSpinner.DateEditor editor2 = new JSpinner.DateEditor(spinner_query, "dd-MM-yyyy");
		spinner_query.setEditor(editor2);
		spinner_query.setBounds(466, 453, 124, 22);
		add(spinner_query);

		table_1 = new JTable();
		loadTable2();

		table_1.setFont(new Font("宋体", Font.PLAIN, 20));
		table_1.setRowHeight(25);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_1.setViewportView(table_1);

		ActionMap am = table.getActionMap();
		am.getParent().remove("selectNextRowCell");
		table.setActionMap(am);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(5, 10, 590, 242);
		add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(5, 253, 590, 157);
		add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(5, 413, 590, 191);
		add(panel_2);

		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				chooseRow = table.getSelectedRow();
				switch (arg0.getKeyCode()) {
				case KeyEvent.VK_ENTER:
					textField_printNum.setText((String) table.getValueAt(chooseRow, 4));
					textField_name.setText((String) table.getValueAt(chooseRow, 0));
					textField_number.setText((String) table.getValueAt(chooseRow, 7));
					textField_price.setText((String) table.getValueAt(chooseRow, 9));
					spinner_num.requestFocus();
					label_tip.setText("");
					break;
				default:
					break;
				}
			}

		});

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				query();

				label_tip.setText("查询成功");

			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
				setSetting();
				query();
				label_tip.setText("保存成功");
			}
		});
	}

	void loadTable1() {
		columns1 = new Vector<String>();
		table1Columns = new Vector<>();
		infoList1 = new MySQLHelper().query("SELECT * from keyinput1");
		for (Map<String, Object> info : infoList1) {
			for (String column : info.keySet()) {
				columns1.add(column);
			}
			break;
		}

		for (int i = 0; i < columns1.size() / 2; i++) {
			String temp = columns1.get(i);
			columns1.set(i, columns1.get(columns1.size() - i - 1));
			columns1.set(columns1.size() - i - 1, temp);
		}

		Vector<String> temp = new Vector<>();
		for (Map<String, Object> info : infoList1) {
			temp = new Vector<>();
			for (String column : columns1) {
				temp.add(String.valueOf(info.get(column)));
			}
			table1Columns.add(temp);
		}
		model1 = new DefaultTableModel(table1Columns, columns1);
	}

	void loadTable2() {
		columns2 = new Vector<String>();
		table2Columns = new Vector<>();
		infoList2 = new MySQLHelper().query("SELECT * from in1 where InDate = '"
				+ new SimpleDateFormat("yyyy-MM-dd").format(spinner_query.getValue()) + "'");
		for (Map<String, Object> info : infoList2) {
			for (String column : info.keySet()) {
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
			// System.out.print(info.get("Name"));
			// System.out.print(info.get(" "));
			// System.out.println(info.get("InNum"));
			for (String column : columns2) {
				temp.add(String.valueOf(info.get(column)));
			}
			table2Columns.add(temp);
		}
		DefaultTableModel model_temp = new DefaultTableModel(table2Columns, columns2);
		table_1.setModel(model_temp);
	}

	void setSetting() {
		spinner_num.setValue(1);
		label_tip.setText("");
	}

	void save() {
		Map<String, String> map = new HashMap<>();
		map.put("Name", textField_name.getText());
		map.put("InDate", new SimpleDateFormat("yyyy-MM-dd").format(spinner_in.getValue()));
		map.put("InNum", String.valueOf(spinner_num.getValue()));
		map.put("Tip", textField_tip.getText());
		map.put("Num", textField_number.getText());

		StringBuilder sql = new StringBuilder();

		sql.append("Insert into in1 (");
		for (String column : columns2) {
			if (column.equals("ID"))
				continue;
			sql.append(column);
			sql.append(",");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(") values (");
		for (String column : columns2) {
			if (column.equals("ID"))
				continue;
			sql.append("'");
			sql.append(map.get(column));
			sql.append("',");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(")");
		new MySQLHelper().executeNonquery(sql.toString());

	}

	void query() {
		loadTable2();
	}
}
