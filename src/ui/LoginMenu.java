package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dal.MySQLHelper;

public class LoginMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	int times;
	int days;
	int num;
	int lock_days;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginMenu frame = new LoginMenu();
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
	public LoginMenu() {

		List<Map<String, Object>> argments = new ArrayList<Map<String, Object>>();
		argments = new MySQLHelper().query("select * from argments");
		for (Map<String, Object> map : argments) {
			num = Integer.parseInt(map.get("num").toString());
			days = Integer.parseInt(map.get("days").toString());
			lock_days = Integer.parseInt(map.get("lock_days").toString());
			break;

		}

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
		panel.setBounds(0, 0, 500, 600);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(LoginMenu.class.getResource("/ui/\u767B\u5F55\u56FE\u6807.png")));
		label_5.setBounds(225, 274, 65, 74);
		panel.add(label_5);

		JLabel label_6 = new JLabel("\u4EA4\u5927\u767B\u5F55\u7BA1\u7406\u7CFB\u7EDF");
		label_6.setForeground(new Color(0, 102, 204));
		label_6.setFont(new Font("宋体", Font.BOLD, 24));
		label_6.setBounds(160, 372, 200, 29);
		panel.add(label_6);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LoginMenu.class.getResource("/ui/\u767B\u5F55\u5C01\u9762.png")));
		lblNewLabel.setBounds(0, 0, 500, 600);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(500, 0, 300, 600);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("\u767B\u5F55");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(130, 360, 42, 24);
		panel_1.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("");

		btnNewButton.setIcon(new ImageIcon(LoginMenu.class.getResource("/ui/\u6309\u94AE.png")));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(60, 351, 191, 41);
		btnNewButton.setBorder(null);
		btnNewButton.setContentAreaFilled(false);
		panel_1.add(btnNewButton);

		JLabel label_4 = new JLabel("\u9000\u51FA");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("宋体", Font.BOLD, 18));
		label_4.setBounds(130, 432, 42, 24);
		panel_1.add(label_4);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button.setIcon(new ImageIcon(LoginMenu.class.getResource("/ui/\u6309\u94AE.png")));
		button.setForeground(Color.WHITE);
		button.setContentAreaFilled(false);
		button.setBorder(null);
		button.setBackground(Color.WHITE);
		button.setBounds(60, 423, 191, 41);
		panel_1.add(button);

		textField = new JTextField(new MySQLHelper().getProperty("auto_id"));
		textField.setForeground(Color.DARK_GRAY);
		textField.setFont(new Font("宋体", Font.BOLD, 16));
		textField.setBounds(101, 158, 162, 44);
		textField.setBorder(null);
		panel_1.add(textField);
		textField.setColumns(10);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(LoginMenu.class.getResource("/ui/\u5206\u5272\u7EBF.png")));
		label.setBounds(51, 198, 223, 15);
		panel_1.add(label);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(LoginMenu.class.getResource("/ui/\u5206\u5272\u7EBF.png")));
		label_1.setBounds(51, 293, 223, 19);
		panel_1.add(label_1);

		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(LoginMenu.class.getResource("/ui/\u7528\u62372.png")));
		label_2.setBounds(51, 158, 40, 40);
		panel_1.add(label_2);

		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(LoginMenu.class.getResource("/ui/password.png")));
		label_3.setBounds(60, 263, 20, 20);
		panel_1.add(label_3);

		passwordField = new JPasswordField(new MySQLHelper().getProperty("auto_password"));
		passwordField.setForeground(Color.DARK_GRAY);
		passwordField.setFont(new Font("宋体", Font.BOLD, 16));
		passwordField.setBounds(103, 254, 162, 44);
		passwordField.setBorder(null);
		panel_1.add(passwordField);

		JLabel lblAsdsad = new JLabel("");
		lblAsdsad.setForeground(Color.BLUE);
		lblAsdsad.setBounds(10, 495, 280, 68);
		panel_1.add(lblAsdsad);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Save password and user's name
				String username = textField.getText();
				String password = String.valueOf(passwordField.getPassword());
				new MySQLHelper().setProperty("auto_id", username);
				new MySQLHelper().setProperty("auto_password", password);

				// Query
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				list = new MySQLHelper().query("select * from design_user where username = '" + username + "'");
				if (list.isEmpty())
					lblAsdsad.setText("没有这个用户名");
				else
					for (Map<String, Object> map : list) {
						String user = map.get("username").toString();
						String pass = map.get("password").toString();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date now = new Date();
						now.getTime();
						String unlockTime = sdf.format(map.get("unlock_time"));

						boolean isLock = true;
						try {
							if (now.before(sdf.parse(unlockTime))) {
								isLock = true;
							} else
								isLock = false;
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (isLock) {
							lblAsdsad.setText("锁定到" + unlockTime);
						} else {
							if (user.equals(username)) {
								times = Integer.parseInt(map.get("fault_time").toString());

								if (pass.equals(password)) {
									GlobalVar.times = times;
									GlobalVar.name = username;
									new MySQLHelper()
											.executeNonquery("update design_user set fault_time = 0 where username = '"
													+ username + "'");
									dispose();
									new ChooseMenu().setVisible(true);
								} else {
									times++;
									if (times == num) {
										Date tomorrow = new Date();// 取时间
										Calendar calendar = new GregorianCalendar();
										calendar.setTime(tomorrow);
										calendar.add(Calendar.DATE, lock_days);// 把日期往后增加一天.整数往后推,负数往前移动
										// System.out.println(lock_days);
										tomorrow = calendar.getTime(); // 这个时间就是日期往后推一天的结果
										SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
										String dateString = formatter.format(tomorrow);
										// System.out.println("update design_user set fault_time = 0,unlock_time='" +
										// dateString
										// + "' where username = '" + username + "'");
										new MySQLHelper()
												.executeNonquery("update design_user set fault_time = 0,unlock_time='"
														+ dateString + "' where username = '" + username + "'");
										lblAsdsad.setText("错误3次，被锁定至" + dateString);
									} else {
										new MySQLHelper().executeNonquery("update design_user set fault_time = "
												+ String.valueOf(times) + " where username = '" + username + "'");
										lblAsdsad.setText("密码错误，输入错误次数：" + String.valueOf(times));
									}
								}

							}

						}
					}
			}
		});
	}
}
