package agijava.gui;

import agijava.main.impl.Text;
import agijava.picture.IPicture;
import agijava.view.ICel;

public interface IGuiController {

	public void letterKeyPressed(String clean);

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

	public boolean isKeyPressWaiting();

	public void setKeyPressWaiting(boolean b);

	public boolean isInputWaiting();

	public void setInputIsWaiting(boolean b);

	public void setWaitingForDeblockingKeyPress(boolean b);

	public boolean isWaitingForDeblockingKeyPress();

	public String getLatestInput();

	public void setInput(String textInput);

	public void handleKeyboardInput();

	public void enterKeyPressed();

	public void backspaceKeyPressed();

	public void changeDirection(int west);

}
