package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import dal.MySQLHelper;

public class MenuManage extends JFrame {

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
					MenuManage frame = new MenuManage();
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
	public MenuManage() {

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
		List<Map<String, Object>> rightsno = new ArrayList<Map<String, Object>>();
		rightsno = new MySQLHelper().query(
				"SELECT * from usersrights2 ur, users2 u,rights2 r where ur.userID=u.id and r.id=ur.rightID and u.username = '"
						+ GlobalVar.name + "'");

		Map<String, Vector<String>> userRight = new HashMap<String, Vector<String>>();
		for (Map<String, Object> right : rightsno) {
			String module = String.valueOf(right.get("Module"));
			String rightName = String.valueOf(right.get("rightName"));
			if (userRight.containsKey(module)) {
				Vector<String> tempVec = userRight.get(module);
				tempVec.add(rightName);
				userRight.put(module, tempVec);

			} else {
				Vector<String> tempVec = new Vector<>();
				tempVec.add(rightName);
				userRight.put(module, tempVec);
			}
		}

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 200, 600);
		panel.setBackground(new Color(19, 108, 182));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MenuManage.class.getResource("/ui/admin.png")));
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

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ChooseMenu().setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setIcon(new ImageIcon(MenuManage.class.getResource("/ui/quit.png")));
		btnNewButton_1.setBounds(0, 562, 42, 38);
		btnNewButton_1.setContentAreaFilled(false);
		panel.add(btnNewButton_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 163, 180, 390);
		panel.add(scrollPane);

		DefaultMutableTreeNode dmtmRoot = new DefaultMutableTreeNode("用户权限");
		JTree tree = new JTree(dmtmRoot);
		tree.setFont(new Font("宋体", Font.PLAIN, 16));
		for (String str : userRight.keySet()) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(str);
			for (String right : userRight.get(str)) {
				// System.out.println(right);
				DefaultMutableTreeNode rightNode = new DefaultMutableTreeNode(right);
				node.add(rightNode);
			}
			dmtmRoot.add(node);
		}
		TreeNode root = (TreeNode) tree.getModel().getRoot();
		expandAll(tree, new TreePath(root), true);
		scrollPane.setViewportView(tree);

		CardLayout layout = new CardLayout();
		JPanel panel_1 = new JPanel(layout);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(200, 0, 604, 600);
		contentPane.add(panel_1);

		EnterPanel enterPanel = new EnterPanel();
		panel_1.add(enterPanel, "p0");

		BasicInfo1 bInfo1 = new BasicInfo1();
		panel_1.add(bInfo1, "p1");

		BasicInfo2 bInfo2 = new BasicInfo2();
		panel_1.add(bInfo2, "p2");

		BasicInfo3 bInfo3 = new BasicInfo3();
		panel_1.add(bInfo3, "p3");

		BasicInfo4 bInfo4 = new BasicInfo4();
		panel_1.add(bInfo4, "p4");

		ReferInfo1 referInfo1 = new ReferInfo1();
		panel_1.add(referInfo1, "r1");

		ReferInfo2 referInfo2 = new ReferInfo2();
		panel_1.add(referInfo2, "r2");

		ReferInfo3 referInfo3 = new ReferInfo3();
		panel_1.add(referInfo3, "r3");

		KeyInput k1 = new KeyInput();
		panel_1.add(k1, "k1");

		FuzzyQuery q1 = new FuzzyQuery();
		panel_1.add(q1, "q1");
		AlmightyQuery q2 = new AlmightyQuery();
		panel_1.add(q2, "q2");
		CombineQuery q3 = new CombineQuery();
		panel_1.add(q3, "q3");

		ReportStaticPlot rep1 = new ReportStaticPlot();
		panel_1.add(rep1, "rep1");

		ReportStaticTable rep2 = new ReportStaticTable();
		panel_1.add(rep2, "rep2");

		ReportOneSee rep3 = new ReportOneSee();
		panel_1.add(rep3, "rep3");

		layout.show(panel_1, "p0");
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO 自动生成的方法存根
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (node == null) {
					return;
				}

				if (node.isLeaf()) {
					switch (node.toString()) {
					case "编辑室管理1":
						layout.show(panel_1, "p1");
						break;
					case "编辑室管理2":
						layout.show(panel_1, "p2");
						break;
					case "编辑室管理3":
						layout.show(panel_1, "p3");
						break;
					case "编辑室管理4":
						layout.show(panel_1, "p4");
						break;
					case "引用管理1":
						layout.show(panel_1, "r1");
						break;
					case "引用管理2":
						layout.show(panel_1, "r2");
						break;
					case "引用管理3":
						layout.show(panel_1, "r3");
						break;
					case "入库管理1":
						layout.show(panel_1, "k1");
						break;
					case "模糊查询":
						layout.show(panel_1, "q1");
						break;
					case "万能查询":
						layout.show(panel_1, "q2");
						break;
					case "组合查询":
						layout.show(panel_1, "q3");
						break;
					case "入库统计图":
						layout.show(panel_1, "rep1");
						break;
					case "入库统计表":
						layout.show(panel_1, "rep2");
						break;
					case "库存一览":
						layout.show(panel_1, "rep3");
						break;
					default:
						break;
					}
				}
			}
		});
	}

	private static void expandAll(JTree tree, TreePath parent, boolean expand) {
		TreeNode node = (TreeNode) parent.getLastPathComponent();
		if (node.getChildCount() >= 0) {
			for (Enumeration e = node.children(); e.hasMoreElements();) {
				TreeNode n = (TreeNode) e.nextElement();
				TreePath path = parent.pathByAddingChild(n);
				expandAll(tree, path, expand);
			}
		}
		if (expand) {
			tree.expandPath(parent);
		} else {
			tree.collapsePath(parent);
		}

	}
}
