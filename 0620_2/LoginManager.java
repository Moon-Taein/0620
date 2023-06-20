import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutil.DBUtil;

public class LoginManager {

	// 행 추가
	/**
	 * @author moon93
	 * @param User instance
	 * @return db에 입력되면 1, 아니면 0
	 */
	public int inputUser(String id, String password) {
		String sql = "insert into users (id, password) values (?, ?);";
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, password);
			stmt.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return 0;
	}

	/**
	 * @author moon93
	 * @param User instance
	 * @return db에 입력되면 1, 아니면 0
	 */
	public int inputUser(User user) {
		return inputUser(user.getId(), user.getPassword());
	}

	// 로그인
	/**
	 * @author moon93
	 * @param String id, String password
	 * @return 로그인 성공 1, 로그인 실패 0, ExceptionError -1
	 */
	public int loginCheck(String id, String password) {
		String sql = "select * from users where id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				String DbPassword = rs.getString("password");
				if (DbPassword.equals(password)) {
					return 1;
				} else {
					return 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return -1;
	}
}