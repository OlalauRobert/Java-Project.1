package tetris;

import java.util.Random;
import java.lang.Math;

public class Shape {//Am creat o clasa Shape pentru reprezentarea formelor pieselor

	enum Tetrominoes {//Se definește o enumerație Tetrominoes, care reprezintă toate formele posibile ale pieselor Tetris, inclusiv forma (NoShape) și cele 7 forme ale pieselor.
	 ZShape, LineShape, MirroredLShape, SShape, SquareShape, LShape, NoShape, TShape,};

	private Tetrominoes pieceShape;//Declararea de variabile private pentru a reține forma piesei curente (pieceShape), coordonatele piesei curente (coords) și o tabelă de coordonate pentru fiecare formă (coordsTable).
	private int coords[][];
	private int[][][] coordsTable;

	public Shape() {//Constructorul clasei Shape 

		coords = new int[4][2];//inițializează variabila coords pentru a reține coordonatele a 4 puncte (x, y)
		setShape(Tetrominoes.NoShape);//setarea formei

	}

	public void setShape(Tetrominoes shape) {//Metoda setShape() setează forma piesei la forma specificată și inițializează coordonatele piesei în funcție de forma specificată.
//Această secțiune alocă spațiu pentru variabila coordsTable, care este un tablou tridimensional. Acest tablou stochează coordonatele relative ale celor patru părți, ale fiecărei forme Tetris.
		coordsTable = new int[][][] { { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } },
				{ { 0, -1 }, { 0, 0 }, { -1, 0 }, { -1, 1 } }, { { 0, -1 }, { 0, 0 }, { 1, 0 }, { 1, 1 } },
				{ { 0, -1 }, { 0, 0 }, { 0, 1 }, { 0, 2 } }, { { -1, 0 }, { 0, 0 }, { 1, 0 }, { 0, 1 } },
				{ { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } }, { { -1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } },
				{ { 1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } } };
//Acest ciclu dublu iterează prin toate cele patru părți ale piesei (i) și coordonatele lor (j). În fiecare iterație, valorile coordonatelor pentru piesa specificată (shape) sunt preluate din coordsTable și atribuite la variabila coords, care reține coordonatele piesei curente.
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; ++j) {
				coords[i][j] = coordsTable[shape.ordinal()][i][j];
			}
		}
		pieceShape = shape;//La final, forma piesei (pieceShape) este actualizată la forma specificată în argumentul metodei.

	}

	private void setX(int index, int x) {//Pentru a seta coordonatele piesei la un anumit index.
		coords[index][0] = x;
	}

	private void setY(int index, int y) {//Pentru a seta coordonatele piesei la un anumit index.
		coords[index][1] = y;
	}

	public int x(int index) {//Returneaza coordonata x pentru un anumit index
		return coords[index][0];
	}

	public int y(int index) {//Returneaza coordonata y pentru un anumit index
		return coords[index][1];
	}

	public Tetrominoes getShape() {// Returnează forma curentă a piesei.
		return pieceShape;
	}

	public void setRandomShape() {//Setează forma piesei la una aleatoare din cele disponibile.
		Random r = new Random();
		int x = Math.abs(r.nextInt()) % 7 + 1;
		Tetrominoes[] values = Tetrominoes.values();
		setShape(values[x]);
	}
	//Metodele minX() și minY() returnează coordonatele minime pe axa x, respectiv pe axa y, ale piesei.
	public int minX() {
		int m = coords[0][0];
		for (int i = 0; i < 4; i++) {
			m = Math.min(m, coords[i][0]);
		}
		return m;
	}

	public int minY() {
		int m = coords[0][1];
		for (int i = 0; i < 4; i++) {
			m = Math.min(m, coords[i][1]);
		}
		return m;
	}
////Cu ajutorul acestei metode rotim piesa la stanga cu exceptia SquareShape.
	public Shape rotateLeft() {
		if (pieceShape == Tetrominoes.SquareShape)
			return this;

		Shape result = new Shape();
		result.pieceShape = pieceShape;

		for (int i = 0; i < 4; ++i) {
			result.setX(i, y(i));
			result.setY(i, -x(i));
		}
		return result;
	}//Cu ajutorul acestei metode rotim piesa la dreapta cu exceptia SquareShape.
	public Shape rotateRight() {
		if (pieceShape == Tetrominoes.SquareShape)
			return this;

		Shape result = new Shape();
		result.pieceShape = pieceShape;

		for (int i = 0; i < 4; ++i) {
			result.setX(i, -y(i));
			result.setY(i, x(i));
		}
		return result;
	}
}
