package ui;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import dal.MySQLHelper;

public class EnterPanel extends JPanel {
	private String clickedInfo;

	/**
	 * Create the panel.
	 */
	public EnterPanel() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("\u6B22\u8FCE\u8FDB\u5165");
		lblNewLabel.setFont(new Font("ÀŒÃÂ", Font.PLAIN, 30));
		lblNewLabel.setBounds(242, 96, 127, 35);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(
				"\u897F\u5357\u4EA4\u901A\u5927\u5B66\u6C34\u7535\u7BA1\u7406\u7CFB\u7EDF\u5B50\u7CFB\u7EDF");
		lblNewLabel_1.setFont(new Font("ÀŒÃÂ", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(90, 143, 455, 83);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(EnterPanel.class.getResource("/ui/\u6821\u5FBD.png")));
		lblNewLabel_2.setBounds(176, 259, 250, 238);
		add(lblNewLabel_2);
		Vector<Vector<String>> bjsNames = new Vector<>();
		List<Map<String, Object>> infoList = new ArrayList<Map<String, Object>>();
		Vector<String> columns = new Vector<>();

		infoList = new MySQLHelper().query("SELECT * from basic1");
		for (Map<String, Object> info : infoList) {
			int id = Integer.parseInt(String.valueOf(info.get("ID")));
			Vector<String> temp = new Vector<>();
			String bjsName = String.valueOf(info.get("bjsName"));
			temp.add(bjsName);
			bjsNames.add(temp);
		}
		columns.add("±‡º≠ “");
		DefaultTableModel model = new DefaultTableModel(bjsNames, columns);

	}
}
