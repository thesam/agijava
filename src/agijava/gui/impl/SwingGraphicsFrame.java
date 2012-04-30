package agijava.gui.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import agijava.gui.IGraphicsDevice;
import agijava.gui.IGraphicsPalette;

public class SwingGraphicsFrame implements IGraphicsDevice {

	private final BufferedImage bufferGfxImage;
	private final Font consoleFont;
	
	@SuppressWarnings("unused")
	private static final int TEXT_COLS = 40;
	private static final int TEXT_ROWS = 25;
	private static final int STRANGE_FONT_MARGIN = 2;
	
	private String[] textRows = new String[TEXT_ROWS];
	private int[] textRowColors = new int[TEXT_ROWS];
	private int x0;
	private int y0;

	// GameFramePanel panel;
	
	private IGraphicsPalette palette = new DefaultAgiPalette();
	private JFrame frame;

	private final int width;

	private final int height;
	private final int scale;

	public SwingGraphicsFrame(int width, int height, int scale, Font font, BufferedImage img, JFrame frame, int x0, int y0) {
		this.width = width;
		this.height = height;
		this.consoleFont = font;
		this.bufferGfxImage = img;
		this.frame = frame;
		this.scale = scale;
		this.x0 = x0;
		this.y0 = y0;
	}

	@Override
	public void drawPixel(int x, int y, int colorIndex) {
		if (x >= 0 && y >= 0 && x < getWidth() && y < getHeight()) {
			bufferGfxImage.setRGB(x, y, palette.getColorRgb(colorIndex));
		}
	}

	@Override
	public void updateToScreen() {
		BufferStrategy bf = getFrame().getBufferStrategy();
		Graphics g = null;
		try {
			g = bf.getDrawGraphics();
			drawGfxLayerToBuffer(g);
			drawTextObjectsToBuffer(g);
		} finally {
			g.dispose();
		}
		bf.show();
		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void clearScreen() {
		textRows = new String[TEXT_ROWS];
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	public void printText(int col, int row, String string, int color) {
		String[] lines = string.split("\\n");
		for (String line : lines) {
			StringBuilder current = new StringBuilder();
			for (int c = 0; c < col; c++) {
				current.append(" ");
			}
			current.append(line);
			textRows[row] = current.toString();
			textRowColors[row] = palette.getColorRgb(color);
			row++;
		}
		
	}

	@Override
	public int getTextHeight() {
		return (int) (getHeight() * 1.0) / TEXT_ROWS;
	}

	@Override
	public int getTextWidth() {
		return getTextHeight();
	}

	@Override
	public void addKeyListener(KeyListener listener) {
		getFrame().addKeyListener(listener);
	}

	private int getScaledHeight() {
		return getHeight() * scale;
	}

	private int getScaledWidth() {
		return getWidth() * scale;
	}

	private JFrame getFrame() {
		return frame;
	}

	private void drawGfxLayerToBuffer(Graphics g) {
		g.drawImage(bufferGfxImage, x0, y0, getScaledWidth(), getScaledHeight(), null);
	}

	private void drawTextObjectsToBuffer(Graphics g) {
		for (int row = 0; row < TEXT_ROWS; row++) {
			double rowPixelSize = (getScaledHeight() * 1.0) / TEXT_ROWS;
			setTextColor(g, row);
			setTextFont(g, rowPixelSize);
			int xPos = x0;
			int yPos = y0 + (int) (row * rowPixelSize);
			yPos += rowPixelSize - scale * STRANGE_FONT_MARGIN; // coordinates to drawString must be the text Baseline
			if (textRows[row] != null) {
				g.drawString(textRows[row], xPos, yPos);
			}
		}
	}

	private void setTextFont(Graphics g, double rowPixelSize) {
		Font textFont = consoleFont.deriveFont((float) rowPixelSize);
		g.setFont(textFont);
	}

	private void setTextColor(Graphics g, int row) {
		int colorVal = textRowColors[row];
		g.setColor(new Color(colorVal));
	}

}
