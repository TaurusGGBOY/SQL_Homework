package dal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 * 
 * @项目名称：JavaSQL1
 * @类名称：MySQLHelper
 * @类描述：mysql操作类
 * @创建人：奔跑的鸡丝 @创建时间：2014-11-25 下午8:58:34 @修改备注： @版本：
 */
public class MySQLHelper {
	private static final String PROPERTIES_NAME = "D:\\eclipse\\workspace\\SQL20190320\\src\\dal\\config.properties";
	public static String url = "jdbc:mysql://127.0.0.1//sqlclass"; // 数据库连接
	public static String name = "com.mysql.cj.jdbc.Driver"; // 程序驱动
	public static String user = "root"; // 用户名
	public static String password = "123456"; // 密码
	public static String recordpassword = "";
	public static String autologin = "";
	public static String auto_id = "";
	public static String auto_password = "";
	public static Connection connection = null; // 数据库连接
	public static PreparedStatement preparedStatement = null; // 待查询语句描述对象
	public static boolean isOpen = false;

	/**
	 * 
	 * 创建一个新的实例 DBHelper.
	 * 
	 * @param sql
	 *            : SQL查询语句
	 */
	public MySQLHelper() {
		try {
			Properties properties = new Properties();
			properties.load(new InputStreamReader(new FileInputStream(PROPERTIES_NAME), "UTF-8"));
			url = "jdbc:mysql://" + properties.getProperty("host")
					+ "?characterEncoding=GBK&useSSL=false&serverTimezone=Hongkong&allowPublicKeyRetrieval=true";
			user = properties.getProperty("user");
			password = properties.getProperty("password");
			recordpassword = properties.getProperty("recordpassword");
			auto_id = properties.getProperty("auto_id");
			auto_password = properties.getProperty("auto_password");
			autologin = properties.getProperty("autologin");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void connection() {
		try {
			if (!isOpen) {
				Class.forName(name);// 指定连接类型
				connection = DriverManager.getConnection(url, user, password);// 获取连接
				isOpen = true;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * 
	 * @方法名称: close ； @方法描述: 关闭数据库 ； @参数 ： @返回类型: void ； @创建人：奔跑的鸡丝
	 *        ; @创建时间：2014-11-25 下午8:58:14； @throws
	 */
	public void close() {

		try {
			if (connection != null)
				if (!connection.isClosed()) {
					connection.close();
					preparedStatement.close();
					connection = null;
					isOpen = false;
				}
		} catch (SQLException e) {
			System.out.println("关闭数据库出现问题！！");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @方法名称: query ； @方法描述: 查询操作 ； @参数 ：@param sql：查询操作语句 ； @返回类型: ResultSet
	 *        :查询结果数据集； @创建人：奔跑的鸡丝 ; @创建时间：2014-11-25 下午8:49:25； @throws
	 */
	public List<Map<String, Object>> query(String sql) {
		connection();
		ResultSet resultSet = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY); // 准备执行语句
			resultSet = preparedStatement.executeQuery();

			// return the description of this ResultSet object's columns
			ResultSetMetaData rsMetaData = resultSet.getMetaData();
			// return the number of columns
			int columnCount = rsMetaData.getColumnCount();
			String columnName = "";
			while (resultSet.next()) {
				Map<String, Object> rowData = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					// return 第i列的column name
					columnName = rsMetaData.getColumnLabel(i);
					rowData.put(columnName, resultSet.getObject(i));
				}
				list.add(rowData);
			}

		} catch (Exception e) {
			System.out.println("查询错误，请检查！！");
			e.printStackTrace();
		}
		close();
		return list;
	}

	/**
	 * 
	 * @方法名称: executeNonquery ； @方法描述: 插入、修改、删除等操作 ； @参数 ：@param sql：插入语句 @返回类型:
	 *        boolean ； @创建人：奔跑的鸡丝； @创建时间：2014-11-25 下午8:45:49； @throws
	 */
	public boolean executeNonquery(String sql) {
		connection();
		boolean flag = false;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;

		} catch (Exception e) {
			System.out.println("插入数据库时出现错误！！");
			e.printStackTrace();
		}
		close();
		return flag;
	}

	/**
	 * 
	 * @方法名称: getCount ； @方法描述: 获取表记录数 ； @参数 ：@param sql @参数 ：@return @返回类型: int
	 *        记录数； @创建人：奔跑的鸡丝 ; @创建时间：2014-11-26 下午2:40:37； @throws
	 */
	public int getCount(String sql) {
		connection();
		int count = 0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.last();
			count = resultSet.getRow();
			resultSet.close();

		} catch (Exception e) {
			System.out.println("查询总记录数失败！！");
			e.printStackTrace();
		}
		close();
		return count;
	}

	public void setProperty(String string, String value) {
		Properties properties = new Properties();
		try {
			properties.load(new InputStreamReader(new FileInputStream(PROPERTIES_NAME), "UTF-8"));
			OutputStream fos = new FileOutputStream(PROPERTIES_NAME);
			properties.setProperty(string, value);
			properties.store(fos, "Update value");
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getProperty(String string) {
		Properties properties = new Properties();
		try {
			properties.load(new InputStreamReader(new FileInputStream(PROPERTIES_NAME), "UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String rsString = properties.getProperty(string);
		return rsString;
	}

	public void FitTableColumns(JTable myTable) {
		JTableHeader header = myTable.getTableHeader();
		int rowCount = myTable.getRowCount();

		Enumeration columns = myTable.getColumnModel().getColumns();
		while (columns.hasMoreElements()) {
			TableColumn column = (TableColumn) columns.nextElement();
			int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
			int width = (int) myTable.getTableHeader().getDefaultRenderer()
					.getTableCellRendererComponent(myTable, column.getIdentifier(), false, false, -1, col)
					.getPreferredSize().getWidth();
			for (int row = 0; row < rowCount; row++) {
				int preferedWidth = (int) myTable.getCellRenderer(row, col)
						.getTableCellRendererComponent(myTable, myTable.getValueAt(row, col), false, false, row, col)
						.getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth);
			}
			header.setResizingColumn(column); // 此行很重要
			column.setWidth(width + myTable.getIntercellSpacing().width);
		}
	}
}