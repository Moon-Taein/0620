import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class MovieFrame extends JFrame {
	private MovieRepository repo;
	private JPanel pnlCenter;

	public MovieFrame() {
		repo = new MovieRepository();

		JPanel pnlNorth = new JPanel();
		pnlCenter = new JPanel();

		JComboBox<String> combo = new JComboBox<>(new String[] { "제목", "감독" });
		JTextField tf = new JTextField(10);

		pnlNorth.add(combo);
		pnlNorth.add(tf);

		JButton btn = new JButton("검색");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = (String) combo.getSelectedItem();
				String word = tf.getText();
				pnlCenter.removeAll();
				process(selected, word);
				pnlCenter.revalidate();
				pnlCenter.repaint();
			}
		});
		pnlNorth.add(btn);

		add(pnlNorth, "North");
		add(pnlCenter, "Center");

		setSize(290, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void process(String selected, String word) {
		if (selected.equals("제목")) {
			selectByName(word);
		} else if (selected.equals("감독")) {
			selectByDirector(word);
		} else {
			selectAll();
		}
	}

	private void selectAll() {
		// 검색 수행
		List<Movie> list = repo.selectAll();
		// 결과 디스플레이
		display(list);
	}

	private void selectByName(String word) {
		// 검색 수행
		List<Movie> list = repo.selectByName(word);
		// 결과 디스플레이
		display(list);
	}

	private void selectByDirector(String word) {
		// 검색 수행
		List<Movie> list = repo.selectByDirector(word);
		// 결과 디스플레이
		display(list);
	}

	private void display(List<Movie> list) {
		for (Movie movie : list) {
			JPanel pnl = new JPanel();
			JLabel lblTitle = new JLabel(movie.getName());
			JLabel lblYear = new JLabel(String.valueOf(movie.getDate()));
			JLabel lblDirector = new JLabel(movie.getDirector());
			JLabel lblScore = new JLabel(String.valueOf(movie.getRating()));
			pnl.add(lblTitle);
			pnl.add(lblYear);
			pnl.add(lblDirector);
			pnl.add(lblScore);
			pnlCenter.add(pnl);
		}
	}
}

public class Main {
	public static void main(String[] args) {
		new MovieFrame();
	}
}