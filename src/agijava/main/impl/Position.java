package agijava.main.impl;

public class Position {

	private final int x;
	private final int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Position)) {
			return false;
		}
		Position otherPos = (Position) obj;
		return x == otherPos.x && y == otherPos.y;
	}

}
