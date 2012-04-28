package agijava.main;

import java.awt.event.KeyListener;

public interface IInputListener extends KeyListener {

	String getLatestInput();

	boolean isInputWaiting();

	boolean isKeyPressWaiting();

	void setKeyPressWaiting(boolean b);

	void setInputIsWaiting(boolean b);

	void setWaitingForDeblockingKeyPress(boolean b);

	boolean isWaitingForDeblockingKeyPress();

}
