package agijava.main.impl;

import java.awt.event.KeyEvent;

import agijava.gui.IGameGui;
import agijava.main.IAnimatedObject;
import agijava.main.IGameState;
import agijava.main.IInputListener;

public class InputListener implements IInputListener {

	private IGameGui gui;
	private boolean keyPressWaiting;
	private IGameState gameState;
	private boolean waitingForDeblockingKeyPress;
	private boolean inputIsWaiting;
	private String input;

	public InputListener(IGameGui gui, IGameState gameState) {
		this.gui = gui;
		this.gameState = gameState;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char rawInput = e.getKeyChar();
		String clean = cleanInputChar(rawInput);
		gui.appendInput(clean);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		setKeyPressWaiting(true);
		if (!gameState.playerControl() && !waitingForDeblockingKeyPress) {
			return;
		}
		int keyCode = e.getKeyCode();
		int newDirection = parseKeyCode(keyCode);
		if (newDirection != -1) {
			IAnimatedObject ego = gameState.getAnimatedObject(0);
			if (ego != null) {
				if (ego.isMoving()) {
					if (ego.getDirection() == newDirection) {
						ego.setMoving(false);
					}
				} else {
					ego.setMoving(true);
				}
				gameState.setVar(GameEngine.VAR_EGO_DIRECTION, newDirection);
			}
		}
	}

	private int parseKeyCode(int keyCode) {
		int newDirection = -1;
		switch (keyCode) {
		case KeyEvent.VK_LEFT:
			newDirection = Directions.WEST;
			break;
		case KeyEvent.VK_UP:
			newDirection = Directions.NORTH;
			break;
		case KeyEvent.VK_RIGHT:
			newDirection = Directions.EAST;
			break;
		case KeyEvent.VK_DOWN:
			newDirection = Directions.SOUTH;
			break;
		case KeyEvent.VK_ENTER:
			this.waitingForDeblockingKeyPress = false;
			String textInput = gui.getCurrentInputLine();
			if (!textInput.isEmpty()) {
				setInputIsWaiting(true);
				input = textInput;
				gui.clearCurrentInputLine();
			}
			break;
		case KeyEvent.VK_BACK_SPACE:
			gui.chopInput();
			break;
		default:
			// String textInput = JOptionPane.showInputDialog("Input");
		}
		return newDirection;
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	private String cleanInputChar(char rawInput) {
		String foo = "" + rawInput;
		return foo.replaceAll("[^A-Za-z, ]", "");
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

}
