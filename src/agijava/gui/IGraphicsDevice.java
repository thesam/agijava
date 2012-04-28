package agijava.gui;

import java.awt.event.KeyListener;

public interface IGraphicsDevice {

	int WHITE = 15;
	int BLACK = 0;

	void drawPixel(int x, int y, int colorIndex);

	void updateToScreen();

	void addKeyListener(KeyListener listener);

	void clearScreen();

	int getWidth();

	void printText(int col, int row, String string, int color);
	
	int getHeight();

	int getTextHeight();

	int getTextWidth();

}
