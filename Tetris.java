package tetris;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Tetris extends JFrame {

	JLabel statusbar;

	public Tetris() {

		statusbar = new JLabel("0");//Se inițializează variabila statusbar cu un nou obiect JLabel care afișează textul "0".
		add(statusbar, BorderLayout.NORTH);//Am adaugat statusbar la fereastra Tetris în partea de sus folosind BorderLayout.NORTH
		Board board = new Board(this);//Am creat un nou obiect Board (tablă de joc),care stochează în variabila board. 
		add(board);
		board.start();

		setSize(600, 800);//Dimensiunea ferestrei
		setTitle("Tetris");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public JLabel getStatusBar() {//Se definește o metodă getStatusBar() care returnează obiectul statusbar.Am folosit aceasta metoda pentru a accesa statusbar din alte clase.
		return statusbar;
	}

	public static void main(String[] args) {

		Tetris game = new Tetris();
		game.setLocationRelativeTo(null);//setare fereastra la mijloc
		game.setVisible(true);//setare fereastra vizibila 

	}
}
