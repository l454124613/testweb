package sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class connectsql {

	private DBHelper db1 = null;
	private ResultSet ret = null;

	// public static void main(String[] args) {
	// // String sql =
	// // "SELECT *  from bguserdb.UserBase WHERE uid='M13813813888'";
	// // List<Map> l = new Demo().select(sql);
	// // System.out.println(l.get(0).toString());
	// String sql =
	// "UPDATE bguserdb.UserBase set levelId='11' where  uid='M13813813888'";
	// int ii = new connectsql().update(sql);
	// System.out.println(ii);
	//
	// }

	public List<Map> select(String sql) {
		List list = new ArrayList();
		try {
			db1 = new DBHelper(sql);// 创建DBHelper对象
			ret = db1.pst.executeQuery();// 执行语句，得到结果集
			java.sql.ResultSetMetaData md = ret.getMetaData(); // 得到结果集(rs)的结构信息，比如字段数、字段名等
			int columnCount = md.getColumnCount(); // 返回此 ResultSet 对象中的列数
			// Map rowData = new HashMap();

			while (ret.next()) {
				Map rowData = new HashMap(columnCount);
				for (int i = 1; i <= columnCount; i++) {
					rowData.put(md.getColumnName(i), ret.getObject(i));
					// System.out.println(md.getColumnName(i) + ":"
					// + ret.getObject(i));
				}
				list.add(rowData);
			}// 显示数据
			ret.close();
			db1.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int update(String sql) {
		int res = 0;
		try {
			db1 = new DBHelper(sql);// 创建DBHelper对象
			res = db1.pst.executeUpdate();// 执行语句，得到结果集

			db1.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}
