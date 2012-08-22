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

	@Override
	public void reset() {
		this.buffer = new int[gfxWidth][gfxHeight];
		for (int i = 0; i < gfxWidth; i++) {
			Arrays.fill(buffer[i], -1);
		}
	}

	@Override
	public void drawPixel(int x, int y, int prio) {
		this.buffer[x][y] = prio;
	}

	@Override
	public int getPixel(int x, int y) {
		return buffer[x][y];
	}

}
