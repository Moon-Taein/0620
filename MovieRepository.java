import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBUtil;

public class MovieRepository implements IMovieRepository {

	// 테이블 생성
	@Override
	public void createTable() {
		Connection conn = null;
		Statement stmt = null;
		try {
			// 최초로 스태틱 클래스를 사용하면 클래스 로드 작업이 일어남
			conn = DBUtil.getConnection();
			String sql = "create table if not exists movie(\r\n" + "	no int auto_increment\r\n"
					+ "	, name varchar(30) not null\r\n" + "    , date int not null\r\n"
					+ "    , director varchar(20) not null\r\n" + "    , rating double not null\r\n" + "    \r\n"
					+ "    , primary key (no)\r\n" + ");";
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}

	// 행 추가
	@Override
	public int insert(String name, int date, String director, double rating) {
		String sql = "insert into movie (name, date, director, rating) values (?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			// setObject
			stmt.setObject(2, date, Types.INTEGER);
			stmt.setString(3, director);
			stmt.setObject(4, rating, Types.DOUBLE);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return 0;
	}

	// 행 추가2
	@Override
	public int insert(Movie movie) {
		// 정의된 insert 메소드에 movie 객체의 정보만을 보내서 사용
		return insert(movie.getName(), movie.getDate(), movie.getDirector(), movie.getRating());
	}

	// 여러 행 추가
	@Override
	public int[] bulkInsert(List<Movie> movies) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtil.getConnection();
			// auto-commit 비활성화 필요
			// 트랜젝션 제어
			conn.setAutoCommit(false);
			// 여러행의 추가
			String sql = "insert into movie (name, date, director, rating) values (?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			for (Movie m : movies) {
				stmt.setString(1, m.getName());
				stmt.setObject(2, m.getDate(), Types.INTEGER);
				stmt.setString(3, m.getDirector());
				stmt.setObject(4, m.getRating(), Types.DOUBLE);
				// batch - preparedstatement
				stmt.addBatch();
			}
			int[] result = stmt.executeBatch();
			// 커밋
			conn.commit();
			return result;
		} catch (SQLException e) {
			// 롤백
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DBUtil.close(conn);
		}
		return null;
	}

	// 모든 영화 조회
	@Override
	public List<Movie> selectAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Movie> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from movie;";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new Movie(rs.getString("name"), rs.getInt("date"), rs.getString("director"),
						rs.getDouble("rating")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return list;
	}

	@Override
	public List<Movie> selectByName(String partOfName) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Movie> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from movie where name like ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + partOfName + "%");
			rs = stmt.executeQuery();
			while (rs.next()) {
				// getObject
				list.add(new Movie(rs.getString("name"), rs.getObject("date", Integer.class), rs.getString("director"),
						rs.getDouble("rating")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return list;
	}

	@Override
	public List<Movie> selectByDirector(String director) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Movie> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from movie where director = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, director);
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new Movie(rs.getString("name"), rs.getInt("date"), rs.getString("director"),
						rs.getDouble("rating")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return list;
	}

	@Override
	public List<Movie> selectByRating(double ratingTarget, double ratingTarget2) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Movie> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from movie where rating > ? and  rating < ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, ratingTarget);
			stmt.setDouble(2, ratingTarget2);
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new Movie(rs.getString("name"), rs.getInt("date"), rs.getString("director"),
						rs.getDouble("rating")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return list;
	}

	@Override
	public int delete(int number) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from movie where no = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, number);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return 0;
	}

}