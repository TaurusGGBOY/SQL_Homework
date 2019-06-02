package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dal.MySQLHelper;

public class ResetPassword extends JPanel {
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
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public ResetPassword() {
		setLayout(null);

		JButton btnNewButton = new JButton("\u91CD\u7F6E\u5BC6\u7801");

		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.setBounds(240, 316, 127, 43);
		add(btnNewButton);

		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setBounds(116, 244, 377, 43);
		add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(115, 369, 367, 43);
		add(lblNewLabel);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = textField.getText();
				// Query
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				list = new MySQLHelper().query("select * from design_user where username = '" + username + "'");
				if (list.isEmpty())
					lblNewLabel.setText("没有这个用户名");
				else {
					new MySQLHelper().executeNonquery(
							"update design_user set password = '123456' where username = '" + username + "'");
					textField.setText("");
					lblNewLabel.setText("密码已更新为123456");
				}

			}

		});

	}
}
