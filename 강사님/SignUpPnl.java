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

public class SignUpPnl extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldConfirm;

	public UserInfo getUserInfo() {
		return new UserInfo(textField.getText(), passwordField.getText());
	}
	
	public boolean matchesPasswords() {
		return passwordField.getText().equals(passwordFieldConfirm.getText());
	}
	
	/**
	 * Create the panel.
	 * @param userInfoFrame 
	 */
	public SignUpPnl(UserInfoFrame userInfoFrame) {
		setPreferredSize(new Dimension(350, 180));
		
		JLabel lblID = new JLabel("아이디");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("비밀번호");
		
		passwordField = new JPasswordField();
		
		JButton btnSignUp = new JButton("회원가입");
		btnSignUp.addActionListener(userInfoFrame);
		
		JLabel lblPassword_1 = new JLabel("확인");
		
		passwordFieldConfirm = new JPasswordField();
		
		JButton btnCancel = new JButton("취소");
		btnCancel.addActionListener(userInfoFrame);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSignUp, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPassword)
								.addComponent(lblID))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPassword_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(passwordFieldConfirm, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))
						.addComponent(btnCancel, Alignment.TRAILING))
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
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword_1)
						.addComponent(passwordFieldConfirm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnSignUp)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
