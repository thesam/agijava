package agijava.gui.impl;

import agijava.gui.IGuiController;
import agijava.gui.IGuiView;
import agijava.gui.IPrioCalculator;
import agijava.main.IAnimatedObject;
import agijava.main.IGameState;
import agijava.main.impl.GameEngine;
import agijava.main.impl.Text;
import agijava.picture.IPicture;
import agijava.view.ICel;

public class GameGui implements IGuiController {

	private final static int MENU_BAR_HEIGHT = 8;
	private IGuiView graphics;
	private String currentInputLine = "";
	private IPrioCalculator prioCalculator;
	private IPrioBuffer prioBuffer;
	private boolean keyPressWaiting;
	private boolean inputIsWaiting;
	private String input;
	private boolean waitingForDeblockingKeyPress;
	private final IGameState gameState;

	public GameGui(IPrioCalculator prioCalculator, IGameState gameState,
			IGuiView graphics, IPrioBuffer prioBuffer) {
		this.prioCalculator = prioCalculator;
		this.gameState = gameState;
		this.graphics = graphics;
		this.prioBuffer = prioBuffer;
	}

	public void drawPicture(IPicture pic) {
		for (int x = 0; x < pic.getWidth(); x++) {
			for (int y = 0; y < pic.getHeight(); y++) {
				drawToGfxArea(x, y, pic.getPictureColorAt(x, y),
						pic.getPrioForDrawingAt(x, y));
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
				IGuiView.WHITE);
	}

	public void drawStatusBar() {
		for (int x = 0; x < graphics.getWidth(); x++) {
			for (int y = 0; y < MENU_BAR_HEIGHT; y++) {
				graphics.drawPixel(x, y, IGuiView.WHITE);
			}
			graphics.printText(0, 0, "Score: x of 250 - Sound: x",
					IGuiView.BLACK);
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
			graphics.printText(col, row, string, IGuiView.WHITE);
			row++;
			col = 0;
		}
	}

	public void letterKeyPressed(String keyChar) {
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
		TextDialog dialog = textDialogGenerator
				.createFromString(currentMessage);
		for (int y = y0; y < y0 + dialog.getHeight(); y++) {
			for (int x = x0; x < x0 + dialog.getWidth(); x++) {
				drawDirectlyToGraphicsDevice(x, y, IGuiView.WHITE);
			}
		}
		graphics.printText(defaultCol, defaultRow, dialog.getMessage(),
				IGuiView.BLACK);
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
		drawDirectlyToGraphicsDevice(2 * x, MENU_BAR_HEIGHT + y, colorIndex);
		drawDirectlyToGraphicsDevice(2 * x + 1, MENU_BAR_HEIGHT + y, colorIndex);
	}

	private void drawDirectlyToGraphicsDevice(int x, int y, int colorIndex) {
		graphics.drawPixel(x, y, colorIndex);
	}

	public boolean isKeyPressWaiting() {
		return keyPressWaiting;
	}

	public void setKeyPressWaiting(boolean b) {
		this.keyPressWaiting = b;
	}

	public boolean isInputWaiting() {
		return inputIsWaiting;
	}

	public String getLatestInput() {
		return input;
	}

	public void setInputIsWaiting(boolean b) {
		inputIsWaiting = b;
	}

	public void setWaitingForDeblockingKeyPress(boolean b) {
		waitingForDeblockingKeyPress = b;
	}

	public boolean isWaitingForDeblockingKeyPress() {
		return waitingForDeblockingKeyPress;
	}

	@Override
	public void setInput(String textInput) {
		this.input = textInput;
	}

	@Override
	public void handleKeyboardInput() {
		if (isKeyPressWaiting()) {
			gameState.setHaveKey(true);
			setKeyPressWaiting(false);
		} else {
			gameState.setHaveKey(false);
		}
		if (isInputWaiting()) {
			gameState.setLatestInput(getLatestInput());
			gameState.setFlag(GameEngine.FLAG_TEXT_ENTERED);
			setInputIsWaiting(false);
		}
	}

	@Override
	public void enterKeyPressed() {
		setKeyPressWaiting(true);
		if (acceptsInput()) {
			setWaitingForDeblockingKeyPress(false);
			String textInput = getCurrentInputLine();
			if (!textInput.isEmpty()) {
				setInputIsWaiting(true);
				setInput(textInput);
				clearCurrentInputLine();
			}
		}
	}

	@Override
	public void backspaceKeyPressed() {
		setKeyPressWaiting(true);
		if (acceptsInput()) {
			chopInput();
		}
	}

	private boolean acceptsInput() {
		return gameState.playerControl() || isWaitingForDeblockingKeyPress();
	}

	@Override
	public void changeDirection(int direction) {
		setKeyPressWaiting(true);
		if (acceptsInput()) {
			if (direction != -1) {
				IAnimatedObject ego = gameState.getAnimatedObject(0);
				if (ego != null) {
					if (ego.isMoving()) {
						if (ego.getDirection() == direction) {
							ego.setMoving(false);
						}
					} else {
						ego.setMoving(true);
					}
					gameState
							.setVar(GameEngine.VAR_EGO_DIRECTION, direction);
				}
			}
		}
	}

}
