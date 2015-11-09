package worldpay.QS.Selenium.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private Connection conn = null;

	public Connection getConnection() {

		try {

			/*
			 * String driver = Config.CONFIG.getAsJsonObject("JDBCConfig")
			 * .get("driver_class").toString();
			 */

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager
					.getConnection(
							"jdbc:sqlserver://mtlqavdb78:1433;databaseName=ValidationTool_NG",
							"sa", "WPCAJIRA2004v2");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
