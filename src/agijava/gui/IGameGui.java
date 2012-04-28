package agijava.gui;

import java.awt.event.KeyListener;

import agijava.main.impl.Text;
import agijava.picture.IPicture;
import agijava.view.ICel;

public interface IGameGui {

	public void addKeyListener(KeyListener listener);

	public void appendInput(String clean);

	public void chopInput();

	public void clearCurrentInputLine();

	public void clearScreen();

	public void drawCel(ICel celToDraw, int x, int y, int prio);

	public void drawCurrentInputLine();

	public void drawPicture(IPicture currentPicture);

	public void drawStatusBar();

	public void drawTextDialog(String currentMessage);

	public String getCurrentInputLine();

	public void printText(Text text);

	public void updateToScreen();

}
