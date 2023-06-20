import java.util.ArrayList;
import java.util.List;

public class UserInfoRepositoryProto implements UserInfoRepository {
	private List<UserInfo> list;

	public UserInfoRepositoryProto() {
		this.list = new ArrayList<>();
		list.add(new UserInfo("user1", "pw1"));
		list.add(new UserInfo("user2", "pw2"));
		list.add(new UserInfo("user3", "pw3"));
		list.add(new UserInfo("user4", "pw4"));
	}

	@Override
	public int insert(UserInfo userinfo) {
		return list.add(userinfo) ? 1 : 0;
	}

	@Override
	public UserInfo select(UserInfo userinfo) {
		for (UserInfo u : list) {
			if (u.equals(userinfo)) {
				return u;
			}
		}
		return null;
	}

}
