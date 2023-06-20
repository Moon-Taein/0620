import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import java.awt.Dimension;
import javax.swing.JButton;

public class LoginPnl extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblMessage;
	
	public UserInfo getUserInfo() {
		return new UserInfo(textField.getText(), passwordField.getText());
	}
	
	public void setMessage(String message) {
		lblMessage.setText(message);
	}
	
	/**
	 * Create the panel.
	 * @param userInfoFrame 
	 */
	public LoginPnl(UserInfoFrame userInfoFrame) {
		setPreferredSize(new Dimension(350, 180));
		
		JLabel lblID = new JLabel("아이디");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("비밀번호");
		
		passwordField = new JPasswordField();
		
		JButton btnLogin = new JButton("로그인");
		btnLogin.addActionListener(userInfoFrame);
		JButton btnSignUp = new JButton("회원가입화면");
		btnSignUp.addActionListener(userInfoFrame);
		
		lblMessage = new JLabel("");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPassword)
								.addComponent(lblID))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)))
						.addComponent(btnSignUp, Alignment.TRAILING)
						.addComponent(lblMessage, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblID)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnLogin)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblMessage)
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addComponent(btnSignUp)
					.addContainerGap())
		);
		setLayout(groupLayout);

	}
}
