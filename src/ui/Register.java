package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dal.MySQLHelper;;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 340, 510);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setExtendedState(JFrame.ICONIFIED); // 最小化
			}
		});

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 6, 20, 20);
		ImageIcon image1 = new ImageIcon(Register.class.getResource("/ui/icon_small.png"));
		image1.setImage(image1.getImage().getScaledInstance(lblNewLabel_1.getWidth(), lblNewLabel_1.getHeight(),
				Image.SCALE_DEFAULT));
		lblNewLabel_1.setIcon(new ImageIcon(Register.class.getResource("/ui/icon_20.png")));

		contentPane.add(lblNewLabel_1);

		JLabel label_1 = new JLabel("\u6CE8\u518C\u8D26\u53F7");
		label_1.setForeground(Color.GRAY);
		label_1.setBounds(36, 6, 53, 20);
		contentPane.add(label_1);

		JLabel label_4 = new JLabel("\u7ACB\u5373\u6CE8\u518C");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("宋体", Font.BOLD, 20));
		label_4.setBounds(120, 369, 104, 31);
		contentPane.add(label_4);

		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField.getText();
				String password = textField_1.getText();
				if (id.isEmpty())
					JOptionPane.showMessageDialog(null, "用户名不能为空", "错误", JOptionPane.PLAIN_MESSAGE);
				else {
					// Query
					if (!password.equals(textField_2.getText())) {
						JOptionPane.showMessageDialog(null, "两次输入的密码不同", "错误", JOptionPane.PLAIN_MESSAGE);
						return;
					}
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					list = new MySQLHelper().query("select * from users2 where username = '" + id + "'");
					if (list.isEmpty()) {
						new MySQLHelper().executeNonquery(
								"Insert into users2 (username,password) values ('" + id + "','" + password + "')");
						JOptionPane.showMessageDialog(null, "注册成功", "成功", JOptionPane.PLAIN_MESSAGE);
						new LoginMenu().setVisible(true);
						dispose();
					} else

						JOptionPane.showMessageDialog(null, "该用户已经存在", "错误", JOptionPane.PLAIN_MESSAGE);

				}

			}
		});
		button_2.setIcon(new ImageIcon(Register.class.getResource("/ui/\u6CE8\u518C\u6309\u94AE.png")));
		button_2.setContentAreaFilled(false);
		button_2.setBorder(null);
		button_2.setBounds(20, 360, 300, 45);
		contentPane.add(button_2);
		btnNewButton.setIcon(new ImageIcon(Register.class.getResource("/ui/-_grey.png")));
		btnNewButton.setBounds(290, 10, 20, 20);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorder(null);
		contentPane.add(btnNewButton);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginMenu().setVisible(true);
				dispose();
			}
		});
		button.setIcon(new ImageIcon(Register.class.getResource("/ui/x_grey.png")));
		button.setBounds(310, 10, 20, 20);
		button.setContentAreaFilled(false);
		button.setBorder(null);
		contentPane.add(button);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(
				new ImageIcon(Register.class.getResource("/ui/\u7F16\u8F91\u8D44\u6599\u6807\u9898\u680F.png")));
		lblNewLabel.setBounds(0, 0, 340, 33);
		contentPane.add(lblNewLabel);

		JLabel label = new JLabel("\u8D26\u53F7");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setForeground(Color.GRAY);
		label.setBounds(10, 161, 50, 40);
		contentPane.add(label);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(60, 161, 250, 40);
		contentPane.add(textField);

		JLabel label_2 = new JLabel("\u5BC6\u7801");
		label_2.setForeground(Color.GRAY);
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));
		label_2.setBounds(10, 222, 50, 40);
		contentPane.add(label_2);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(60, 222, 250, 40);
		contentPane.add(textField_1);

		JLabel lblNewLabel_2 = new JLabel("\u6B22\u8FCE\u6CE8\u518C");
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 34));
		lblNewLabel_2.setBounds(10, 59, 320, 40);
		contentPane.add(lblNewLabel_2);

		JLabel label_5 = new JLabel("\u6BCF\u4E00\u5929\uFF0C\u6C9F\u901A\uFF0C\u4E50\u54C9");
		label_5.setFont(new Font("宋体", Font.PLAIN, 30));
		label_5.setBounds(10, 100, 320, 40);
		contentPane.add(label_5);

		JLabel label_3 = new JLabel("\u786E\u8BA4");
		label_3.setForeground(Color.GRAY);
		label_3.setFont(new Font("宋体", Font.PLAIN, 18));
		label_3.setBounds(10, 280, 50, 40);
		contentPane.add(label_3);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(60, 280, 250, 40);
		contentPane.add(textField_2);
		ImageIcon image = new ImageIcon(Register.class.getResource("/ui/\u7F16\u8F91\u8D44\u6599\u4E0B\u9762.png"));
		image.setImage(image.getImage().getScaledInstance(JLabel.WIDTH, JLabel.HEIGHT, Image.SCALE_DEFAULT));
	}
}
