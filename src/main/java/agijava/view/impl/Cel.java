package agijava.view.impl;

public class Cel {
	private final int height;
	private final int width;
	private int[][] celData;
	private int transparencyIndex;

	public Cel(int width, int height, int transparentColorIndex) {
		this.width = width;
		this.height = height;
		celData = new int[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				celData[x][y] = -1;
			}
		}
		this.transparencyIndex = transparentColorIndex;
	}

	public void appendPixelsToRow(int y, int colorIndex, int length) {
		int drawnPixels = 0;
		for (int i = 0; i < width; i++) {
			if (isEmpty(y, i)) {
				celData[i][y] = colorIndex;
				drawnPixels++;
			}
			if (drawnPixels == length) {
				break;
			}
		}
	}

	private boolean isEmpty(int y, int i) {
		return celData[i][y] == -1;
	}

	public void fillAllEmptyPixelsWithTransparency() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (isEmpty(y, x)) {
					celData[x][y] = transparencyIndex;
				}
			}
		}

	}

	public int getTransparency() {
		return transparencyIndex;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setPixel(int x, int y, int colorIndex) {
		celData[x][y] = colorIndex;

	}

	public int getPixel(int x, int y) {
		return celData[x][y];
	}

	public boolean isTransparentAt(int x, int y) {
		return celData[x][y] == transparencyIndex;
	}

}
