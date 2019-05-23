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

public class ReferInfo1 extends JPanel {
	private JTextField textField_name;
	private String clickedInfo;
	private JTable table;
	private String clickName;
	private String clickbookType;
	private String clickshum;
	private String clickID;
	private JTextField textField_ID;
	private HashSet<String> idSet = new HashSet<>();
	private HashSet<String> bookTypeSet = new HashSet<>();
	private HashSet<String> nameSet = new HashSet<>();
	private HashSet<String> shumSet = new HashSet<>();
	DefaultTableModel model;
	private JTextField textField_bt;
	List<Map<String, Object>> infoList = new ArrayList<Map<String, Object>>();
	private JTextField textField_shum;
	Vector<Vector<String>> bjsNames;

	/**
	 * Create the panel.
	 */
	public ReferInfo1() {
		setLayout(null);
		bjsNames = new Vector<>();

		Vector<String> columns = new Vector<>();

		infoList = new MySQLHelper().query("SELECT * from refer1 order by ID");
		for (Map<String, Object> info : infoList) {
			String id = String.valueOf(info.get("ID"));
			Vector<String> temp = new Vector<>();
			temp.add(id);
			idSet.add(id);

			String bjsName = String.valueOf(info.get("bjsName"));
			temp.add(bjsName);
			nameSet.add(bjsName);

			String bookType = String.valueOf(info.get("bookType"));
			temp.add(bookType);
			bookTypeSet.add(bookType);

			String shum = String.valueOf(info.get("shum"));
			temp.add(shum);
			shumSet.add(shum);

			bjsNames.add(temp);
		}
		columns.add("ID");
		columns.add("编辑室");
		columns.add("bookType");
		columns.add("shum");
		model = new DefaultTableModel(bjsNames, columns);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 557, 282);
		add(scrollPane);

		table = new JTable(model);
		table.setFont(new Font("宋体", Font.PLAIN, 20));
		table.setRowHeight(25);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(300);
		table.setRowSorter(null);
		scrollPane.setViewportView(table);

		textField_name = new JTextField();
		textField_name.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_name.setBounds(125, 373, 211, 27);
		add(textField_name);
		textField_name.setColumns(10);

