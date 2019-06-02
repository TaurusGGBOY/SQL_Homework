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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dal.MySQLHelper;

public class KeyInput extends JPanel {
	private JTextField textField_en;
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
	private JTextField textField_wn;

	Vector<Vector<String>> table1Columns = new Vector<>();
	Vector<Vector<String>> table2Columns = new Vector<>();
	Vector<String> columns1 = new Vector<>();
	Vector<String> columns2 = new Vector<>();

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	int chooseRow;
	private JLabel label_tip;
	private JTextField textField_wp;
	private JTextField textField_ep;
	private JTextField textField_iwn;
	private JTextField textField_tp;
	private JTextField textField_ien;
	private JTextField textField_op;
	private JTextField textField_wsp;
	private JTextField textField_esp;
	private JTextField textField_gp;
	private JTextField textField_agp;
	private int elePrice = 0;
	private int watPrice = 0;
	private JTextField textField_wpp;
	private JTextField textField_epp;

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
		scrollPane.setBounds(10, 36, 580, 338);
		add(scrollPane);

		loadTable1();
		table = new JTable(model1);

		table.setFont(new Font("宋体", Font.PLAIN, 20));
		table.setRowHeight(25);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.requestFocus();
		scrollPane.setViewportView(table);

		ActionMap am = table.getActionMap();
		am.getParent().remove("selectNextRowCell");
		table.setActionMap(am);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(5, 10, 590, 375);
		add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(5, 395, 590, 198);
		add(panel_1);
		panel_1.setLayout(null);

		textField_en = new JTextField();
		textField_en.setBounds(160, 87, 70, 27);
		panel_1.add(textField_en);
		textField_en.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_en.setEditable(false);
		textField_en.setColumns(10);

		JLabel lblId = new JLabel("\u59D3\u540D");
		lblId.setBounds(10, 46, 39, 29);
		panel_1.add(lblId);
		lblId.setFont(new Font("宋体", Font.PLAIN, 16));

		textField_name = new JTextField();
		textField_name.setBounds(46, 48, 70, 27);
		panel_1.add(textField_name);
		textField_name.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_name.setColumns(10);
		textField_name.setEditable(false);

		textField_number = new JTextField();
		textField_number.setBounds(160, 46, 70, 27);
		panel_1.add(textField_number);
		textField_number.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_number.setColumns(10);
		textField_number.setEditable(false);

		JLabel lblNum = new JLabel("\u5BDD\u5BA4");
		lblNum.setBounds(124, 46, 39, 29);
		panel_1.add(lblNum);
		lblNum.setFont(new Font("宋体", Font.PLAIN, 16));

		JLabel label_4 = new JLabel("\u6C34");
		label_4.setBounds(10, 81, 32, 29);
		panel_1.add(label_4);
		label_4.setFont(new Font("宋体", Font.PLAIN, 16));

		JLabel label_6 = new JLabel("\u7535");
		label_6.setBounds(125, 81, 39, 29);
		panel_1.add(label_6);
		label_6.setFont(new Font("宋体", Font.PLAIN, 16));

		textField_wn = new JTextField();
		textField_wn.setBounds(45, 87, 70, 27);
		panel_1.add(textField_wn);
		textField_wn.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_wn.setColumns(10);
		textField_wn.setEditable(false);

