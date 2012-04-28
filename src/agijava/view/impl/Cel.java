package agijava.view.impl;

import agijava.view.ICel;


public class Cel implements ICel {
	private int height;
	private int width;
	private int[][] celData;
	private int transparency;
	
	public Cel(int width, int height) {
		this.width = width;
		this.height = height;
		celData = new int[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				celData[x][y] = -1;
			}
		}
	}
	
	public void appendPixelsToRow(int y, int colorIndex,
			int length) {
		int drawnPixels = 0;
		for (int i = 0; i < width; i++) {
			if (celData[i][y] == -1) {
				celData[i][y] = colorIndex;
				drawnPixels++;
			}
			if (drawnPixels == length) {
				break;
			}
		}
		
	}

	public void FillAllEmptyPixelsWithTransparency() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (celData[x][y] == -1) {
					celData[x][y] = transparency;
				}
			}
		}
		
	}

	public int[][] getCelData() {
		return celData;
	}

	public void setTransparency(int colorIndex) {
		this.transparency = colorIndex;
		
	}

	public int getTransparency() {
		return transparency;
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

	@Override
	public int getPixel(int x, int y) {
		return celData[x][y];
	}

	@Override
	public boolean isTransparentAt(int x, int y) {
		return celData[x][y] == transparency;
	}

}
