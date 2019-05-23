package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dal.MySQLHelper;

public class MainMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField_1;
	private JTable table_1;
	private JTable table_2;
	List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();
	List<Map<String, Object>> rightsno = new ArrayList<Map<String, Object>>();
	List<Map<String, Object>> rightsyes = new ArrayList<Map<String, Object>>();
	Vector<String> type1 = new Vector<>();
	int norights;
	int yesrights;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {

		Vector<Vector<String>> names = new Vector<>();
		Vector<String> columns1 = new Vector<>();
		Vector<String> type2 = new Vector<>();
		Vector<Vector<String>> typeright1 = new Vector<>();
		Vector<Vector<String>> typeright2 = new Vector<>();
		Map<String, Integer> nameMap = new HashMap<>();
		Map<String, Integer> rightMap = new HashMap<>();
		columns1.add("type");
		columns1.add("right");

		users = new MySQLHelper().query("SELECT * from users2");
		for (Map<String, Object> map : users) {
			Vector<String> nameVector = new Vector<>();
			nameVector.add(String.valueOf(map.get("username")));
			names.add(nameVector);
			nameMap.put(String.valueOf(map.get("username")), Integer.parseInt(String.valueOf(map.get("id"))));
		}

		List<Map<String, Object>> rightList = new MySQLHelper().query("SELECT * from rights2");
		for (Map<String, Object> map : rightList) {
			rightMap.put(String.valueOf(map.get("rightName")), Integer.parseInt(String.valueOf(map.get("id"))));
		}

		Vector<String> columns = new Vector<String>();
		columns.add("names");

		DefaultTableModel model = new DefaultTableModel(names, columns);
		DefaultTableModel model1 = new DefaultTableModel(typeright1, columns1);
		DefaultTableModel model2 = new DefaultTableModel(typeright2, columns1);
		setBackground(Color.WHITE);
		setTitle("\u897F\u5357\u4EA4\u901A\u5927\u5B66\u5BBF\u820D\u6C34\u7535\u7BA1\u7406\u7CFB\u7EDF");
		setUndecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginMenu.class.getResource("/ui/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(560, 240, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 200, 600);
		panel.setBackground(new Color(19, 108, 182));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel label_2 = new JLabel("\u7528\u6237\u4E00\u89C8\u8868");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("宋体", Font.BOLD, 20));
		label_2.setBackground(Color.WHITE);
		label_2.setBounds(36, 132, 124, 24);
		panel.add(label_2);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MainMenu.class.getResource("/ui/admin.png")));
		label.setBounds(10, 48, 40, 40);
		panel.add(label);

		JLabel lblNewLabel = new JLabel(GlobalVar.name);
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(36, 98, 124, 24);
		panel.add(lblNewLabel);

		JLabel label_1 = new JLabel("\u6B22\u8FCE\u4F60");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("宋体", Font.BOLD, 20));
		label_1.setBackground(Color.WHITE);
		label_1.setBounds(82, 60, 63, 24);
		panel.add(label_1);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new ChooseMenu().setVisible(true);
			}
		});
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setIcon(new ImageIcon(MainMenu.class.getResource("/ui/quit.png")));
		btnNewButton_1.setBounds(0, 562, 42, 38);
		btnNewButton_1.setContentAreaFilled(false);
		panel.add(btnNewButton_1);

		table = new JTable(model);
		table.setFont(new Font("宋体", Font.PLAIN, 20));
		table.getTableHeader().setVisible(false);
		table.setBounds(10, 189, 167, 346);
		table.setRowHeight(25);
		panel.add(table);

		JLabel label_7 = new JLabel("共" + String.valueOf(names.size()) + "人");
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("宋体", Font.BOLD, 20));
		label_7.setBackground(Color.WHITE);
		label_7.setBounds(66, 545, 124, 24);
		panel.add(label_7);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(196, 0, 604, 600);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel label_3 = new JLabel("\u4FEE\u6539");
		label_3.setForeground(Color.BLUE);
		label_3.setFont(new Font("宋体", Font.BOLD, 20));
		label_3.setBackground(Color.WHITE);
		label_3.setBounds(30, 10, 124, 24);
		panel_1.add(label_3);

		JLabel label_4 = new JLabel("\u7528\u6237\u540D");
		label_4.setForeground(Color.BLUE);
		label_4.setFont(new Font("宋体", Font.BOLD, 20));
		label_4.setBackground(Color.WHITE);
		label_4.setBounds(30, 44, 72, 24);
		panel_1.add(label_4);

		JLabel label_5 = new JLabel("\u5BC6\u7801");
		label_5.setForeground(Color.BLUE);
		label_5.setFont(new Font("宋体", Font.BOLD, 20));
		label_5.setBackground(Color.WHITE);
		label_5.setBounds(30, 83, 72, 24);
		panel_1.add(label_5);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(112, 87, 113, 21);
		panel_1.add(textField_1);

		JButton btnNewButton = new JButton("\u5220\u9664");

		btnNewButton.setBounds(235, 62, 93, 23);
		panel_1.add(btnNewButton);

		JButton btnNewButton_2 = new JButton("\u4FDD\u5B58");

		btnNewButton_2.setBounds(338, 62, 93, 23);
		panel_1.add(btnNewButton_2);

		JButton button = new JButton("\u590D\u4F4D");

		button.setBounds(441, 62, 93, 23);
		panel_1.add(button);

		JLabel label_6 = new JLabel("\u57FA\u672C\u6743\u9650\u5206\u914D");
		label_6.setForeground(Color.BLUE);
		label_6.setFont(new Font("宋体", Font.BOLD, 20));
		label_6.setBackground(Color.WHITE);
		label_6.setBounds(30, 173, 143, 24);
		panel_1.add(label_6);

		table_1 = new JTable(model1);
		table_1.setFont(new Font("宋体", Font.PLAIN, 20));
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_1.setBounds(10, 207, 250, 340);
		table_1.getTableHeader().setVisible(false);
		table_1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table_1.setRowHeight(25);
		panel_1.add(table_1);

		table_2 = new JTable(model2);
		table_2.setFont(new Font("宋体", Font.PLAIN, 20));
		table_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_2.setBounds(320, 207, 250, 340);
		table_2.getTableHeader().setVisible(false);
		table_2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table_2.setRowHeight(25);
		panel_1.add(table_2);

		JButton button_1 = new JButton("=>");

		button_1.setBounds(262, 221, 58, 23);
		panel_1.add(button_1);

		JButton button_2 = new JButton("<=");

		button_2.setBounds(262, 391, 58, 23);
		panel_1.add(button_2);

		JLabel label_8 = new JLabel("\u672A\u5206\u9879\u76EE");
		label_8.setForeground(Color.BLUE);
		label_8.setFont(new Font("宋体", Font.BOLD, 20));
		label_8.setBackground(Color.WHITE);
		label_8.setBounds(82, 566, 124, 24);
		panel_1.add(label_8);

		JLabel label_9 = new JLabel("\u5DF2\u5206\u9879");
		label_9.setForeground(Color.BLUE);
		label_9.setFont(new Font("宋体", Font.BOLD, 20));
		label_9.setBackground(Color.WHITE);
		label_9.setBounds(404, 566, 124, 24);
		panel_1.add(label_9);

		JTextField label_10 = new JTextField("");
		label_10.setForeground(Color.BLUE);
		label_10.setFont(new Font("宋体", Font.BOLD, 20));
		label_10.setBackground(Color.WHITE);
		label_10.setBounds(112, 44, 113, 24);
		panel_1.add(label_10);

		JLabel label_11 = new JLabel("");
		label_11.setForeground(Color.RED);
		label_11.setFont(new Font("宋体", Font.BOLD, 20));
		label_11.setBackground(Color.WHITE);
		label_11.setBounds(235, 95, 299, 24);
		panel_1.add(label_11);

		table.addMouseListener(new java.awt.event.MouseAdapter() {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				String n = "";
				try {
					n = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
				} catch (Exception er) {
					// TODO: handle exception
				}
				if (!n.isEmpty()) {
					label_11.setText("");
					String name = (String) table.getValueAt(table.getSelectedRow(), 0); // 获取所选中的行的第一个位置的内容，当然你也可以指定具体的该行第几格
					for (Map<String, Object> map : users) {
						// map = String.valueOf(map.get("thisDate"));
						if (String.valueOf(map.get("username")).equals(name)) {
							GlobalVar.name = name;
							GlobalVar.password = String.valueOf(map.get("password"));
							label_10.setText(name);
							textField_1.setText(GlobalVar.password);

							rightsno = new MySQLHelper().query(
									"select * from rights2 where id not in (SELECT r.id from usersrights2 ur, users2 u,rights2 r where ur.userID=u.id and r.id=ur.rightID and u.username = '"
											+ GlobalVar.name + "')");

							typeright1.clear();
							String lastone = "";
							norights = 0;
							for (Map<String, Object> right : rightsno) {
								Vector<String> temp = new Vector<>();
								String module = String.valueOf(right.get("Module"));
								temp.add(module);
								lastone = module;
								temp.add(String.valueOf(right.get("rightName")));
								// System.out.println(String.valueOf(right.get("rightName")));
								typeright1.add(temp);
								norights++;
							}

							rightsyes = new MySQLHelper().query(
									"SELECT * from usersrights2 ur, users2 u,rights2 r where ur.userID=u.id and r.id=ur.rightID and u.username = '"
											+ GlobalVar.name + "'");

							typeright2.clear();
							yesrights = 0;
							lastone = "";
							for (Map<String, Object> right : rightsyes) {
								Vector<String> temp = new Vector<>();
								String module = String.valueOf(right.get("Module"));
								temp.add(module);
								lastone = module;
								temp.add(String.valueOf(right.get("rightName")));
								// System.out.println(String.valueOf(right.get("rightName")));
								typeright2.add(temp);
								yesrights++;
							}
							label_8.setText("未分配：" + String.valueOf(norights));
							label_9.setText("已分配：" + String.valueOf(yesrights));
							table_1.revalidate();
							table_1.updateUI();
							table_2.revalidate();
							table_2.updateUI();
						}
					}
				}
				repaint();
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!label_10.getText().equals("")) {
					if (!nameMap.containsKey(label_10.getText())) {
						label_11.setText("没有这个用户，请重新输入");
						return;
					}
					if (JOptionPane.showConfirmDialog(null, "删除该用户？" + label_10.getText(), "提示",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						new MySQLHelper()
								.executeNonquery("delete from users2 where username = '" + label_10.getText() + "'");
						new MySQLHelper().executeNonquery(
								"delete ur from usersrights2 ur,users2 u where ur.userID=u.id and u.username = '"
										+ label_10.getText() + "'");

						int i = table.getSelectedRow();
						if (i < 0)
							return;
						names.remove(i);
						table.updateUI();

						label_10.setText("");

						textField_1.setText("");

						((DefaultTableModel) table_1.getModel()).getDataVector().clear();
						((DefaultTableModel) table_1.getModel()).fireTableDataChanged();
						table_1.updateUI();
						((DefaultTableModel) table_2.getModel()).getDataVector().clear();
						((DefaultTableModel) table_2.getModel()).fireTableDataChanged();
						table_2.updateUI();
						label_11.setText("已删除");
					}
				} else
					label_11.setText("用户名不能为空");
			}
		});

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// norights = 0;
				// yesrights = 0;
				// label_8.setText("未分配：" + String.valueOf(norights));
				// label_9.setText("已分配：" + String.valueOf(yesrights));
				// label_10.setText("");
				// textField_1.setText("");
				// GlobalVar.name = "";
				// ((DefaultTableModel) table_1.getModel()).getDataVector().clear();
				// ((DefaultTableModel) table_1.getModel()).fireTableDataChanged();
				// table_1.updateUI();
				// ((DefaultTableModel) table_2.getModel()).getDataVector().clear();
				// ((DefaultTableModel) table_2.getModel()).fireTableDataChanged();
				// table_2.updateUI();
				String n = "";
				try {
					n = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
				} catch (Exception er) {
					// TODO: handle exception
				}
				if (!n.isEmpty()) {
					label_11.setText("");
					String name = (String) table.getValueAt(table.getSelectedRow(), 0); // 获取所选中的行的第一个位置的内容，当然你也可以指定具体的该行第几格
					for (Map<String, Object> map : users) {
						// map = String.valueOf(map.get("thisDate"));
						if (String.valueOf(map.get("username")).equals(name)) {
							GlobalVar.name = name;
							GlobalVar.password = String.valueOf(map.get("password"));
							label_10.setText(name);
							textField_1.setText(GlobalVar.password);

							rightsno = new MySQLHelper().query(
									"select * from rights2 where id not in (SELECT r.id from usersrights2 ur, users2 u,rights2 r where ur.userID=u.id and r.id=ur.rightID and u.username = '"
											+ GlobalVar.name + "')");

							typeright1.clear();
							String lastone = "";
							norights = 0;
							for (Map<String, Object> right : rightsno) {
								Vector<String> temp = new Vector<>();
								String module = String.valueOf(right.get("Module"));
								temp.add(module);
								lastone = module;
								temp.add(String.valueOf(right.get("rightName")));
								// System.out.println(String.valueOf(right.get("rightName")));
								typeright1.add(temp);
								norights++;
							}

							rightsyes = new MySQLHelper().query(
									"SELECT * from usersrights2 ur, users2 u,rights2 r where ur.userID=u.id and r.id=ur.rightID and u.username = '"
											+ GlobalVar.name + "'");

							typeright2.clear();
							yesrights = 0;
							lastone = "";
							for (Map<String, Object> right : rightsyes) {
								Vector<String> temp = new Vector<>();
								String module = String.valueOf(right.get("Module"));
								temp.add(module);
								lastone = module;
								temp.add(String.valueOf(right.get("rightName")));
								// System.out.println(String.valueOf(right.get("rightName")));
								typeright2.add(temp);
								yesrights++;
							}
							label_8.setText("未分配：" + String.valueOf(norights));
							label_9.setText("已分配：" + String.valueOf(yesrights));
							table_1.revalidate();
							table_1.updateUI();
							table_2.revalidate();
							table_2.updateUI();
						}
					}
				}
				repaint();
			}
		});

		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int[] a = table_1.getSelectedRows();
				// System.out.println(a.length);
				if (a.length < 0)
					return;
				for (int i = 0; i < a.length; i++) {

					typeright2.add(typeright1.get(a[i]));
					norights--;
					yesrights++;
				}
				for (int i = a.length - 1; i >= 0; i--) {

					typeright1.remove(a[i]);
				}
				table_1.setRowSorter(null);
				table_2.setRowSorter(null);
				table_1.updateUI();
				table_2.updateUI();
				label_8.setText("未分配：" + String.valueOf(norights));
				label_9.setText("已分配：" + String.valueOf(yesrights));
			}
		});

		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int[] a = table_2.getSelectedRows();
				// System.out.println(a.length);
				if (a.length < 0)
					return;
				for (int i = 0; i < a.length; i++) {
					typeright1.add(typeright2.get(a[i]));
					norights++;
					yesrights--;

				}
				for (int i = a.length - 1; i >= 0; i--) {
					typeright2.remove(a[i]);
				}
				table_1.setRowSorter(null);
				table_2.setRowSorter(null);
				table_1.updateUI();
				table_2.updateUI();
				label_8.setText("未分配：" + String.valueOf(norights));
				label_9.setText("已分配：" + String.valueOf(yesrights));

			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nameMap.containsKey(label_10.getText().trim())
						&& (!label_10.getText().trim().equals(GlobalVar.name))) {
					label_11.setText("添加用户为已存在用户");
					return;
				}
				if (!nameMap.containsKey(label_10.getText())) {
					new MySQLHelper().executeNonquery("Insert into users2 (username,password) values ('"
							+ label_10.getText().trim() + "','" + textField_1.getText() + "')");
					label_11.setText("注册成功，请重新登录");
					return;
				}
				new MySQLHelper().executeNonquery("update users2 set password = '" + textField_1.getText()
						+ "' where username = '" + GlobalVar.name + "'");
				new MySQLHelper().executeNonquery(
						"delete ur from usersrights2 ur, users2 u where ur.userID=u.id and u.username = '"
								+ GlobalVar.name + "'");
				for (Vector<String> v : typeright2) {
					new MySQLHelper().executeNonquery("insert into usersrights2 (userID,rightID) values ("
							+ String.valueOf(nameMap.get(GlobalVar.name)) + "," + String.valueOf(rightMap.get(v.get(1)))
							+ ")");
				}
				label_10.setText("");
				textField_1.setText("");
				label_11.setText("保存成功");

			}
		});
	}

	void loadRight() {

	}
}
