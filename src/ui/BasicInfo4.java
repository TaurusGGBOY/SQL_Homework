package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dal.MySQLHelper;

public class BasicInfo4 extends JPanel {
	private JTextField textField;
	private String clickedInfo;
	private JTable table;
	private String clickName;
	private String clickNum;
	private String clickID;
	private String clickUse;
	private JTextField textField_1;
	private HashSet<String> idSet = new HashSet<>();
	private HashSet<String> numSet = new HashSet<>();
	private HashSet<String> nameSet = new HashSet<>();
	DefaultTableModel model;
	private JTextField textField_2;
	List<Map<String, Object>> infoList = new ArrayList<Map<String, Object>>();
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public BasicInfo4() {
		setLayout(null);
		Vector<Vector<String>> bjsNames = new Vector<>();

		Vector<String> columns = new Vector<>();

		infoList = new MySQLHelper().query("SELECT * from basic4 order by Num");
		for (Map<String, Object> info : infoList) {
			String id = String.valueOf(info.get("ID"));
			Vector<String> temp = new Vector<>();
			temp.add(id);
			idSet.add(id);

			String num = String.valueOf(info.get("Num"));
			temp.add(num);
			numSet.add(num);

			String bjsName = String.valueOf(info.get("bjsName"));
			temp.add(bjsName);
			nameSet.add(bjsName);

			String inUse = String.valueOf(info.get("inUse"));
			temp.add(inUse);
			nameSet.add(inUse);

			bjsNames.add(temp);
		}
		columns.add("ID");
		columns.add("Num");
		columns.add("编辑室");
		columns.add("是否在使用");
		model = new DefaultTableModel(bjsNames, columns);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 553, 241);
		add(scrollPane);

