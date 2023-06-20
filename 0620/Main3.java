import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import dbutil.DBUtil;

public class Main3 {

	// encoding by base64
	public static String encodeBase64(byte[] bytes) {
		return Base64.getMimeEncoder().encodeToString(bytes);
	}

	// decoding by base64
	public static byte[] decodeBase64(String encoded) {
		return Base64.getMimeDecoder().decode(encoded);
	}

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("select * from files_base64 where id = ?");
			stmt.setInt(1, 1);
			rs = stmt.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String contentEncoded = rs.getString("contentEncoded");
				byte[] bytes = decodeBase64(contentEncoded);
				Files.write(Paths.get("d:\\filetest", "복사본_" + name), bytes);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}

//		Connection conn = null;
//		PreparedStatement stmt = null;
//
//		try {
//			byte[] bytes = Files.readAllBytes(Paths.get("d:\\filetest\\춘식2.png"));
//			String encoded = encodeBase64(bytes);
//			conn = DBUtil.getConnection();
//			stmt = conn.prepareStatement("insert into files_base64 (name, contentEncoded) values (?, ?)");
//			stmt.setString(1, "춘식2.png");
//			stmt.setString(2, encoded);
//			stmt.executeUpdate();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			DBUtil.close(stmt);
//			DBUtil.close(conn);
//		}

	}
}