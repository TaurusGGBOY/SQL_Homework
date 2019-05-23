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

public class BasicInfo3 extends JPanel {
	private JTextField textField;
	private String clickedInfo;
	private JTable table;
	private String clickName;
	private String clickCode;
	private String clickID;
	private JTextField textField_1;
	private HashSet<String> idSet = new HashSet<>();
	private HashSet<String> CodeSet = new HashSet<>();
	private HashSet<String> nameSet = new HashSet<>();
	DefaultTableModel model;
	private JTextField textField_2;
	List<Map<String, Object>> infoList = new ArrayList<Map<String, Object>>();

	/**
	 * Create the panel.
	 */
	public BasicInfo3() {
		setLayout(null);
		Vector<Vector<String>> bookTypes = new Vector<>();

		Vector<String> columns = new Vector<>();

		infoList = new MySQLHelper().query("SELECT * from basic3 order by Code");
		for (Map<String, Object> info : infoList) {
			String id = String.valueOf(info.get("ID"));
			Vector<String> temp = new Vector<>();
			temp.add(id);
			idSet.add(id);

			String Code = String.valueOf(info.get("Code"));
			temp.add(Code);
			CodeSet.add(Code);

			String bookType = String.valueOf(info.get("bookType"));
			temp.add(bookType);
			nameSet.add(bookType);
			bookTypes.add(temp);
		}
		columns.add("ID");
		columns.add("代码");
		columns.add("类型");
		model = new DefaultTableModel(bookTypes, columns);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 556, 263);
		add(scrollPane);

		table = new JTable(model);
		table.setFont(new Font("宋体", Font.PLAIN, 20));
		table.setRowHeight(25);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setPreferredWidth(400);
		table.setRowSorter(null);
		scrollPane.setViewportView(table);

		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 16));
		textField.setBounds(114, 456, 211, 27);
		add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("\u7F16\u8F91\u5BA4\u540D\u5B57");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 455, 94, 29);
		add(lblNewLabel);

		JButton btnNewButton = new JButton("\u4FDD\u5B58");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton.setBounds(460, 322, 106, 34);
		add(btnNewButton);

		JButton button = new JButton("\u5220\u9664");

		button.setFont(new Font("宋体", Font.PLAIN, 16));
		button.setBounds(460, 392, 106, 34);
		add(button);

		JButton button_1 = new JButton("\u590D\u4F4D");

		button_1.setFont(new Font("宋体", Font.PLAIN, 16));
		button_1.setBounds(460, 452, 106, 34);
		add(button_1);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("宋体", Font.PLAIN, 16));
		lblId.setBounds(10, 325, 94, 29);
		add(lblId);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(114, 326, 211, 27);
		add(textField_1);

		JLabel label_11 = new JLabel("");
		label_11.setForeground(Color.BLUE);
		label_11.setFont(new Font("宋体", Font.PLAIN, 20));
		label_11.setBounds(10, 493, 327, 44);
		add(label_11);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_2.setColumns(10);
		textField_2.setBounds(114, 396, 211, 27);
		add(textField_2);

		JLabel lblCode = new JLabel("Code");
		lblCode.setFont(new Font("宋体", Font.PLAIN, 16));
		lblCode.setBounds(10, 395, 94, 29);
		add(lblCode);

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

					clickCode = (String) table.getValueAt(table.getSelectedRow(), 1); // 获取所选中的行的第一个位置的内容，当然你也可以指定具体的该行第几格
					clickCode = clickCode.trim();
					textField_2.setText(clickCode);

					clickName = (String) table.getValueAt(table.getSelectedRow(), 2); // 获取所选中的行的第一个位置的内容，当然你也可以指定具体的该行第几格
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
								.executeNonquery("delete from basic3 where id = " + textField_1.getText().trim());

						int i = table.getSelectedRow();
						if (i < 0)
							return;

						// System.out.println(i);
						model.removeRow(i);
						table.updateUI();

						textField.setText("");
						textField_1.setText("");

						textField_2.setText("");
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

				table.clearSelection();
				clickID = "";
				clickCode = "";
				clickName = "";
				label_11.setText("");
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField_1.getText().trim();
				String name = textField.getText().trim();
				String Code = textField_2.getText().trim();
				int i = 0;
				if (id.isEmpty() || name.isEmpty() || Code.isEmpty()) {
					label_11.setText("名称或ID或排行不能为空");
					return;
				}

				if (nameSet.contains(name) && !idSet.contains(id)) {
					label_11.setText("添加名称已存在");
					return;
				}

				if (CodeSet.contains(Code) && !idSet.contains(id)) {
					label_11.setText("添加代码已存在");
					return;
				}

				if (!nameSet.contains(name) && !idSet.contains(id) && !CodeSet.contains(Code)) {
					new MySQLHelper().executeNonquery(
							"Insert into basic3 (ID,Code,bookType) values (" + id + ",'" + Code + "','" + name + "')");
					model.setRowCount(0);
					infoList = new MySQLHelper().query("SELECT * from basic3 order by Code");
					for (Map<String, Object> info : infoList) {
						String id1 = String.valueOf(info.get("ID"));
						Vector<String> temp = new Vector<>();
						temp.add(id1);
						idSet.add(id1);

						String Code1 = String.valueOf(info.get("Code"));
						temp.add(Code1);
						CodeSet.add(Code1);

						String bookType1 = String.valueOf(info.get("bookType"));
						temp.add(bookType1);
						nameSet.add(bookType1);
						bookTypes.add(temp);
					}
					model = new DefaultTableModel(bookTypes, columns);
					table.setModel(model);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.getColumnModel().getColumn(0).setPreferredWidth(60);
					table.getColumnModel().getColumn(1).setPreferredWidth(60);
					table.getColumnModel().getColumn(2).setPreferredWidth(400);
					table.setRowSorter(null);
					table.updateUI();
					label_11.setText("添加成功");
					return;
				}

				if (table.getSelectedRow() < 0) {
					label_11.setText("当前没有选中行");
					return;
				}

				new MySQLHelper().executeNonquery(
						"update basic3 set bookType = '" + name + "',Code ='" + Code + "' where ID =" + id);
				model.setRowCount(0);
				infoList = new MySQLHelper().query("SELECT * from basic3 order by Code");
				for (Map<String, Object> info : infoList) {
					String id1 = String.valueOf(info.get("ID"));
					Vector<String> temp = new Vector<>();
					temp.add(id1);
					idSet.add(id1);

					String Code1 = String.valueOf(info.get("Code"));
					temp.add(Code1);
					CodeSet.add(Code1);

					String bookType1 = String.valueOf(info.get("bookType"));
					temp.add(bookType1);
					nameSet.add(bookType1);
					bookTypes.add(temp);
				}
				model = new DefaultTableModel(bookTypes, columns);

				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				table.clearSelection();
				clickID = "";
				clickName = "";
				clickCode = "";
				table.updateUI();

				label_11.setText("保存成功");
				repaint();
			}
		});
	}
}
