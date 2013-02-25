package agijava.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import agijava.main.Directions;

public class SwingGraphicsFrame implements KeyListener {
	
	public static int WHITE = 15;
	public static int BLACK = 0;

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
	
	private DefaultAgiPalette palette = new DefaultAgiPalette();
	private JFrame frame;

	private final int width;

	private final int height;
	private final int scale;
	private GameGui gui;

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

	public void drawPixel(int x, int y, int colorIndex) {
		if (x >= 0 && y >= 0 && x < getWidth() && y < getHeight()) {
			bufferGfxImage.setRGB(x, y, palette.getColorRgb(colorIndex));
		}
	}

	public void updateToScreen() {
		BufferStrategy bf = getFrame().getBufferStrategy();
		Graphics g = null;
		try {
			g = bf.getDrawGraphics();
			drawGfxLayerToBuffer(g);
			drawTextObjectsToBuffer(g);
		} finally {
			if (g != null) {
				g.dispose();
			}
		}
		bf.show();
		Toolkit.getDefaultToolkit().sync();
	}

	public void clearScreen() {
		textRows = new String[TEXT_ROWS];
	}

	public int getWidth() {
		return width;
	}

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

	public int getTextHeight() {
		return (int) (getHeight() * 1.0) / TEXT_ROWS;
	}

	public int getTextWidth() {
		return getTextHeight();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char rawInput = e.getKeyChar();
		String clean = cleanInputChar(rawInput);
		gui.letterKeyPressed(clean);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		switch (keyCode) {
		case KeyEvent.VK_LEFT:
			gui.changeDirection(Directions.WEST);
			break;
		case KeyEvent.VK_UP:
			gui.changeDirection(Directions.NORTH);
			break;
		case KeyEvent.VK_RIGHT:
			gui.changeDirection(Directions.EAST);
			break;
		case KeyEvent.VK_DOWN:
			gui.changeDirection(Directions.SOUTH);
			break;
		case KeyEvent.VK_ENTER:
			gui.enterKeyPressed();
			break;
		case KeyEvent.VK_BACK_SPACE:
			gui.backspaceKeyPressed();
			break;
		default:
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
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
	
	private String cleanInputChar(char rawInput) {
		String foo = "" + rawInput;
		return foo.replaceAll("[^A-Za-z, ]", "");
	}

	public void setController(GameGui gui) {
		this.gui = gui;
	}

}
