import java.util.HashMap;
import java.util.Map;

public class UserInfoValidator {
	public Map<String, String> validateId(String id) {
		// 1 ~ 15 이하.
		Map<String, String> errorMap = new HashMap<>();
		int length = id.length();
		if (length < 1 || length > 15) {
			// 문제사항 기록
			// id를 키로 문제사항을 기록
			errorMap.put("length", "아이디는 1 ~ 15자 사이여야 합니다.");
		}
		// 영소문자로 시작해야한다.
		char firstChar = id.charAt(0);
		if (firstChar < 'a' || firstChar > 'z') {
			errorMap.put("firstLettet", "첫 글자는 영소문자여야 합니다.");
		}
		// 영소문자와 0~9 숫자만을 사용해야한다.
		if (!rangeCheckId(id)) {
			errorMap.put("letters", "아이디는 영소문자와 0~9만 사용할 수 있습니다.");
		}
		return errorMap;
	}

	public Map<String, String> validatePassword(String password) {
		Map<String, String> errorMap = new HashMap<>();
		// 1 ~ 15 이하.
		int length = password.length();
		if (length < 1 || length > 15) {
			// 문제사항 기록
			// id를 키로 문제사항을 기록
			errorMap.put("length", "비밀번호는 1 ~ 15자 사이여야 합니다.");
		}

		// ! @ # $ % 중 하나를 꼭 포함해야 합니다.
		if (symbolCheck(password)) {
			errorMap.put("special symbol", "최소 1개의 특수기호를 포함해야합니다.");
		}

		// a ~ z, A ~ Z, 0 ~ 9, ! @ # $ %
		if (rangeCheckPassword(password)) {
			errorMap.put("letters", "아이디는 영대소문자와 0~9 그리고 \"! @ # $ % \"만 사용할 수 있습니다.");
		}

		return errorMap;
	}

	private boolean rangeCheckId(String str) {
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if ((c < 'a' || c > 'z') && (c < '0' || c > '9')) {
				return false;
			}
		}
		return true;
	}

	private boolean rangeCheckPassword(String str) {
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if ((c > 'a' && c < 'z') || (c > '0' && c < '9')
					|| (c > 'A' && c < 'Z') && (c == '!' || c == '@' || c == '#' || c == '$' || c == '%')) {
				return false;
			}
		}
		return true;
	}

	private boolean symbolCheck(String str) {
		String[] symbols = { "!", "@", "#", "$", "%" };
		for (String s : symbols) {
			if (str.contains(s)) {
				return false;
			}
		}
		return true;
	}
}