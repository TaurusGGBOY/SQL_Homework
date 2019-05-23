package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.VerticalAlignment;

import dal.MySQLHelper;

public class ReportStaticPlot extends JPanel {
	private HashSet<String> nameSet = new HashSet<>();
	DefaultTableModel model1;
	DefaultTableModel model2;
	List<Map<String, Object>> infoList1 = new ArrayList<Map<String, Object>>();
	List<Map<String, Object>> infoList2 = new ArrayList<Map<String, Object>>();

	Vector<Vector<String>> table1Columns = new Vector<>();
	Vector<Vector<String>> table2Columns = new Vector<>();
	Vector<String> columns1 = new Vector<>();
	Vector<String> columns2 = new Vector<>();
	JComboBox<String> comboBox = new JComboBox<>();

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	int chooseRow;
	ChartPanel cp;

	/**
	 * Create the panel.
	 */
	public ReportStaticPlot() {
		setLayout(null);

		JPanel BotPanel = new JPanel();
		BotPanel.setBounds(10, 95, 560, 440);
		BotPanel.setLayout(null);
		BotPanel.setBorder(BorderFactory.createTitledBorder("������"));
		cp = new ChartPanel(createLineChart());
		cp.setBounds(0, 20, 560, 420);
		BotPanel.add(cp);
		this.add(BotPanel);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch (String.valueOf(comboBox.getSelectedItem())) {
				case "��״ͼ":
					cp.setChart(createChart());
					break;

				case "��ͼ":
					cp.setChart(createPanChart());
					break;
				// case "����ͼ":
				// cp.setChart(createLineChart());
				// break;
				case "3D��״ͼ":
					cp.setChart(create3DChart());
					break;

				case "3D��ͼ":
					cp.setChart(createPan3DChart());
					break;
				default:
					break;
				}
				// System.out.println("jinlai");
				// revalidate();
				// updateUI();
				// repaint();
			}
		});

		comboBox.setBounds(73, 10, 127, 21);
		comboBox.addItem("��״ͼ");
		comboBox.addItem("��ͼ");
		comboBox.addItem("3D��״ͼ");
		comboBox.addItem("3D��ͼ");

		add(comboBox);

		JLabel lblNewLabel = new JLabel("\u56FE\u5F62");
		lblNewLabel.setBounds(10, 13, 54, 15);
		add(lblNewLabel);

	}

	void init() {

	}

	public static CategoryDataset GetDataset() {
		DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
		mDataset.addValue(1, "First", "2013");
		mDataset.addValue(3, "First", "2014");
		mDataset.addValue(2, "First", "2015");
		mDataset.addValue(6, "First", "2016");
		mDataset.addValue(5, "First", "2017");
		mDataset.addValue(12, "First", "2018");
		mDataset.addValue(14, "Second", "2013");
		mDataset.addValue(13, "Second", "2014");
		mDataset.addValue(12, "Second", "2015");
		mDataset.addValue(9, "Second", "2016");
		mDataset.addValue(5, "Second", "2017");
		mDataset.addValue(7, "Second", "2018");
		return mDataset;
	}

	public static CategoryDataset createDataSet() {
		// ʵ����DefaultCategoryDataset����
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		List<Map<String, Object>> infoList3 = new MySQLHelper()
				.query("select * from bookonesee1 bo1, bookin1 bi1 where bo1.���=bi1.���"); // ��ӵ�һ��������
		for (Map<String, Object> info : infoList3) {
			// System.out.println(Integer.parseInt(String.valueOf(info.get("������"))));
			dataSet.addValue(Integer.parseInt(String.valueOf(info.get("������"))), String.valueOf(info.get("����")), "");
		}
		return dataSet;
	}

	public static CategoryDataset createXYDataSet() {
		// ʵ����DefaultCategoryDataset����
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		List<Map<String, Object>> infoList3 = new MySQLHelper()
				.query("select * from bookonesee1 bo1, bookin1 bi1 where bo1.���=bi1.���  and bo1.����='���ƻ�ѧ'"); // ��ӵ�һ��������
		for (Map<String, Object> info : infoList3) {
			dataSet.addValue(Integer.parseInt(String.valueOf(info.get("������"))), String.valueOf(info.get("�������")),
					String.valueOf(info.get("����")));
		}
		return dataSet;
	}

	public static PieDataset createPieDataSet() {
		// ʵ����DefaultCategoryDataset����
		DefaultPieDataset dataSet = new DefaultPieDataset();
		List<Map<String, Object>> infoList4 = new MySQLHelper()
				.query("select * from bookonesee1 bo1, bookin1 bi1 where bo1.���=bi1.���"); // ��ӵ�һ��������
		for (Map<String, Object> info : infoList4) {
			dataSet.setValue(String.valueOf(info.get("����")), Integer.parseInt(String.valueOf(info.get("������"))));
		}
		return dataSet;
	}

	public static JFreeChart createChart() {
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); //
		// ����������ʽ
		standardChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20)); //
		// ���ñ�������
		standardChartTheme.setRegularFont(new Font("����", Font.PLAIN, 15)); // ����ͼ��������
		standardChartTheme.setLargeFont(new Font("����", Font.PLAIN, 15)); // �������������
		ChartFactory.setChartTheme(standardChartTheme);// ����������ʽ
		// ͨ��ChartFactory����JFreeChart
		JFreeChart chart = ChartFactory.createBarChart("ͼ�����ͳ��", // ͼ�����
				"ͼ��", // �������
				"��⣨����", // �������
				createDataSet(), // ���ݼ���
				PlotOrientation.VERTICAL, // ͼ����
				true, // �Ƿ���ʾͼ����ʶ
				false, // �Ƿ���ʾtooltips
				false);// �Ƿ�֧�ֳ�����
		// ����ͼƬ
		Image image = null;

		return chart;
	}

	public static JFreeChart create3DChart() {
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); //
		// ����������ʽ
		standardChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20)); //
		// ���ñ�������
		standardChartTheme.setRegularFont(new Font("����", Font.PLAIN, 15)); // ����ͼ��������
		standardChartTheme.setLargeFont(new Font("����", Font.PLAIN, 15)); // �������������
		ChartFactory.setChartTheme(standardChartTheme);// ����������ʽ
		// ͨ��ChartFactory����JFreeChart
		JFreeChart chart = ChartFactory.createBarChart3D("ͼ�����ͳ��", // ͼ�����
				"ͼ��", // �������
				"��⣨����", // �������
				createDataSet(), // ���ݼ���
				PlotOrientation.VERTICAL, // ͼ����
				true, // �Ƿ���ʾͼ����ʶ
				false, // �Ƿ���ʾtooltips
				false);// �Ƿ�֧�ֳ�����
		// ����ͼƬ
		Image image = null;

		chart.getTitle().setFont(new Font("����", Font.BOLD, 25)); // ���ñ�������
		chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12)); // ����ͼ���������
		chart.setBorderVisible(true); // ������ʾ�߿�
		TextTitle subTitle = new TextTitle("2017��ͼ�����ͳ��");// ʵ����TextTitle����
		subTitle.setVerticalAlignment(VerticalAlignment.BOTTOM); // ���þ�����ʾ
		chart.addSubtitle(subTitle);// ����ӱ���
		CategoryPlot plot = chart.getCategoryPlot(); // ��ȡ��ͼ������
		plot.setForegroundAlpha(0.8F);// ���û�ͼ��ǰ��ɫ͸����
		plot.setBackgroundAlpha(0.5F);// ���û�ͼ������ɫ͸����
		plot.setBackgroundImage(image);// ���û�ͼ������ͼƬ
		CategoryAxis categoryAxis = plot.getDomainAxis();// ��ȡ���������
		categoryAxis.setLabelFont(new Font("����", Font.PLAIN, 12));// �����������������
		categoryAxis.setTickLabelFont(new Font("����", Font.PLAIN, 12));// �����������ֵ����
		categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);//
		// ���������������ת�Ƕ�
		ValueAxis valueAxis = plot.getRangeAxis();// �������������
		valueAxis.setLabelFont(new Font("����", Font.PLAIN, 12));
		BarRenderer3D renderer = new BarRenderer3D();
		renderer.setItemMargin(0.32); // ��������ļ��
		plot.setRenderer(renderer);// ����ͼƬ��Ⱦ����
		return chart;
	}

	public static JFreeChart createPanChart() {
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); // ����������ʽ
		standardChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20)); // ���ñ�������
		standardChartTheme.setRegularFont(new Font("����", Font.PLAIN, 15)); // ����ͼ��������
		standardChartTheme.setLargeFont(new Font("����", Font.PLAIN, 15)); // �������������
		ChartFactory.setChartTheme(standardChartTheme);// ����������ʽ
		// ͨ��ChartFactory����JFreeChart
		JFreeChart chart = ChartFactory.createPieChart("ͼ�����ͳ��", // ͼ�����
				createPieDataSet(), // ���ݼ���
				true, // �Ƿ���ʾͼ����ʶ
				false, // �Ƿ���ʾtooltips
				false);// �Ƿ�֧�ֳ�����
		// ����ͼƬ
		Image image = null;

		// chart.getTitle().setFont(new Font("����", Font.BOLD, 25)); // ���ñ�������
		// chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12)); // ����ͼ���������
		// chart.setBorderVisible(true); // ������ʾ�߿�
		// TextTitle subTitle = new TextTitle("2017��ͼ�����ͳ��");// ʵ����TextTitle����
		// subTitle.setVerticalAlignment(VerticalAlignment.BOTTOM); // ���þ�����ʾ
		// chart.addSubtitle(subTitle);// ����ӱ���
		// CategoryPlot plot = chart.getCategoryPlot(); // ��ȡ��ͼ������
		// plot.setForegroundAlpha(0.8F);// ���û�ͼ��ǰ��ɫ͸����
		// plot.setBackgroundAlpha(0.5F);// ���û�ͼ������ɫ͸����
		// plot.setBackgroundImage(image);// ���û�ͼ������ͼƬ
		// CategoryAxis categoryAxis = plot.getDomainAxis();// ��ȡ���������
		// categoryAxis.setLabelFont(new Font("����", Font.PLAIN, 12));// �����������������
		// categoryAxis.setTickLabelFont(new Font("����", Font.PLAIN, 12));// �����������ֵ����
		// categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);//
		// ���������������ת�Ƕ�
		// ValueAxis valueAxis = plot.getRangeAxis();// �������������
		// valueAxis.setLabelFont(new Font("����", Font.PLAIN, 12));
		// BarRenderer3D renderer = new BarRenderer3D();
		// renderer.setItemMargin(0.32); // ��������ļ��
		// plot.setRenderer(renderer);// ����ͼƬ��Ⱦ����
		return chart;
	}

	public static JFreeChart createPan3DChart() {
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); // ����������ʽ
		standardChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20)); // ���ñ�������
		standardChartTheme.setRegularFont(new Font("����", Font.PLAIN, 15)); // ����ͼ��������
		standardChartTheme.setLargeFont(new Font("����", Font.PLAIN, 15)); // �������������
		ChartFactory.setChartTheme(standardChartTheme);// ����������ʽ
		// ͨ��ChartFactory����JFreeChart
		JFreeChart chart = ChartFactory.createPieChart3D("ͼ�����ͳ��", // ͼ�����
				createPieDataSet(), // ���ݼ���
				true, // �Ƿ���ʾͼ����ʶ
				false, // �Ƿ���ʾtooltips
				false);// �Ƿ�֧�ֳ�����
		// ����ͼƬ
		Image image = null;

		// chart.getTitle().setFont(new Font("����", Font.BOLD, 25)); // ���ñ�������
		// chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12)); // ����ͼ���������
		// chart.setBorderVisible(true); // ������ʾ�߿�
		// TextTitle subTitle = new TextTitle("2017��ͼ�����ͳ��");// ʵ����TextTitle����
		// subTitle.setVerticalAlignment(VerticalAlignment.BOTTOM); // ���þ�����ʾ
		// chart.addSubtitle(subTitle);// ����ӱ���
		// CategoryPlot plot = chart.getCategoryPlot(); // ��ȡ��ͼ������
		// plot.setForegroundAlpha(0.8F);// ���û�ͼ��ǰ��ɫ͸����
		// plot.setBackgroundAlpha(0.5F);// ���û�ͼ������ɫ͸����
		// plot.setBackgroundImage(image);// ���û�ͼ������ͼƬ
		// CategoryAxis categoryAxis = plot.getDomainAxis();// ��ȡ���������
		// categoryAxis.setLabelFont(new Font("����", Font.PLAIN, 12));// �����������������
		// categoryAxis.setTickLabelFont(new Font("����", Font.PLAIN, 12));// �����������ֵ����
		// categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);//
		// ���������������ת�Ƕ�
		// ValueAxis valueAxis = plot.getRangeAxis();// �������������
		// valueAxis.setLabelFont(new Font("����", Font.PLAIN, 12));
		// BarRenderer3D renderer = new BarRenderer3D();
		// renderer.setItemMargin(0.32); // ��������ļ��
		// plot.setRenderer(renderer);// ����ͼƬ��Ⱦ����
		return chart;
	}

	public static JFreeChart createLineChart() {
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); // ����������ʽ
		standardChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20)); // ���ñ�������
		standardChartTheme.setRegularFont(new Font("����", Font.PLAIN, 15)); // ����ͼ��������
		standardChartTheme.setLargeFont(new Font("����", Font.PLAIN, 15)); // �������������
		ChartFactory.setChartTheme(standardChartTheme);// ����������ʽ
		// ͨ��ChartFactory����JFreeChart
		JFreeChart chart = ChartFactory.createLineChart3D("ͼ�����ͳ��", // ͼ�����
				"����", "��⣨����", createXYDataSet(), PlotOrientation.VERTICAL, // ���ݼ���
				true, // �Ƿ���ʾͼ����ʶ
				false, // �Ƿ���ʾtooltips
				false);// �Ƿ�֧�ֳ�����
		// ����ͼƬ
		Image image = null;

		return chart;
	}

	public static JFreeChart createTestChart() {
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		mChartTheme.setLargeFont(new Font("����", Font.BOLD, 20));
		mChartTheme.setExtraLargeFont(new Font("����", Font.PLAIN, 15));
		mChartTheme.setRegularFont(new Font("����", Font.PLAIN, 15));
		ChartFactory.setChartTheme(mChartTheme);
		CategoryDataset mDataset = GetDataset();
		JFreeChart mChart = ChartFactory.createLineChart("����ͼ", // ͼ����
				"���", // ������
				"����", // ������
				mDataset, // ���ݼ�
				PlotOrientation.VERTICAL, true, // ��ʾͼ��
				true, // ���ñ�׼������
				false);// �Ƿ����ɳ�����

		CategoryPlot mPlot = (CategoryPlot) mChart.getPlot();
		mPlot.setBackgroundPaint(Color.LIGHT_GRAY);
		mPlot.setRangeGridlinePaint(Color.BLUE);// �����ײ�������
		mPlot.setOutlinePaint(Color.RED);// �߽���
		return mChart;
	}

}
