package agijava.gui.impl;

import java.util.Arrays;

public class PrioBuffer implements IPrioBuffer {

	private int[][] buffer;
	private int gfxHeight;
	private int gfxWidth;

	public PrioBuffer(int height, int width) {
		this.gfxHeight = height;
		this.gfxWidth = width;
		reset();
	}

	public void reset() {
		this.buffer = new int[gfxWidth][gfxHeight];
		for (int i = 0; i < gfxWidth; i++) {
			Arrays.fill(buffer[i], -1);
		}
	}

	public void drawPixel(int x, int y, int prio) {
		this.buffer[x][y] = prio;
	}

	public int getPrioAt(int x, int y) {
		return this.buffer[x][y];
	}

	@Override
	public int getPixel(int x, int y) {
		return buffer[x][y];
	}

}