		table = new JTable(model);
		table.setFont(new Font("宋体", Font.PLAIN, 20));
		table.setRowHeight(25);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.setRowSorter(null);
		scrollPane.setViewportView(table);

		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 16));
		textField.setBounds(114, 430, 211, 27);
		add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("\u7F16\u8F91\u5BA4\u540D\u5B57");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 429, 94, 29);
		add(lblNewLabel);

		JButton btnNewButton = new JButton("\u4FDD\u5B58");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton.setBounds(457, 296, 106, 34);
		add(btnNewButton);

		JButton button = new JButton("\u5220\u9664");

		button.setFont(new Font("宋体", Font.PLAIN, 16));
		button.setBounds(457, 366, 106, 34);
		add(button);

		JButton button_1 = new JButton("\u590D\u4F4D");

		button_1.setFont(new Font("宋体", Font.PLAIN, 16));
		button_1.setBounds(457, 426, 106, 34);
		add(button_1);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("宋体", Font.PLAIN, 16));
		lblId.setBounds(10, 299, 94, 29);
		add(lblId);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(114, 300, 211, 27);
		add(textField_1);

		JLabel label_11 = new JLabel("");
		label_11.setForeground(Color.BLUE);
		label_11.setFont(new Font("宋体", Font.PLAIN, 20));
		label_11.setBounds(10, 516, 538, 44);
		add(label_11);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_2.setColumns(10);
		textField_2.setBounds(114, 370, 211, 27);
		add(textField_2);

		JLabel lblNum = new JLabel("Num");
		lblNum.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNum.setBounds(10, 369, 94, 29);
		add(lblNum);

		JLabel label = new JLabel("\u662F\u5426\u5728\u4F7F\u7528");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(10, 481, 94, 29);
		add(label);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_3.setColumns(10);
		textField_3.setBounds(114, 482, 211, 27);
		add(textField_3);

		table.addMouseListener(new java.awt.event.MouseAdapter() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				String n = "";
				try {
					n = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
				} catch (Exception er) {
					// TODO: handle exception
				}
				if (!n.isEmpty()) {
					clickID = (String) table.getValueAt(table.getSelectedRow(), 0); // 获取所选中的行的第一个位置的内容，当然你也可以指定具体的该行第几格
					clickID = clickID.trim();
					textField_1.setText(clickID);

					clickNum = (String) table.getValueAt(table.getSelectedRow(), 1); // 获取所选中的行的第一个位置的内容，当然你也可以指定具体的该行第几格
					clickNum = clickNum.trim();
					textField_2.setText(clickNum);

					clickName = (String) table.getValueAt(table.getSelectedRow(), 2); // 获取所选中的行的第一个位置的内容，当然你也可以指定具体的该行第几格
					clickName = clickName.trim();
					textField.setText(clickName);

					clickUse = (String) table.getValueAt(table.getSelectedRow(), 3); // 获取所选中的行的第一个位置的内容，当然你也可以指定具体的该行第几格
					clickUse = clickUse.trim();
					textField_3.setText(clickUse);

					if (textField_3.getText().trim().equals("否")) {
						textField.setEditable(false);
						textField_1.setEditable(false);
						textField_2.setEditable(false);
					} else {
						textField.setEditable(true);
						textField_1.setEditable(true);
						textField_2.setEditable(true);
					}
				}
			}

		});

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (!textField_1.getText().equals("")) {
					if (!idSet.contains(textField_1.getText())) {
						label_11.setText("没有这个用户，请重新输入");
						return;
					}
					if (JOptionPane.showConfirmDialog(null, "删除该用户？" + textField_1.getText().trim(), "提示",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						new MySQLHelper().executeNonquery(
								"update basic4 set inUse = '否' where ID =" + textField_1.getText().trim());

						int i = table.getSelectedRow();
						if (i < 0)
							return;

						// System.out.println(i);
						model.removeRow(i);
						table.updateUI();

						textField.setText("");
						textField_1.setText("");

						textField_2.setText("");
						textField_3.setText("");
						/*
						 * ((DefaultTableModel) table.getModel()).getDataVector().clear();
						 * ((DefaultTableModel) table.getModel()).fireTableDataChanged();
						 * table.updateUI();
						 */
						label_11.setText("已删除");
					}
				} else
					textField_1.setText("ID不能为空");
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");

				table.clearSelection();
				clickID = "";
				clickNum = "";
				clickName = "";
				clickUse = "";
				label_11.setText("");
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField_1.getText().trim();
				String name = textField.getText().trim();
				String num = textField_2.getText().trim();
				String use = textField_3.getText().trim();
				int i = 0;
				if (id.isEmpty() || name.isEmpty() || num.isEmpty()) {
					label_11.setText("名称或ID或排行不能为空");
					return;
				}

				if (nameSet.contains(name) && !idSet.contains(id)) {
					label_11.setText("添加名称已存在");
					return;
				}

				if (numSet.contains(num) && !idSet.contains(id)) {
					label_11.setText("添加排名已存在");
					return;
				}

				if (!(use.equals("是") || use.equals("否"))) {
					label_11.setText("是否使用只能填写是或者否");
					return;
				}

				if (!nameSet.contains(name) && !idSet.contains(id) && !numSet.contains(num)) {
					new MySQLHelper().executeNonquery("Insert into basic4 (ID,Num,bjsName,inUse) values (" + id + ","
							+ num + ",'" + name + "','" + use + "')");
					model.setRowCount(0);
					infoList = new MySQLHelper().query("SELECT * from basic4 where inUse = '是' order by Num");
					for (Map<String, Object> info : infoList) {
						String id1 = String.valueOf(info.get("ID"));
						Vector<String> temp = new Vector<>();
						temp.add(id1);
						idSet.add(id1);

						String num1 = String.valueOf(info.get("Num"));
						temp.add(num1);
						numSet.add(num1);

						String bjsName1 = String.valueOf(info.get("bjsName"));
						temp.add(bjsName1);
						nameSet.add(bjsName1);

						String inUse = String.valueOf(info.get("inUse"));
						temp.add(inUse);
						nameSet.add(inUse);
						bjsNames.add(temp);
					}
					model = new DefaultTableModel(bjsNames, columns);
					table.setModel(model);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.getColumnModel().getColumn(0).setPreferredWidth(40);
					table.getColumnModel().getColumn(1).setPreferredWidth(150);
					table.getColumnModel().getColumn(2).setPreferredWidth(300);
					table.getColumnModel().getColumn(3).setPreferredWidth(40);
					table.setRowSorter(null);
					table.updateUI();
					label_11.setText("添加成功");
					return;
				}

				if (table.getSelectedRow() < 0) {
					label_11.setText("当前没有选中行");
					return;
				}

				new MySQLHelper().executeNonquery("update basic4 set bjsName = '" + name + "',Num =" + num + ",inUse ='"
						+ use + "' where ID =" + id);
				model.setRowCount(0);
				infoList = new MySQLHelper().query("SELECT * from basic4 where inUse = '是' order by Num");
				for (Map<String, Object> info : infoList) {
					String id1 = String.valueOf(info.get("ID"));
					Vector<String> temp = new Vector<>();
					temp.add(id1);
					idSet.add(id1);

					String num1 = String.valueOf(info.get("Num"));
					temp.add(num1);
					numSet.add(num1);

					String bjsName1 = String.valueOf(info.get("bjsName"));
					temp.add(bjsName1);
					nameSet.add(bjsName1);

					String inUse = String.valueOf(info.get("inUse"));
					temp.add(inUse);
					nameSet.add(inUse);
					bjsNames.add(temp);
				}
				model = new DefaultTableModel(bjsNames, columns);
				table.setModel(model);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.getColumnModel().getColumn(0).setPreferredWidth(40);
				table.getColumnModel().getColumn(1).setPreferredWidth(150);
				table.getColumnModel().getColumn(2).setPreferredWidth(300);
				table.getColumnModel().getColumn(3).setPreferredWidth(40);
				table.setRowSorter(null);
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				table.clearSelection();
				clickID = "";
				clickName = "";
				clickNum = "";
				table.updateUI();

				label_11.setText("保存成功");
				repaint();
			}
		});
	}
}
