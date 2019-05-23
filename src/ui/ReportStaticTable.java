package ui;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dal.MySQLHelper;

public class ReportStaticTable extends JPanel {
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
	private JTable table;
	JComboBox<String> comboBox = new JComboBox<>();

	/**
	 * Create the panel.
	 */
	public ReportStaticTable() {
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 82, 580, 456);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		init();

		JButton btnNewButton_1 = new JButton("\u6253\u5370");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessageFormat footer = new MessageFormat("- {0} -"); // 页脚加页码
				MessageFormat header = new MessageFormat(
						"2017年入库图书 统计                   \r\n报表生成时间:" + String.valueOf(sdf.format(new Date()))); // 页眉加时间
				PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
				aset.add(OrientationRequested.PORTRAIT); // 横排列打印，改为OrientationRequested.LANDSCAPE为竖排列
				try {
					table.print(JTable.PrintMode.FIT_WIDTH, header, footer, true, aset, true);
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (PrinterException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(497, 27, 93, 23);
		add(btnNewButton_1);

		JButton button = new JButton("\u4FDD\u5B58");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileDialog fd = new FileDialog(new Frame(), "保存", FileDialog.SAVE);
				fd.setLocation(400, 250);
				fd.setVisible(true);
				String stringfile = fd.getDirectory() + fd.getFile() + ".xls";
				try {
					exportTable(table, new File(stringfile));
				} catch (IOException ex) {
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}

			void exportTable(JTable table, File file) throws IOException {
				TableModel model = table.getModel();
				BufferedWriter bWriter = new BufferedWriter(new FileWriter(file));
				for (int i = 0; i < model.getColumnCount(); i++) {
					bWriter.write(model.getColumnName(i));
					bWriter.write("\t");
				}
				bWriter.newLine();
				for (int i = 0; i < model.getRowCount(); i++) {
					for (int j = 0; j < model.getColumnCount(); j++) {
						try {
							bWriter.write(model.getValueAt(i, j).toString());
						} catch (Exception e) {
							// TODO: handle exception
						}
						bWriter.write("\t");

					}
					bWriter.newLine();
				}
				bWriter.close();
				System.out.println("write out to: " + file);
			}
		});

		button.setBounds(387, 27, 93, 23);

		add(button);

		comboBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				switch (String.valueOf(comboBox.getSelectedItem())) {
				case "":
					loadTable("select * from bookonesee1 bo1,bookstorage1 bs1 where bo1.书号=bs1.书号");
					break;

				default:
					loadTable("select * from bookonesee1 bo1,bookstorage1 bs1 where bo1.书号=bs1.书号  and 图书分类='"
							+ String.valueOf(comboBox.getSelectedItem()) + "'");
					break;
				}
			}
		});
		comboBox.setBounds(83, 27, 108, 21);
		add(comboBox);

		JLabel lblNewLabel = new JLabel("\u5206\u7C7B");
		lblNewLabel.setBounds(19, 30, 54, 15);

		add(lblNewLabel);

	}

	void init() {
		loadTable("select * from bookonesee1 bo1,bookstorage1 bs1 where bo1.书号=bs1.书号");

		Set<String> type = new HashSet<>();
		comboBox.addItem("");
		for (Map<String, Object> info : infoList2) {
			if (!type.contains(String.valueOf(info.get("图书分类")))) {
				type.add(String.valueOf(info.get("图书分类")));
				comboBox.addItem(String.valueOf(info.get("图书分类")));
			}

		}
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

		int sumNum = 0;
		double sumPrice = 0;
		for (Map<String, Object> info : infoList2) {
			temp = new Vector<>();
			for (String column : columns2) {
				temp.add(String.valueOf(info.get(column)));
			}
			sumPrice += Double.parseDouble(String.valueOf(info.get("单价")))
					* Integer.parseInt(String.valueOf(info.get("库存册数")));
			sumNum += Integer.parseInt(String.valueOf(info.get("库存册数")));
			table2Columns.add(temp);
		}
		temp = new Vector<>();
		temp.add("合计");
		temp.add(String.valueOf(sumPrice));
		temp.add("");
		temp.add(String.valueOf(sumNum));
		table2Columns.add(temp);
		DefaultTableModel model_temp = new DefaultTableModel(table2Columns, columns2);
		table.setModel(model_temp);
		new MySQLHelper().FitTableColumns(table);
	}

}
