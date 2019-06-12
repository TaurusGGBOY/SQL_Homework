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
import java.util.Vector;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dal.MySQLHelper;

public class ReportOneSee extends JPanel {
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

	/**
	 * Create the panel.
	 */
	public ReportOneSee() {
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 64, 565, 514);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton button_1 = new JButton("\u6253\u5370");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MessageFormat footer = new MessageFormat("- {0} -"); // 页脚加页码
				MessageFormat header = new MessageFormat(
						"2019年水电报表                    \r\n报表生成时间:" + String.valueOf(sdf.format(new Date()))); // 页眉加时间
				PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
				aset.add(OrientationRequested.PORTRAIT); // 横排列打印，改为OrientationRequested.LANDSCAPE为竖排列
				try {
					table.print(JTable.PrintMode.FIT_WIDTH, header, footer, true, aset, true);
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (PrinterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_1.setBounds(497, 9, 93, 23);
		add(button_1);

		JButton button_2 = new JButton("\u4FDD\u5B58");
		button_2.addActionListener(new ActionListener() {
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
				// System.out.println("write out to: " + file);
			}
		});
		button_2.setBounds(394, 9, 93, 23);
		add(button_2);
		init();

	}

	void init() {
		loadTable("SELECT * from design_numprice");
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
