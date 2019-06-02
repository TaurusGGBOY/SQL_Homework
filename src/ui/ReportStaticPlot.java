package ui;

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
		BotPanel.setBorder(BorderFactory.createTitledBorder("水电信息"));
		cp = new ChartPanel(createChart());
		cp.setBounds(0, 20, 560, 420);
		BotPanel.add(cp);
		this.add(BotPanel);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch (String.valueOf(comboBox.getSelectedItem())) {
				case "用水柱状图":
					cp.setChart(createChart());
					break;
				case "用电3D柱状图":
					cp.setChart(create3DChart());
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
		comboBox.addItem("用水柱状图");
		comboBox.addItem("用电3D柱状图");

		add(comboBox);

		JLabel lblNewLabel = new JLabel("\u56FE\u5F62");
		lblNewLabel.setBounds(10, 13, 54, 15);
		add(lblNewLabel);

	}

	void init() {

	}

	public static CategoryDataset createWaterDataSet() {
		// 实例化DefaultCategoryDataset对象
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		List<Map<String, Object>> infoList3 = new MySQLHelper().query("select * from design_numprice bo1"); // 添加第一季度数据
		for (Map<String, Object> info : infoList3) {
			// System.out.println(Integer.parseInt(String.valueOf(info.get("入库册数"))));
			dataSet.addValue(Integer.parseInt(String.valueOf(info.get("水表示数"))), String.valueOf(info.get("姓名")), "");
		}
		return dataSet;
	}

	public static CategoryDataset createEleDataSet() {
		// 实例化DefaultCategoryDataset对象
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		List<Map<String, Object>> infoList3 = new MySQLHelper().query("select * from design_numprice bo1"); // 添加第一季度数据
		for (Map<String, Object> info : infoList3) {
			dataSet.addValue(Integer.parseInt(String.valueOf(info.get("电表示数"))), String.valueOf(info.get("姓名")), "");
		}
		return dataSet;
	}

	public static JFreeChart createChart() {
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); //
		// 创建主题样式
		standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20)); //
		// 设置标题字体
		standardChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15)); // 设置图例的字体
		standardChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15)); // 设置轴向的字体
		ChartFactory.setChartTheme(standardChartTheme);// 设置主题样式
		// 通过ChartFactory创建JFreeChart
		JFreeChart chart = ChartFactory.createBarChart("水费统计", // 图表标题
				"姓名", // 横轴标题
				"用水示数（吨）", // 纵轴标题
				createWaterDataSet(), // 数据集合
				PlotOrientation.VERTICAL, // 图表方向
				true, // 是否显示图例标识
				false, // 是否显示tooltips
				false);// 是否支持超链接
		// 背景图片
		Image image = null;

		return chart;
	}

	public static JFreeChart create3DChart() {
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); //
		// 创建主题样式
		standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20)); //
		// 设置标题字体
		standardChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15)); // 设置图例的字体
		standardChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15)); // 设置轴向的字体
		ChartFactory.setChartTheme(standardChartTheme);// 设置主题样式
		// 通过ChartFactory创建JFreeChart
		JFreeChart chart = ChartFactory.createBarChart3D("用电统计", // 图表标题
				"姓名", // 横轴标题
				"用电示数（度）", // 纵轴标题
				createEleDataSet(), // 数据集合
				PlotOrientation.VERTICAL, // 图表方向
				true, // 是否显示图例标识
				false, // 是否显示tooltips
				false);// 是否支持超链接
		// 背景图片
		Image image = null;

		chart.getTitle().setFont(new Font("隶书", Font.BOLD, 25)); // 设置标题字体
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12)); // 设置图例类别字体
		chart.setBorderVisible(true); // 设置显示边框
		TextTitle subTitle = new TextTitle("2019年用电信息统计");// 实例化TextTitle对象
		subTitle.setVerticalAlignment(VerticalAlignment.BOTTOM); // 设置居中显示
		chart.addSubtitle(subTitle);// 添加子标题
		CategoryPlot plot = chart.getCategoryPlot(); // 获取绘图区对象
		plot.setForegroundAlpha(0.8F);// 设置绘图区前景色透明度
		plot.setBackgroundAlpha(0.5F);// 设置绘图区背景色透明度
		plot.setBackgroundImage(image);// 设置绘图区背景图片
		CategoryAxis categoryAxis = plot.getDomainAxis();// 获取坐标轴对象
		categoryAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));// 设置坐标轴标题字体
		categoryAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 12));// 设置坐标轴尺值字体
		categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);//
		// 设置坐标轴标题旋转角度
		ValueAxis valueAxis = plot.getRangeAxis();// 设置数据轴对象
		valueAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
		BarRenderer3D renderer = new BarRenderer3D();
		renderer.setItemMargin(0.32); // 设置柱间的间距
		plot.setRenderer(renderer);// 设置图片渲染对象
		return chart;
	}

}
