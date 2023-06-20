import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
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
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

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
		setBounds(800, 450, 331, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImageIcon image = new ImageIcon("D:\\Moon\\Eclipse_project\\춘식\\춘식16.png");
		lblState = new JLabel(image);
		lblState.setBounds(12, 10, 210, 188);
		contentPane.add(lblState);

		textFieldId = new JTextField();
		textFieldId.setBounds(106, 208, 116, 21);
		contentPane.add(textFieldId);
		textFieldId.setColumns(10);

		txtPasswrod = new JPasswordField();
		txtPasswrod.setBounds(106, 234, 116, 21);
		contentPane.add(txtPasswrod);
		txtPasswrod.setColumns(10);

		JButton btnCreateId = new JButton("가입");
		btnCreateId.setBounds(234, 10, 78, 118);
		btnCreateId.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!textFieldId.getText().contains(" ")) {
					int result = lm.inputUser(textFieldId.getText(), txtPasswrod.getText());
					if (result == 1) {
						JOptionPane.showMessageDialog(null, "성공적으로 가입되었습니다.", "가입 성공!", JOptionPane.PLAIN_MESSAGE);
						setLabelImage(15);
					} else if (result == -1) {
						JOptionPane.showMessageDialog(null, "가입에 실패하였습니다.", "가입 실패!", JOptionPane.WARNING_MESSAGE);
						setLabelImage(1);
					}
				} else {
					JOptionPane.showMessageDialog(null, "공백을 포함한 ID는 할수없습니다.", "가입 실패!", JOptionPane.PLAIN_MESSAGE);
					setLabelImage(11);
				}
			}
		});
		contentPane.add(btnCreateId);

		JButton btnLogin = new JButton("로그인");
		btnLogin.setBounds(234, 141, 78, 114);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int check = lm.loginCheck(textFieldId.getText(), txtPasswrod.getText());
				if (check == 1) {
					JOptionPane.showMessageDialog(null, "로그인 성공하였습니다.", "로그인 성공!", JOptionPane.PLAIN_MESSAGE);
					setLabelImage(18);
				} else if (check == 0) {
					JOptionPane.showMessageDialog(null, "비밀번호 틀렸습니다.", "로그인 실패!", JOptionPane.WARNING_MESSAGE);
					setLabelImage(14);
				} else if (check == -1) {
					JOptionPane.showMessageDialog(null, "ID가 존재하지 않습니다.", "로그인 실패!", JOptionPane.WARNING_MESSAGE);
					setLabelImage(17);
				}
			}
		});
		contentPane.add(btnLogin);

		lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Adobe 고딕 Std B", Font.PLAIN, 12));
		lblNewLabel.setBounds(71, 212, 23, 15);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("password");
		lblNewLabel_1.setFont(new Font("Adobe 고딕 Std B", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(37, 238, 57, 15);
		contentPane.add(lblNewLabel_1);
	}

	public void setLabelImage(int number) {
		ImageIcon image = new ImageIcon("D:\\Moon\\Eclipse_project\\춘식\\춘식" + number + ".png");
		lblState.setIcon(image);
	}
}