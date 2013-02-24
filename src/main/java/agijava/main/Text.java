package agijava.main;

public class Text {

	private final int row;
	private final String message;
	private int col;

	public Text(int row, int col, String message) {
		this.row = row;
		this.message = message;
		this.col = col;
	}

	public int getRow() {
		return row;

	}

	public int getCol() {
		return col;
	}

	public String getMessage() {
		return message;
	}

}
