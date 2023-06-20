import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

class UserInfoFrame extends JFrame implements ActionListener {
	private CardLayout card;
	private JPanel pnlCenter;
	private SignUpPnl signUpPnl;
	private LoginPnl loginPnl;
	private UserInfoRepository repo;

	public UserInfoFrame(UserInfoRepository repo) {
		this.repo = repo;

		loginPnl = new LoginPnl(this);
		signUpPnl = new SignUpPnl(this);

		pnlCenter = new JPanel();
		card = new CardLayout();
		pnlCenter.setLayout(card);
		add(pnlCenter);

		pnlCenter.add(loginPnl, "login");
		pnlCenter.add(signUpPnl, "signup");

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("회원가입화면")) {
			card.show(pnlCenter, "signup");
		} else if (command.equals("취소")) {
			card.show(pnlCenter, "login");
		} else {
			process(command);
		}
	}

	private void process(String command) {
		if (command.equals("로그인")) {
			UserInfo result = login(loginPnl.getUserInfo());
			loginPnl.setMessage(result != null ? "로그인 성공" : "로그인 실패");
		} else if (command.equals("회원가입")) {
			boolean matches = signUpPnl.matchesPasswords();
			if (matches) {
				signup(signUpPnl.getUserInfo());
				card.show(pnlCenter, "login");
			}
		}
	}

	private int signup(UserInfo userinfo) {
		return repo.insert(userinfo);
	}

	private UserInfo login(UserInfo userinfo) {
		return repo.select(userinfo);
	}
}

public class Main {
	public static void main(String[] args) {
		new UserInfoFrame(new UserInfoRepositoryImpl());
	}
}







