import java.util.Arrays;
import java.util.List;

public class TestBulkInsert {
	public static void main(String[] args) {

		// all or nothing 에 적합하지않음
		// 1235 는 db에 올라가고 4는 안올라감
		// 전부 적합한 데이터일 경우에 다같이 db에 넣을수있게 해야한다
		// 트랜젝션 개념
		// 동작중에 하나라도 문제 생기면 롤백
		// 전부 잘 동작했으면 커밋
		List<Movie> movies = Arrays.asList(new Movie("영화1", 1999, "감독1", 4.3), new Movie("영화2", 1999, "감독2", 4.4),
				new Movie("영화3", 1999, "감독3", 4.5), new Movie("영화4", 1999, "감독4", 4.6),
				new Movie("영화5", 1999, "감독5", 4.7));

		MovieRepository mr = new MovieRepository();

		int[] result = mr.bulkInsert(movies);
		if (result != null) {
			System.out.println(Arrays.toString(result));
		}

		System.out.println("bulk insert 종료");
	}
}