		JLabel lblNewLabel = new JLabel("\u7F16\u8F91\u5BA4\u540D\u5B57");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 367, 94, 29);
		add(lblNewLabel);

		JButton btnNewButton = new JButton("\u4FDD\u5B58");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton.setBounds(458, 330, 106, 34);
		add(btnNewButton);

		JButton button = new JButton("\u5220\u9664");

		button.setFont(new Font("宋体", Font.PLAIN, 16));
		button.setBounds(458, 369, 106, 34);
		add(button);

		JButton button_1 = new JButton("\u590D\u4F4D");

		button_1.setFont(new Font("宋体", Font.PLAIN, 16));
		button_1.setBounds(461, 413, 106, 34);
		add(button_1);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("宋体", Font.PLAIN, 16));
		lblId.setBounds(10, 328, 94, 29);
		add(lblId);

		textField_ID = new JTextField();
		textField_ID.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_ID.setColumns(10);
		textField_ID.setBounds(125, 336, 211, 27);
		add(textField_ID);

		JLabel label_11 = new JLabel("");
		label_11.setForeground(Color.BLUE);
		label_11.setFont(new Font("宋体", Font.PLAIN, 20));
		label_11.setBounds(10, 488, 396, 44);
		add(label_11);

		textField_bt = new JTextField();
		textField_bt.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_bt.setColumns(10);
		textField_bt.setBounds(125, 411, 211, 27);
		add(textField_bt);

		JLabel lblbookType = new JLabel("bookType");
		lblbookType.setFont(new Font("宋体", Font.PLAIN, 16));
		lblbookType.setBounds(10, 405, 94, 29);
		add(lblbookType);

		JLabel lblShum = new JLabel("shum");
		lblShum.setFont(new Font("宋体", Font.PLAIN, 16));
		lblShum.setBounds(10, 442, 94, 29);
		add(lblShum);

		textField_shum = new JTextField();
		textField_shum.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_shum.setColumns(10);
		textField_shum.setBounds(125, 448, 211, 27);
		add(textField_shum);

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
					textField_ID.setText(clickID);

					clickbookType = (String) table.getValueAt(table.getSelectedRow(), 2); // 获取所选中的行的第一个位置的内容，当然你也可以指定具体的该行第几格
					clickbookType = clickbookType.trim();
					textField_bt.setText(clickbookType);

					clickName = (String) table.getValueAt(table.getSelectedRow(), 1); // 获取所选中的行的第一个位置的内容，当然你也可以指定具体的该行第几格
					clickName = clickName.trim();
					textField_name.setText(clickName);

					clickshum = (String) table.getValueAt(table.getSelectedRow(), 3); // 获取所选中的行的第一个位置的内容，当然你也可以指定具体的该行第几格
					clickshum = clickshum.trim();
					textField_shum.setText(clickshum);
				}
			}

		});

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (!textField_ID.getText().equals("")) {
					if (!idSet.contains(textField_ID.getText())) {
						label_11.setText("没有这个用户，请重新输入");
						return;
					}
					if (JOptionPane.showConfirmDialog(null, "删除该用户？" + textField_ID.getText().trim(), "提示",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						new MySQLHelper()
								.executeNonquery("delete from refer1 where id = " + textField_ID.getText().trim());

						int i = table.getSelectedRow();
						if (i < 0)
							return;

						// System.out.println(i);
						model.removeRow(i);
						table.updateUI();

						textField_name.setText("");
						textField_ID.setText("");
						textField_shum.setText("");
						textField_bt.setText("");
						/*
						 * ((DefaultTableModel) table.getModel()).getDataVector().clear();
						 * ((DefaultTableModel) table.getModel()).fireTableDataChanged();
						 * table.updateUI();
						 */
						label_11.setText("已删除");
					}
				} else
					textField_ID.setText("ID不能为空");
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_name.setText("");
				textField_ID.setText("");
				textField_bt.setText("");
				textField_shum.setText("");
				table.clearSelection();
				clickID = "";
				clickName = "";
				clickbookType = "";
				clickshum = "";
				label_11.setText("");
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField_ID.getText().trim();
				String name = textField_name.getText().trim();
				String bookType = textField_bt.getText().trim();
				String shum = textField_shum.getText().trim();
				int i = 0;
				if (id.isEmpty() || name.isEmpty() || bookType.isEmpty() || shum.isEmpty()) {
					label_11.setText("不能有项目为空");
					return;
				}

				if (shumSet.contains(shum) && !idSet.contains(id)) {
					label_11.setText("添加名称已存在");
					return;
				}

				if (!shumSet.contains(shum) && !idSet.contains(id)) {
					new MySQLHelper().executeNonquery("Insert into refer1 (ID,bookType,bjsName,shum) values (" + id
							+ ",'" + bookType + "','" + name + "','" + shum + "')");
					bjsNames = new Vector<>();
					model.setRowCount(0);
					infoList = new MySQLHelper().query("SELECT * from refer1 order by ID");
					for (Map<String, Object> info : infoList) {
						String id1 = String.valueOf(info.get("ID"));
						Vector<String> temp = new Vector<>();
						temp.add(id1);
						idSet.add(id1);

						String bjsName1 = String.valueOf(info.get("bjsName"));
						temp.add(bjsName1);
						nameSet.add(bjsName1);

						String bookType1 = String.valueOf(info.get("bookType"));
						temp.add(bookType1);
						bookTypeSet.add(bookType1);

						String shum1 = String.valueOf(info.get("shum"));
						temp.add(shum1);
						shumSet.add(shum1);

						bjsNames.add(temp);
					}
					model = new DefaultTableModel(bjsNames, columns);
					table.setModel(model);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.getColumnModel().getColumn(0).setPreferredWidth(40);
					table.getColumnModel().getColumn(1).setPreferredWidth(150);
					table.getColumnModel().getColumn(2).setPreferredWidth(150);
					table.getColumnModel().getColumn(3).setPreferredWidth(300);
					table.setRowSorter(null);
					table.updateUI();
					textField_name.setText("");
					textField_ID.setText("");
					textField_bt.setText("");
					textField_shum.setText("");
					table.clearSelection();
					clickID = "";
					clickName = "";
					clickbookType = "";
					clickshum = "";
					label_11.setText("添加成功");
					return;
				}

				if (table.getSelectedRow() < 0) {
					label_11.setText("当前没有选中行");
					return;
				}

				new MySQLHelper().executeNonquery("update refer1 set bjsName = '" + name + "',bookType ='" + bookType
						+ "',shum='" + shum + "' where ID =" + id);
				model.setRowCount(0);
				bjsNames = new Vector<>();
				infoList = new MySQLHelper().query("SELECT * from refer1 order by ID");
				for (Map<String, Object> info : infoList) {
					String id1 = String.valueOf(info.get("ID"));
					Vector<String> temp = new Vector<>();
					temp.add(id1);
					idSet.add(id1);

					String bjsName1 = String.valueOf(info.get("bjsName"));
					temp.add(bjsName1);
					nameSet.add(bjsName1);

					String bookType1 = String.valueOf(info.get("bookType"));
					temp.add(bookType1);
					bookTypeSet.add(bookType1);

					String shum1 = String.valueOf(info.get("shum"));
					temp.add(shum1);
					shumSet.add(shum1);

					bjsNames.add(temp);
				}
				model = new DefaultTableModel(bjsNames, columns);
				table.setModel(model);
				table.getColumnModel().getColumn(0).setPreferredWidth(40);
				table.getColumnModel().getColumn(1).setPreferredWidth(150);
				table.getColumnModel().getColumn(2).setPreferredWidth(150);
				table.getColumnModel().getColumn(3).setPreferredWidth(300);
				table.setRowSorter(null);

				textField_name.setText("");
				textField_ID.setText("");
				textField_bt.setText("");
				textField_shum.setText("");
				table.clearSelection();
				clickID = "";
				clickName = "";
				clickbookType = "";
				clickshum = "";
				table.updateUI();

				label_11.setText("保存成功");
				repaint();
			}
		});
	}
}
