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
		BotPanel.setBorder(BorderFactory.createTitledBorder("入库情况"));
		cp = new ChartPanel(createLineChart());
		cp.setBounds(0, 20, 560, 420);
		BotPanel.add(cp);
		this.add(BotPanel);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch (String.valueOf(comboBox.getSelectedItem())) {
				case "柱状图":
					cp.setChart(createChart());
					break;

				case "饼图":
					cp.setChart(createPanChart());
					break;
				// case "折线图":
				// cp.setChart(createLineChart());
				// break;
				case "3D柱状图":
					cp.setChart(create3DChart());
					break;

				case "3D饼图":
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
		comboBox.addItem("柱状图");
		comboBox.addItem("饼图");
		comboBox.addItem("3D柱状图");
		comboBox.addItem("3D饼图");

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
		// 实例化DefaultCategoryDataset对象
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		List<Map<String, Object>> infoList3 = new MySQLHelper()
				.query("select * from bookonesee1 bo1, bookin1 bi1 where bo1.书号=bi1.书号"); // 添加第一季度数据
		for (Map<String, Object> info : infoList3) {
			// System.out.println(Integer.parseInt(String.valueOf(info.get("入库册数"))));
			dataSet.addValue(Integer.parseInt(String.valueOf(info.get("入库册数"))), String.valueOf(info.get("书名")), "");
		}
		return dataSet;
	}

	public static CategoryDataset createXYDataSet() {
		// 实例化DefaultCategoryDataset对象
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

		List<Map<String, Object>> infoList3 = new MySQLHelper()
				.query("select * from bookonesee1 bo1, bookin1 bi1 where bo1.书号=bi1.书号  and bo1.书名='工科化学'"); // 添加第一季度数据
		for (Map<String, Object> info : infoList3) {
			dataSet.addValue(Integer.parseInt(String.valueOf(info.get("入库册数"))), String.valueOf(info.get("入库日期")),
					String.valueOf(info.get("书名")));
		}
		return dataSet;
	}

	public static PieDataset createPieDataSet() {
		// 实例化DefaultCategoryDataset对象
		DefaultPieDataset dataSet = new DefaultPieDataset();
		List<Map<String, Object>> infoList4 = new MySQLHelper()
				.query("select * from bookonesee1 bo1, bookin1 bi1 where bo1.书号=bi1.书号"); // 添加第一季度数据
		for (Map<String, Object> info : infoList4) {
			dataSet.setValue(String.valueOf(info.get("书名")), Integer.parseInt(String.valueOf(info.get("入库册数"))));
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
		JFreeChart chart = ChartFactory.createBarChart("图书入库统计", // 图表标题
				"图书", // 横轴标题
				"入库（本）", // 纵轴标题
				createDataSet(), // 数据集合
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
		JFreeChart chart = ChartFactory.createBarChart3D("图书入库统计", // 图表标题
				"图书", // 横轴标题
				"入库（本）", // 纵轴标题
				createDataSet(), // 数据集合
				PlotOrientation.VERTICAL, // 图表方向
				true, // 是否显示图例标识
				false, // 是否显示tooltips
				false);// 是否支持超链接
		// 背景图片
		Image image = null;

		chart.getTitle().setFont(new Font("隶书", Font.BOLD, 25)); // 设置标题字体
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12)); // 设置图例类别字体
		chart.setBorderVisible(true); // 设置显示边框
		TextTitle subTitle = new TextTitle("2017年图书入库统计");// 实例化TextTitle对象
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

	public static JFreeChart createPanChart() {
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); // 创建主题样式
		standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20)); // 设置标题字体
		standardChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15)); // 设置图例的字体
		standardChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15)); // 设置轴向的字体
		ChartFactory.setChartTheme(standardChartTheme);// 设置主题样式
		// 通过ChartFactory创建JFreeChart
		JFreeChart chart = ChartFactory.createPieChart("图书入库统计", // 图表标题
				createPieDataSet(), // 数据集合
				true, // 是否显示图例标识
				false, // 是否显示tooltips
				false);// 是否支持超链接
		// 背景图片
		Image image = null;

		// chart.getTitle().setFont(new Font("隶书", Font.BOLD, 25)); // 设置标题字体
		// chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12)); // 设置图例类别字体
		// chart.setBorderVisible(true); // 设置显示边框
		// TextTitle subTitle = new TextTitle("2017年图书入库统计");// 实例化TextTitle对象
		// subTitle.setVerticalAlignment(VerticalAlignment.BOTTOM); // 设置居中显示
		// chart.addSubtitle(subTitle);// 添加子标题
		// CategoryPlot plot = chart.getCategoryPlot(); // 获取绘图区对象
		// plot.setForegroundAlpha(0.8F);// 设置绘图区前景色透明度
		// plot.setBackgroundAlpha(0.5F);// 设置绘图区背景色透明度
		// plot.setBackgroundImage(image);// 设置绘图区背景图片
		// CategoryAxis categoryAxis = plot.getDomainAxis();// 获取坐标轴对象
		// categoryAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));// 设置坐标轴标题字体
		// categoryAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 12));// 设置坐标轴尺值字体
		// categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);//
		// 设置坐标轴标题旋转角度
		// ValueAxis valueAxis = plot.getRangeAxis();// 设置数据轴对象
		// valueAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
		// BarRenderer3D renderer = new BarRenderer3D();
		// renderer.setItemMargin(0.32); // 设置柱间的间距
		// plot.setRenderer(renderer);// 设置图片渲染对象
		return chart;
	}

	public static JFreeChart createPan3DChart() {
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); // 创建主题样式
		standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20)); // 设置标题字体
		standardChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15)); // 设置图例的字体
		standardChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15)); // 设置轴向的字体
		ChartFactory.setChartTheme(standardChartTheme);// 设置主题样式
		// 通过ChartFactory创建JFreeChart
		JFreeChart chart = ChartFactory.createPieChart3D("图书入库统计", // 图表标题
				createPieDataSet(), // 数据集合
				true, // 是否显示图例标识
				false, // 是否显示tooltips
				false);// 是否支持超链接
		// 背景图片
		Image image = null;

		// chart.getTitle().setFont(new Font("隶书", Font.BOLD, 25)); // 设置标题字体
		// chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12)); // 设置图例类别字体
		// chart.setBorderVisible(true); // 设置显示边框
		// TextTitle subTitle = new TextTitle("2017年图书入库统计");// 实例化TextTitle对象
		// subTitle.setVerticalAlignment(VerticalAlignment.BOTTOM); // 设置居中显示
		// chart.addSubtitle(subTitle);// 添加子标题
		// CategoryPlot plot = chart.getCategoryPlot(); // 获取绘图区对象
		// plot.setForegroundAlpha(0.8F);// 设置绘图区前景色透明度
		// plot.setBackgroundAlpha(0.5F);// 设置绘图区背景色透明度
		// plot.setBackgroundImage(image);// 设置绘图区背景图片
		// CategoryAxis categoryAxis = plot.getDomainAxis();// 获取坐标轴对象
		// categoryAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));// 设置坐标轴标题字体
		// categoryAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 12));// 设置坐标轴尺值字体
		// categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);//
		// 设置坐标轴标题旋转角度
		// ValueAxis valueAxis = plot.getRangeAxis();// 设置数据轴对象
		// valueAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
		// BarRenderer3D renderer = new BarRenderer3D();
		// renderer.setItemMargin(0.32); // 设置柱间的间距
		// plot.setRenderer(renderer);// 设置图片渲染对象
		return chart;
	}

	public static JFreeChart createLineChart() {
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); // 创建主题样式
		standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20)); // 设置标题字体
		standardChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15)); // 设置图例的字体
		standardChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15)); // 设置轴向的字体
		ChartFactory.setChartTheme(standardChartTheme);// 设置主题样式
		// 通过ChartFactory创建JFreeChart
		JFreeChart chart = ChartFactory.createLineChart3D("图书入库统计", // 图表标题
				"书名", "入库（本）", createXYDataSet(), PlotOrientation.VERTICAL, // 数据集合
				true, // 是否显示图例标识
				false, // 是否显示tooltips
				false);// 是否支持超链接
		// 背景图片
		Image image = null;

		return chart;
	}

	public static JFreeChart createTestChart() {
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		mChartTheme.setLargeFont(new Font("黑体", Font.BOLD, 20));
		mChartTheme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 15));
		mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));
		ChartFactory.setChartTheme(mChartTheme);
		CategoryDataset mDataset = GetDataset();
		JFreeChart mChart = ChartFactory.createLineChart("折线图", // 图名字
				"年份", // 横坐标
				"数量", // 纵坐标
				mDataset, // 数据集
				PlotOrientation.VERTICAL, true, // 显示图例
				true, // 采用标准生成器
				false);// 是否生成超链接

		CategoryPlot mPlot = (CategoryPlot) mChart.getPlot();
		mPlot.setBackgroundPaint(Color.LIGHT_GRAY);
		mPlot.setRangeGridlinePaint(Color.BLUE);// 背景底部横虚线
		mPlot.setOutlinePaint(Color.RED);// 边界线
		return mChart;
	}

}
