import java.util.Map;

public class TestValidator {
	public static void main(String[] args) {
		UserInfoValidator validator = new UserInfoValidator();

//		Map<String, String> result;
//		result = validator.validateId("abcde");
//
//		System.out.println(result.size());
//
//		result = validator.validateId("0abcd");
//		if (result.size() > 0) {
//			System.out.println(result);
//		}
//
//		result = validator.validateId("가나다라");
//		if (result.size() > 0) {
//			System.out.println(result);
//		}

		Map<String, String> result2;
		result2 = validator.validatePassword("가나다라");
		System.out.println(result2);

		result2 = validator.validatePassword("가나다라가나다라가나다라가나다라가나다라가나다라가나다라가나다라가나다라");
		System.out.println(result2);

		result2 = validator.validatePassword("sunshine90!@#");
		System.out.println(result2);

	}
}