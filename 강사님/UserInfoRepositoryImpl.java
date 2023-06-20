import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutil.DBUtil;

public class UserInfoRepositoryImpl implements UserInfoRepository {
	@Override
	public int insert(UserInfo userinfo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("INSERT INTO userinfo (id, password) VALUES (?, ?)");
			stmt.setString(1, userinfo.getId());
			stmt.setString(2, userinfo.getPassword());
			
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return 0;
	}

	@Override
	public UserInfo select(UserInfo userinfo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM userinfo WHERE id = ? AND password = ?");
			stmt.setString(1, userinfo.getId());
			stmt.setString(2, userinfo.getPassword());
			
			rs = stmt.executeQuery();
			if (rs.next()) {
				return new UserInfo(rs.getString("id"), rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return null;
	}
}




