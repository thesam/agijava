package agijava.gui.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import agijava.gui.IGraphicsDevice;
import agijava.gui.IGraphicsPalette;

public class SwingGraphicsFrame implements IGraphicsDevice {

	private static final int SCALE = 2;
	
	private static final int GFX_SCREEN_X = 320;
	private static final int GFX_SCREEN_Y = 200;
	private static final int OUTPUT_HEIGHT = GFX_SCREEN_Y * SCALE;
	private static final int OUTPUT_WIDTH = GFX_SCREEN_X * SCALE;
	
	@SuppressWarnings("unused")
	private static final int TEXT_COLS = 40;
	private static final int TEXT_ROWS = 25;
	private static final int STRANGE_FONT_MARGIN = 2;
	
	private BufferedImage bufferGfxImage;
	private Font consoleFont;
	private String[] textRows = new String[TEXT_ROWS];
	private int[] textRowColors = new int[TEXT_ROWS];
	private int x0;
	private int y0;

	// GameFramePanel panel;
	
	private IGraphicsPalette palette = new DefaultAgiPalette();
	private JFrame frame;

	public SwingGraphicsFrame(Font font) {
		this.consoleFont = font;
		this.bufferGfxImage = BufferedImageFactory.createBufferGfxImage(GFX_SCREEN_X,GFX_SCREEN_Y);
		this.frame = setupFrame();
	}

	@Override
	public void drawPixel(int x, int y, int colorIndex) {
		if (x >= 0 && y >= 0 && x < GFX_SCREEN_X && y < GFX_SCREEN_Y) {
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
		return GFX_SCREEN_X;
	}

	@Override
	public int getHeight() {
		return GFX_SCREEN_Y;
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
		return (int) (GFX_SCREEN_Y * 1.0) / TEXT_ROWS;
	}

	@Override
	public int getTextWidth() {
		return getTextHeight();
	}

	@Override
	public void addKeyListener(KeyListener listener) {
		getFrame().addKeyListener(listener);
	}

	private JFrame setupFrame() {
		JFrame frame = new JFrame();
		
		frame.setIgnoreRepaint(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(OUTPUT_WIDTH, OUTPUT_HEIGHT);
		
		// Important to set visible before getting inset sizes..
		frame.setVisible(true);
		Insets insets = frame.getInsets();
		int insetWide = insets.left + insets.right;
		int insetTall = insets.top + insets.bottom;
		frame.setSize(frame.getWidth() + insetWide, frame.getHeight() + insetTall);
		x0 = insets.left;
		y0 = insets.top;
		frame.createBufferStrategy(2);
		
		
		return frame;
	}

	private JFrame getFrame() {
		if (frame == null) {
			setupFrame();
		}
		return frame;
	}

	private void drawGfxLayerToBuffer(Graphics g) {
		g.drawImage(bufferGfxImage, x0, y0, OUTPUT_WIDTH, OUTPUT_HEIGHT, null);
	}

	private void drawTextObjectsToBuffer(Graphics g) {
		for (int row = 0; row < TEXT_ROWS; row++) {
			double rowPixelSize = (OUTPUT_HEIGHT * 1.0) / TEXT_ROWS;
			setTextColor(g, row);
			setTextFont(g, rowPixelSize);
			int xPos = x0;
			int yPos = y0 + (int) (row * rowPixelSize);
			yPos += rowPixelSize - SCALE * STRANGE_FONT_MARGIN; // coordinates to drawString must be the text Baseline
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