		JLabel label_1 = new JLabel("\u6C34\u7535");
		label_1.setBounds(10, 10, 54, 29);
		panel_1.add(label_1);
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));

		JLabel label = new JLabel("\u6C34\u8D39");
		label.setBounds(10, 120, 32, 29);
		panel_1.add(label);
		label.setFont(new Font("宋体", Font.PLAIN, 16));

		JLabel label_5 = new JLabel("\u7535\u8D39");
		label_5.setBounds(125, 120, 39, 29);
		panel_1.add(label_5);
		label_5.setFont(new Font("宋体", Font.PLAIN, 16));

		textField_wp = new JTextField();
		textField_wp.setBounds(45, 120, 70, 27);
		panel_1.add(textField_wp);
		textField_wp.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_wp.setEditable(false);
		textField_wp.setColumns(10);

		textField_ep = new JTextField();
		textField_ep.setBounds(160, 120, 70, 27);
		panel_1.add(textField_ep);
		textField_ep.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_ep.setEditable(false);
		textField_ep.setColumns(10);

		JLabel label_2 = new JLabel("\u6C34\u8868\u793A\u6570");
		label_2.setBounds(248, 46, 66, 29);
		panel_1.add(label_2);
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));

		JLabel label_3 = new JLabel("\u7535\u8868\u793A\u6570");
		label_3.setBounds(400, 46, 64, 29);
		panel_1.add(label_3);
		label_3.setFont(new Font("宋体", Font.PLAIN, 16));

		JLabel label_8 = new JLabel("\u603B\u8BA1");
		label_8.setBounds(10, 159, 39, 29);
		panel_1.add(label_8);
		label_8.setFont(new Font("宋体", Font.PLAIN, 16));

		JButton btnNewButton = new JButton("\u4FDD\u5B58");

		btnNewButton.setBounds(474, 10, 106, 34);
		panel_1.add(btnNewButton);
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));

		label_tip = new JLabel("");
		label_tip.setBounds(137, 10, 327, 27);
		panel_1.add(label_tip);
		label_tip.setForeground(Color.BLUE);
		label_tip.setFont(new Font("宋体", Font.PLAIN, 20));

		textField_iwn = new JTextField();
		textField_iwn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ENTER:
					textField_wsp.setText(String.valueOf(
							(Integer.parseInt(textField_iwn.getText()) - Integer.parseInt(textField_wn.getText()))
									* Integer.parseInt(textField_wpp.getText())));
					textField_ien.requestFocus();
					break;
				default:
					break;
				}
			}
		});

		textField_iwn.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_iwn.setBounds(324, 51, 70, 27);
		panel_1.add(textField_iwn);
		textField_iwn.setColumns(10);

		textField_tp = new JTextField();
		textField_tp.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_tp.setEditable(false);
		textField_tp.setColumns(10);
		textField_tp.setBounds(46, 160, 70, 27);
		panel_1.add(textField_tp);

		textField_ien = new JTextField();
		textField_ien.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ENTER:
					textField_esp.setText(String.valueOf(
							(Integer.parseInt(textField_ien.getText()) - Integer.parseInt(textField_en.getText()))
									* Integer.parseInt(textField_epp.getText())));
					textField_gp.requestFocus();
					break;
				default:
					break;
				}
			}
		});
		textField_ien.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_ien.setColumns(10);
		textField_ien.setBounds(472, 51, 70, 27);
		panel_1.add(textField_ien);

		JLabel label_7 = new JLabel("\u6B20\u8D39");
		label_7.setFont(new Font("宋体", Font.PLAIN, 16));
		label_7.setBounds(124, 159, 32, 29);
		panel_1.add(label_7);

		textField_op = new JTextField();
		textField_op.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_op.setEditable(false);
		textField_op.setColumns(10);
		textField_op.setBounds(160, 160, 70, 27);
		panel_1.add(textField_op);

		JLabel label_9 = new JLabel("\u6C34\u5E94\u7F34");
		label_9.setFont(new Font("宋体", Font.PLAIN, 16));
		label_9.setBounds(248, 117, 66, 29);
		panel_1.add(label_9);

		textField_wsp = new JTextField();
		textField_wsp.setEditable(false);
		textField_wsp.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_wsp.setColumns(10);
		textField_wsp.setBounds(324, 122, 70, 27);
		panel_1.add(textField_wsp);

		JLabel label_10 = new JLabel("\u7535\u5E94\u7F34");
		label_10.setFont(new Font("宋体", Font.PLAIN, 16));
		label_10.setBounds(400, 117, 64, 29);
		panel_1.add(label_10);

		textField_esp = new JTextField();
		textField_esp.setEditable(false);
		textField_esp.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_esp.setColumns(10);
		textField_esp.setBounds(472, 122, 70, 27);
		panel_1.add(textField_esp);

		JLabel label_11 = new JLabel("\u7F34\u8D39");
		label_11.setFont(new Font("宋体", Font.PLAIN, 16));
		label_11.setBounds(248, 155, 66, 29);
		panel_1.add(label_11);

		textField_gp = new JTextField();
		textField_gp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ENTER:
					textField_agp.setText(String.valueOf(Integer.parseInt(textField_wsp.getText())
							+ Integer.parseInt(textField_esp.getText()) + Integer.parseInt(textField_op.getText())
							- Integer.parseInt(textField_gp.getText())));
					btnNewButton.requestFocus();
					break;
				default:
					break;
				}
			}
		});
		textField_gp.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_gp.setColumns(10);
		textField_gp.setBounds(324, 160, 70, 27);
		panel_1.add(textField_gp);

		JLabel label_12 = new JLabel("\u7F34\u8D39\u540E");
		label_12.setFont(new Font("宋体", Font.PLAIN, 16));
		label_12.setBounds(400, 156, 64, 29);
		panel_1.add(label_12);

		textField_agp = new JTextField();
		textField_agp.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_agp.setEditable(false);
		textField_agp.setColumns(10);
		textField_agp.setBounds(472, 161, 70, 27);
		panel_1.add(textField_agp);

		JLabel label_13 = new JLabel("\u6C34\u5355\u4EF7");
		label_13.setFont(new Font("宋体", Font.PLAIN, 16));
		label_13.setBounds(248, 81, 66, 29);
		panel_1.add(label_13);

		textField_wpp = new JTextField();
		textField_wpp.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_wpp.setEditable(false);
		textField_wpp.setColumns(10);
		textField_wpp.setBounds(324, 86, 70, 27);
		panel_1.add(textField_wpp);

		JLabel 电单价 = new JLabel("\u7535\u5355\u4EF7");
		电单价.setFont(new Font("宋体", Font.PLAIN, 16));
		电单价.setBounds(400, 81, 64, 29);
		panel_1.add(电单价);

		textField_epp = new JTextField();
		textField_epp.setFont(new Font("宋体", Font.PLAIN, 16));
		textField_epp.setEditable(false);
		textField_epp.setColumns(10);
		textField_epp.setBounds(472, 86, 70, 27);
		panel_1.add(textField_epp);

		init();

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
				setSetting();
				loadTable1();
				table.requestFocus();
				label_tip.setText("保存成功");
			}
		});

		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				save();
				setSetting();
				loadTable1();
				table.requestFocus();
				label_tip.setText("保存成功");
			}
		});

		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				chooseRow = table.getSelectedRow();
				switch (arg0.getKeyCode()) {
				case KeyEvent.VK_ENTER:
					textField_en.setText((String) table.getValueAt(chooseRow, 0));
					textField_tp.setText((String) table.getValueAt(chooseRow, 1));
					textField_ep.setText((String) table.getValueAt(chooseRow, 2));
					textField_wp.setText((String) table.getValueAt(chooseRow, 3));
					textField_op.setText((String) table.getValueAt(chooseRow, 4));
					textField_number.setText((String) table.getValueAt(chooseRow, 5));
					textField_wn.setText((String) table.getValueAt(chooseRow, 6));
					textField_name.setText((String) table.getValueAt(chooseRow, 7));
					label_tip.setText("");
					textField_iwn.requestFocus();
					break;
				default:
					break;
				}
			}

		});
	}

	void loadTable1() {
		columns1 = new Vector<String>();
		table1Columns = new Vector<>();
		infoList1 = new MySQLHelper().query("SELECT * from design_numprice");
		for (Map<String, Object> info : infoList1) {
			for (String column : info.keySet()) {
				columns1.add(column);
			}
			break;
		}
		columns1.remove("搜索项");

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

	void setSetting() {
		label_tip.setText("");
		textField_en.setText("");
		textField_tp.setText("");
		textField_ep.setText("");
		textField_wp.setText("");
		textField_op.setText("");
		textField_number.setText("");
		textField_wn.setText("");
		textField_name.setText("");
		textField_wsp.setText("");
		textField_esp.setText("");
		textField_agp.setText("");
		textField_iwn.setText("");
		textField_ien.setText("");
		textField_gp.setText("");
	}

	void save() {
		Map<String, String> map = new HashMap<>();
		map.put("水表示数", textField_iwn.getText());
		map.put("电表示数", textField_ien.getText());
		map.put("电费",
				String.valueOf(Integer.parseInt(textField_ep.getText()) + Integer.parseInt(textField_esp.getText())));
		map.put("水费",
				String.valueOf(Integer.parseInt(textField_wp.getText()) + Integer.parseInt(textField_wsp.getText())));
		map.put("总计",
				String.valueOf(Integer.parseInt(textField_ep.getText()) + Integer.parseInt(textField_esp.getText())
						+ Integer.parseInt(textField_ep.getText()) + Integer.parseInt(textField_esp.getText())));
		map.put("欠费", textField_agp.getText());
		for (String string : map.keySet()) {
			if (Integer.parseInt(map.get(string)) < 0) {
				label_tip.setText("输入的参数错误");
				return;
			}
		}

		StringBuilder sql = new StringBuilder();

		sql.append("update design_numprice set ");
		for (String column : map.keySet()) {
			if (column.equals("姓名"))
				continue;
			sql.append(column);
			sql.append("='");
			sql.append(map.get(column));
			sql.append("',");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(" where 姓名='");
		sql.append(textField_name.getText());
		sql.append("'");
		System.out.println(sql.toString());
		new MySQLHelper().executeNonquery(sql.toString());

	}

	void init() {
		infoList2 = new MySQLHelper().query("SELECT * from design_price");
		for (Map<String, Object> info : infoList2) {
			watPrice = Integer.parseInt(info.get("水").toString());
			elePrice = Integer.parseInt(info.get("电").toString());
			break;
		}
		textField_epp.setText(String.valueOf(elePrice));
		textField_wpp.setText(String.valueOf(watPrice));
	}
}
