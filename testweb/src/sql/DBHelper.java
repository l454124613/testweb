package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mainDo.getProperties;

public class DBHelper {

	Connection conn = null;
	PreparedStatement pst = null;
	getProperties gp = new getProperties();

	public DBHelper(String sql) {
		try {
			Class.forName(gp.read("sqlname"));// 指定连接类型

			conn = DriverManager.getConnection(gp.read("sqlurl"),
					gp.read("sqlusername"), gp.read("sqlpassword"));// 获取连接
			pst = conn.prepareStatement(sql);// 准备执行语句
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			this.conn.close();
			this.pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
