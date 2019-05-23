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

public class BasicInfo1 extends JPanel {
	private JTextField textField;
	private String clickedInfo;
	private JTable table;
	private String clickName;
	private String clickID;
	private JTextField textField_1;
	private HashSet<String> idSet = new HashSet<>();
	private HashSet<String> nameSet = new HashSet<>();
	DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public BasicInfo1() {
		setLayout(null);
		Vector<Vector<String>> bjsNames = new Vector<>();
		List<Map<String, Object>> infoList = new ArrayList<Map<String, Object>>();
		Vector<String> columns = new Vector<>();

		infoList = new MySQLHelper().query("SELECT * from basic1 order by convert(bjsName using gbk) ASC");
		for (Map<String, Object> info : infoList) {
			String id = String.valueOf(info.get("ID"));
			Vector<String> temp = new Vector<>();
			temp.add(id);
			idSet.add(id);
			String bjsName = String.valueOf(info.get("bjsName"));
			temp.add(bjsName);
			nameSet.add(bjsName);
			bjsNames.add(temp);
		}
		columns.add("ID");
		columns.add("编辑室");
		model = new DefaultTableModel(bjsNames, columns);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 243, 493);
		add(scrollPane);

		table = new JTable(model);
		table.setFont(new Font("宋体", Font.PLAIN, 20));
		table.setRowHeight(25);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.setRowSorter(null);
		scrollPane.setViewportView(table);

		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 16));
		textField.setBounds(379, 103, 211, 27);
		add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("\u7F16\u8F91\u5BA4\u540D\u5B57");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel.setBounds(275, 102, 94, 29);
		add(lblNewLabel);

		JButton btnNewButton = new JButton("\u4FDD\u5B58");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton.setBounds(263, 495, 106, 34);
		add(btnNewButton);

		JButton button = new JButton("\u5220\u9664");

		button.setFont(new Font("宋体", Font.PLAIN, 16));
		button.setBounds(375, 495, 106, 34);
		add(button);

		JButton button_1 = new JButton("\u590D\u4F4D");

		button_1.setFont(new Font("宋体", Font.PLAIN, 16));
		button_1.setBounds(487, 495, 106, 34);
		add(button_1);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("宋体", Font.PLAIN, 16));
		lblId.setBounds(275, 36, 94, 29);
		add(lblId);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(379, 37, 211, 27);
		add(textField_1);

		JLabel label_11 = new JLabel("");
		label_11.setForeground(Color.BLUE);
		label_11.setFont(new Font("宋体", Font.PLAIN, 20));
		label_11.setBounds(263, 441, 327, 44);
		add(label_11);

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

					clickName = (String) table.getValueAt(table.getSelectedRow(), 1); // 获取所选中的行的第一个位置的内容，当然你也可以指定具体的该行第几格
					clickName = clickName.trim();
					textField.setText(clickName);
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
						new MySQLHelper()
								.executeNonquery("delete from basic1 where id = " + textField_1.getText().trim());

						int i = table.getSelectedRow();
						if (i < 0)
							return;

						// System.out.println(i);
						model.removeRow(i);
						table.updateUI();

						textField.setText("");
						textField_1.setText("");

						textField_1.setText("");
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
				table.clearSelection();
				clickID = "";
				clickName = "";
				label_11.setText("");
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().trim().isEmpty() || textField_1.getText().trim().isEmpty()) {
					label_11.setText("名称或ID不能为空");
				}
				String id = textField_1.getText().trim();
				String name = textField.getText().trim();
				if (nameSet.contains(name) && !idSet.contains(id)) {
					label_11.setText("添加名称已存在");
					return;
				}
				if (!nameSet.contains(name) && !idSet.contains(id)) {
					new MySQLHelper()
							.executeNonquery("Insert into basic1 (ID,bjsName) values (" + id + ",'" + name + "')");
					Vector<String> temp = new Vector<>();
					temp.add(id);
					temp.add(name);
					bjsNames.add(temp);
					model = new DefaultTableModel(bjsNames, columns);
					table.setModel(model);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.getColumnModel().getColumn(0).setPreferredWidth(40);
					table.getColumnModel().getColumn(1).setPreferredWidth(200);
					table.setRowSorter(null);
					table.updateUI();
					label_11.setText("添加成功");
					return;
				}
				if (table.getSelectedRow() < 0) {
					label_11.setText("当前没有选中行");
					return;
				}
				new MySQLHelper().executeNonquery("update basic1 set bjsName = '" + name + "' where ID =" + id);
				model.setValueAt(name, table.getSelectedRow(), 1);
				textField.setText("");
				textField_1.setText("");
				table.clearSelection();
				clickID = "";
				clickName = "";

				table.updateUI();

				label_11.setText("保存成功");

			}
		});
	}
}
