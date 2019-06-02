package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ChooseMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseMenu frame = new ChooseMenu();
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
	public ChooseMenu() {

		setBackground(Color.WHITE);
		setTitle("\u897F\u5357\u4EA4\u901A\u5927\u5B66\u5BBF\u820D\u6C34\u7535\u7BA1\u7406\u7CFB\u7EDF");
		setUndecorated(true);
		// setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenu.class.getResource("/ggb/s7/ui/icon.png")));
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

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ChooseMenu.class.getResource("/ui/admin.png")));
		label.setBounds(10, 42, 40, 40);
		panel.add(label);

		JLabel lblNewLabel = new JLabel(GlobalVar.name);
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(21, 106, 124, 24);
		panel.add(lblNewLabel);

		JLabel label_1 = new JLabel("\u6B22\u8FCE\u4F60");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("宋体", Font.BOLD, 20));
		label_1.setBackground(Color.WHITE);
		label_1.setBounds(82, 60, 63, 24);
		panel.add(label_1);

		JButton btnNewButton = new JButton("\u7528\u6237\u7BA1\u7406");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MainMenu().setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorder(null);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 16));
		btnNewButton.setBounds(0, 150, 200, 30);
		btnNewButton.setBackground(new Color(19, 108, 182));
		btnNewButton.setContentAreaFilled(false);
		panel.add(btnNewButton);

		JButton button = new JButton("\u83DC\u5355\u7BA1\u7406");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MenuManage().setVisible(true);
				dispose();
			}
		});
		button.setFocusPainted(false);
		button.setBorder(null);
		button.setFont(new Font("宋体", Font.BOLD, 16));
		button.setForeground(Color.WHITE);
		button.setBounds(0, 205, 200, 30);
		button.setBackground(new Color(19, 108, 182));
		button.setContentAreaFilled(false);
		panel.add(button);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new LoginMenu().setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setIcon(new ImageIcon(ChooseMenu.class.getResource("/ui/quit.png")));
		btnNewButton_1.setBounds(0, 562, 42, 38);
		btnNewButton_1.setContentAreaFilled(false);
		panel.add(btnNewButton_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(200, 0, 604, 600);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel label_2 = new JLabel("\u6B22\u8FCE\u8FDB\u5165");
		label_2.setFont(new Font("宋体", Font.BOLD, 30));
		label_2.setBounds(194, 37, 128, 35);
		panel_1.add(label_2);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ChooseMenu.class.getResource("/ui/\u6821\u5FBD.png")));
		lblNewLabel_1.setBounds(151, 264, 250, 250);
		panel_1.add(lblNewLabel_1);

		JLabel label_3 = new JLabel("\u897F\u5357\u4EA4\u901A\u5927\u5B66\u6C34\u7535\u7BA1\u7406\u7CFB\u7EDF");
		label_3.setFont(new Font("宋体", Font.BOLD, 30));
		label_3.setBounds(65, 141, 448, 35);
		panel_1.add(label_3);
	}
}
