package agijava.gui.impl;

import java.awt.event.KeyListener;

import agijava.gui.IGameGui;
import agijava.gui.IGraphicsDevice;
import agijava.gui.IPrioCalculator;
import agijava.main.impl.Text;
import agijava.picture.IPicture;
import agijava.view.ICel;

public class GameGui implements IGameGui {

	private final static int MENU_BAR_HEIGHT = 8;
	private IGraphicsDevice graphics;
	private String currentInputLine = "";
	private IPrioCalculator prioCalculator;
	private IPrioBuffer prioBuffer;

	public GameGui(IGraphicsDevice graphics, IPrioCalculator prioCalculator,
			IPrioBuffer prioBuffer) {
		this.graphics = graphics;
		this.prioCalculator = prioCalculator;
		this.prioBuffer = prioBuffer;
	}

	public void drawPicture(IPicture pic) {
		for (int x = 0; x < pic.getWidth(); x++) {
			for (int y = 0; y < pic.getHeight(); y++) {
				drawToGfxArea(x, y, pic.getPictureColorAt(x, y), pic.getPrioForDrawingAt(x, y));
			}
		}
	}

	public void drawCel(ICel celToDraw, int x0, int y0, int prio) {
		int yBottom = y0;
		y0 -= celToDraw.getHeight();
		for (int x = 0; x < celToDraw.getWidth(); x++) {
			for (int y = 0; y < celToDraw.getHeight(); y++) {
				if (!celToDraw.isTransparentAt(x, y)) {
					drawIfPrioIsRight(x0 + x, y0 + y, prio, yBottom,
							celToDraw.getPixel(x, y));
				}
			}
		}
	}

	public void drawCurrentInputLine() {
		graphics.printText(0, 23, ">" + currentInputLine + "_",
				IGraphicsDevice.WHITE);
	}

	public void drawStatusBar() {
		for (int x = 0; x < graphics.getWidth(); x++) {
			for (int y = 0; y < MENU_BAR_HEIGHT; y++) {
				graphics.drawPixel(x, y, IGraphicsDevice.WHITE);
			}
			 graphics.printText(0, 0, "Score: x of 250 - Sound: x",
			 IGraphicsDevice.BLACK);
		}

	}

	public void updateToScreen() {
		graphics.updateToScreen();
	}

	public void clearScreen() {
		graphics.clearScreen();
		prioBuffer.reset();
	}

	public void printText(Text text) {
		int col = text.getCol();
		int row = text.getRow();
		String message = text.getMessage();
		String[] rows = message.split("\\n");
		for (String string : rows) {
			graphics.printText(col, row, string, IGraphicsDevice.WHITE);
			row++;
			col = 0;
		}
	}

	public void addKeyListener(KeyListener listener) {
		graphics.addKeyListener(listener);
	}

	public void appendInput(String keyChar) {
		currentInputLine = currentInputLine + keyChar;
	}

	public String getCurrentInputLine() {
		return currentInputLine;
	}

	public void clearCurrentInputLine() {
		currentInputLine = "";

	}

	public void setCurrentInputLine(String line) {
		this.currentInputLine = line;
	}

	public void chopInput() {
		if (currentInputLine.length() > 1) {
			currentInputLine = currentInputLine.substring(0,
					currentInputLine.length() - 1);
		} else {
			currentInputLine = "";
		}

	}

	public void drawTextDialog(String currentMessage) {
		// JOptionPane.showMessageDialog(null, currentMessage);
		// drawEmptyTextBoxForText(5,20,currentMessage);

		int defaultCol = 3;
		int defaultRow = 3;
		int y0 = defaultRow * graphics.getTextHeight();
		int x0 = defaultCol * graphics.getTextWidth();
		TextDialogGenerator textDialogGenerator = new TextDialogGenerator();
		TextDialog dialog = textDialogGenerator.createFromString(currentMessage);
		for (int y = y0; y < y0 + dialog.getHeight(); y++) {
			for (int x = x0; x < x0 + dialog.getWidth(); x++) {
				drawDirectlyToGraphicsDevice(x, y, IGraphicsDevice.WHITE);
			}
		}
		graphics.printText(defaultCol, defaultRow, dialog.getMessage(),
				IGraphicsDevice.BLACK);
	}

	private void drawIfPrioIsRight(int x, int y, int prio, int yBottom,
			int colorIndex) {
		int prioFromBackgroundPicture = getFromPrioBuffer(x, y);
		if (!prioIsSet(prio)) {
			prio = prioCalculator.getPrioBasedOnPosition(yBottom);
		}
		if (prio >= prioFromBackgroundPicture) {
			drawToGfxArea(x, y, colorIndex, prio);
		}
	}

	private int getFromPrioBuffer(int x, int y) {
		return prioBuffer.getPixel(x, MENU_BAR_HEIGHT + y);
	}

	private boolean prioIsSet(int prio) {
		return prio != -1;
	}

	private void drawToGfxArea(int x, int y, int colorIndex, int prio) {
		drawToVisibleGraphics(x, y, colorIndex);
		drawToPrioBuffer(x, y, prio);
	}

	private void drawToPrioBuffer(int x, int y, int prio) {
		if (prioIsSet(prio)) {
			prioBuffer.drawPixel(x, MENU_BAR_HEIGHT + y, prio);
		}
	}

	private void drawToVisibleGraphics(int x, int y, int colorIndex) {
		drawScaledByFactor2InWidth(x, y, colorIndex);
	}

	private void drawScaledByFactor2InWidth(int x, int y, int colorIndex) {
		drawDirectlyToGraphicsDevice(2*x, MENU_BAR_HEIGHT + y, colorIndex);
		drawDirectlyToGraphicsDevice(2*x+1, MENU_BAR_HEIGHT + y, colorIndex);
	}

	private void drawDirectlyToGraphicsDevice(int x, int y, int colorIndex) {
		graphics.drawPixel(x, y, colorIndex);
	}

}
