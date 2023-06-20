import java.util.List;

// 1. 영화 검색 및 조회 서비스를 위한 데이터베이스 구현
// - 제목
// - 개봉(년)
// - 감독명
// - 평점

// 2. CRUD 메소드 구현
// - 행 추가 메소드
// - 제목 검색('일부' 검색)
// - 감독명 검색(일치)
// - 평점은 범위로 검색
// - 

public class Main {
	public static void main(String[] args) {
		MovieRepository mr = new MovieRepository();

		// 테이블 생성
//		mr.createTable();
//		mr.insert("마션", 2015, "리들리 스콧", 8.42);
//		mr.insert(new Movie("컬러풀", 2010, "하라 케이이치", 8.56));
//		System.out.println(mr.selectAll());
//		System.out.println(mr.selectByName("시"));
//		System.out.println(mr.selectByDirector("대니 보일"));
		List<Movie> list = mr.selectByRating(7.0, 8.5);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
//		mr.delete(14);
	}
}