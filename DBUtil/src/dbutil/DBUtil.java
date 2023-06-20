package dbutil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
	// key = value 형태의 properties 파일을 객체 형태로 사용할 수 있는 클래스이다.
	private static final Properties PROPS = new Properties();

	static {
		try {
			PROPS.load(DBUtil.class.getClassLoader().getResourceAsStream("mysql.properties"));
			Class.forName(PROPS.getProperty("jdbc.DRIVER"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(PROPS.getProperty("jdbc.URL"), PROPS.getProperty("jdbc.USER"),
				PROPS.getProperty("jdbc.PASSWORD"));
	}

	public static void close(Connection conect) {
		if (conect != null) {
			try {
				conect.close();
			} catch (SQLException e) {
			}
		}
	}

	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		}
	}

	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
	}

}
