import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {
	private LoginManager lm;

	private JPanel contentPane;
	private JTextField textFieldId;
	private JTextField txtPasswrod;

	private JLabel lblState;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		lm = new LoginManager();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 265, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldId = new JTextField();
		textFieldId.setBounds(31, 93, 116, 21);
		contentPane.add(textFieldId);
		textFieldId.setColumns(10);

		txtPasswrod = new JPasswordField();
		txtPasswrod.setBounds(31, 124, 116, 21);
		contentPane.add(txtPasswrod);
		txtPasswrod.setColumns(10);

		lblState = new JLabel("회원이 되어라");
		lblState.setBounds(31, 31, 116, 52);
		contentPane.add(lblState);

		JButton btnCreateId = new JButton("가입");
		btnCreateId.setBounds(159, 31, 78, 52);
		btnCreateId.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = lm.inputUser(textFieldId.getText(), txtPasswrod.getText());
				if (result == 1) {
					JOptionPane.showMessageDialog(null, "성공적으로 가입되었습니다.", "가입 성공!", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "회원가입에 실패하였습니다.", "가입 실패!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		contentPane.add(btnCreateId);

		JButton btnLogin = new JButton("로그인");
		btnLogin.setBounds(159, 93, 78, 52);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int check = lm.loginCheck(textFieldId.getText(), txtPasswrod.getText());
				if (check == 1) {
					JOptionPane.showMessageDialog(null, "로그인 성공하였습니다.", "로그인 성공!", JOptionPane.PLAIN_MESSAGE);
				} else if (check == 0) {
					JOptionPane.showMessageDialog(null, "비밀번호 틀렸습니다.", "로그인 실패!", JOptionPane.WARNING_MESSAGE);
				} else if (check == -1) {
					JOptionPane.showMessageDialog(null, "ID가 존재하지 않습니다.", "로그인 실패!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		contentPane.add(btnLogin);
	}
}