import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbutil.DBUtil;

public class Main {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement stmt = null;
		FileInputStream fis = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("insert into files (name, content) values (?, ?)");
			stmt.setString(1, "춘식3.png");
			fis = new FileInputStream(new File("D:\\Moon\\Eclipse_project\\춘식\\춘식3.png"));
			stmt.setBlob(2, fis);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}

	}
}
