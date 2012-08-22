package agijava.main;

import java.util.Collection;
import java.util.List;

import agijava.logic.ILogic;
import agijava.main.impl.AnimatedObject;
import agijava.main.impl.InventoryObject;
import agijava.main.impl.Text;
import agijava.picture.IPicture;

public interface IGameState {

	int getVar(int var);

	boolean controller(int controllerNo);

	boolean getFlag(int flagNo);

	boolean has(int itemNo);

	boolean haveKey();

	void jumpForward(int blockSize);

	int getLogicOffset();

	void setVar(int varNo, int newValue);

	void callNewLogic(Integer integer);

	boolean isGameExited();

	boolean executeNextCommand();

	void returnToCallingLogic();

	void setFlag(int flagNo);

	void clearLogicStack();

	void reset(int flagNo);

	void addAnimatedObject(int objNo);

	IAnimatedObject getAnimatedObject(int objNo);

	List<Integer> getLatestSaidWords();

	void setHorizon(int newHorizon);

	void showPictureFromBuffer();

	void setPictureInBuffer(int picNo);

	IPicture getCurrentPicture();

	Collection<AnimatedObject> getAnimatedObjects();

	ILogic getCurrentLogic();

	void addText(int row, int col, String message);

	List<Text> getDisplayedTexts();

	void setString(int stringNo, String message);

	void setScanStart(int entryNumber, int offset);

	void resetScanStart(int entryNumber);

	void setHaveKey(boolean b);

	void setNewRoomWaiting(boolean b);

	void setNewRoomNumber(int newRoomNumber);

	boolean isNewRoomWaiting();

	int getNewRoomNumber();

	void setCurrentLogic(ILogic object);

	void addBackgroundViewToBuffer(int viewNo, int loopNo, int celNo, int x, int y,
			int priority, int margin);

	List<AnimatedObject> getBackgroundViews();

	void clearBackgroundViews();

	void clearDisplayedTexts();

	void setCursorChar(char charAt);

	void setStatusLineOn(boolean b);

	boolean isStatusLineOn();

	boolean playerControl();

	void setPlayerControl(boolean b);

	void clearAnimatedObjects();

	void setLastSaidWords(List<Integer> saidWords);

	void clearLastSaidWords();

	void setLatestInput(String textInput);

	String getLatestInput();

	int getNumberForWord(String string);

	InventoryObject getInventoryObject(int inventoryObjectNo);

	void showMessage(String message);

	String getCurrentMessage();

	boolean isMessageShowing();

	void clearMessage();

	String getString(int parseInt);

	void setAcceptInput(boolean b);

}
