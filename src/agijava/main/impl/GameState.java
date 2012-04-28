package agijava.main.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import agijava.logic.ILogic;
import agijava.main.IAnimatedObject;
import agijava.main.IGameState;
import agijava.main.IInventoryObjects;
import agijava.main.ILogicCommand;
import agijava.main.ILogicRepository;
import agijava.main.IPictureRepository;
import agijava.main.IViewRepository;
import agijava.main.IWordsTok;
import agijava.picture.IPicture;

public class GameState implements IGameState {

	private ILogicRepository logicRepository;
	private IPictureRepository pictureRepository;
	private IViewRepository viewRepository;
	private IWordsTok wordsTok;
	private IInventoryObjects inventoryObjects;
	
	private boolean isGameExited;
	private boolean haveKey;
	private boolean newRoomWaiting;
	private boolean statusLineOn;
	private boolean playerControl;
	private boolean messageShown;

	private String currentMessage;
	private char cursorChar;

	private ILogic currentLogic;
	private IPicture currentPicture;
	private IPicture bufferPicture;
	private Map<Integer, AnimatedObject> animatedObjects;
	private Stack<ILogic> logicStack;
	private List<Text> displayedTexts;
	private String[] strings;
	private Map<Integer, Integer> scanStarts;
	private List<AnimatedObject> displayedBackgroundViews;
	private List<AnimatedObject> bufferBackgroundViews;
	private List<Integer> latestSaidWords;
	
	private boolean[] flags;
	private int[] vars;
	private int horizon;
	private int newRoomNumber;
	private String latestInput = "";
	@SuppressWarnings("unused")
	private boolean acceptInput;
	

	public GameState(ILogicRepository logicRepository,
			IPictureRepository pictureRepository,
			IViewRepository viewRepository, IWordsTok wordsTok,
			IInventoryObjects inventoryObjects) {
		this.logicRepository = logicRepository;
		this.pictureRepository = pictureRepository;
		this.viewRepository = viewRepository;
		this.wordsTok = wordsTok;
		this.inventoryObjects = inventoryObjects;
		
		this.animatedObjects = new HashMap<Integer, AnimatedObject>();
		this.displayedTexts = new ArrayList<Text>();
		this.scanStarts = new HashMap<Integer, Integer>();
		this.displayedBackgroundViews = new ArrayList<AnimatedObject>();
		this.bufferBackgroundViews = new ArrayList<AnimatedObject>();
		this.logicStack = new Stack<ILogic>();
		this.flags = new boolean[256];
		this.vars = new int[256];
		this.strings = new String[256];
		
		this.acceptInput = true;

	}

	public boolean gameExited() {
		return isGameExited;
	}

	public boolean executeNextCommand() {
		if (currentLogic == null) {
			return false;
		} else {
			ILogicCommand nextCommand = currentLogic.getNextCommand();
			nextCommand.execute(this);
			return true;
		}
	}

	public void setLogic(ILogic logic) {
		this.currentLogic = logic;

	}

	public void jumpForward(int blockSize) {
		currentLogic.increaseOffset(blockSize);
	}

	public int getVar(int var) {
		return vars[var];
	}

	public boolean getFlag(int flagNo) {
		return flags[flagNo];
	}

	public boolean has(int itemNo) {
		return inventoryObjects.playerHas(itemNo);
	}

	public boolean controller(int controllerNo) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean haveKey() {
		return haveKey;
	}

	@Override
	public int getLogicOffset() {
		return currentLogic.getCurrentOffset();
	}

	@Override
	public void setVar(int varNo, int newValue) {
		vars[varNo] = newValue;
		// if (varNo == 31) {
		// System.err.println("Setting 31 to :" + newValue);
		// }
	}

	@Override
	public void callNewLogic(Integer logicNo) {
		if (currentLogic != null) {
			logicStack.push(currentLogic);
		}
		currentLogic = logicRepository.getLogic(logicNo);
		int scanStart = getScanStart(logicNo);
		if (scanStart > 0) {
			currentLogic.setOffset(scanStart);
		}

	}

	private int getScanStart(Integer logicNo) {
		if (scanStarts.containsKey(logicNo)) {
			return scanStarts.get(logicNo);
		} else {
			return 0;
		}

	}

	@Override
	public void returnToCallingLogic() {
		// System.err.println("RETURN");
		if (logicStack.isEmpty()) {
			currentLogic = null;
		} else {
			currentLogic = logicStack.pop();
		}
	}

	@Override
	public void setFlag(int flagNo) {
		flags[flagNo] = true;
	}

	@Override
	public void clearLogicStack() {
		logicStack.clear();
	}

	@Override
	public void reset(int flagNo) {
		flags[flagNo] = false;
	}

	@Override
	public void addAnimatedObject(int objNo) {
		AnimatedObject obj = null;
		obj = getAnimatedObjectWithNumber(objNo);
		obj.setShouldBeUpdated(true);
		animatedObjects.put(objNo, obj);

	}

	private AnimatedObject getAnimatedObjectWithNumber(int objNo) {
		AnimatedObject obj;
		if (animatedObjects.containsKey(objNo)) {
			obj = animatedObjects.get(objNo);
		} else {
			obj = new AnimatedObject(this.viewRepository);
		}
		if (objNo == 0) {
			obj.setEgo(true);
		}
		obj.setNumber(objNo);
		return obj;
	}

	@Override
	public IAnimatedObject getAnimatedObject(int objNo) {
		return animatedObjects.get(objNo);
	}

	@Override
	public void setHorizon(int newHorizon) {
		this.horizon = newHorizon;
	}

	@Override
	public void showPictureFromBuffer() {
		currentPicture = bufferPicture;
		displayedBackgroundViews = bufferBackgroundViews;

	}

	@Override
	public void setPictureInBuffer(int picNo) {
		bufferPicture = pictureRepository.getPicture(picNo);
	}

	@Override
	public IPicture getCurrentPicture() {
		return currentPicture;
	}

	@Override
	public Collection<AnimatedObject> getAnimatedObjects() {
		return animatedObjects.values();
	}

	@Override
	public ILogic getCurrentLogic() {
		return currentLogic;
	}

	@Override
	public void addText(int row, int col, String message) {
		displayedTexts.add(new Text(row, col, message));

	}

	@Override
	public List<Text> getDisplayedTexts() {
		return displayedTexts;
	}

	@Override
	public void setString(int stringNo, String message) {
		this.strings[stringNo] = message;
	}

	@Override
	public void setScanStart(int entryNumber, int offset) {
		scanStarts.put(entryNumber, offset);
	}

	@Override
	public void resetScanStart(int entryNumber) {
		scanStarts.remove(entryNumber);
	}

	@Override
	public void setHaveKey(boolean b) {
		this.haveKey = b;
	}

	@Override
	public void setNewRoomWaiting(boolean b) {
		this.newRoomWaiting = b;

	}

	@Override
	public void setNewRoomNumber(int newRoomNumber) {
		this.newRoomNumber = newRoomNumber;
	}

	@Override
	public boolean isNewRoomWaiting() {
		return newRoomWaiting;
	}

	@Override
	public int getNewRoomNumber() {
		return this.newRoomNumber;
	}

	@Override
	public void setCurrentLogic(ILogic object) {
		currentLogic = object;
	}

	@Override
	public void addBackgroundViewToBuffer(int viewNo, int loopNo, int celNo,
			int x, int y, int priority, int margin) {
		AnimatedObject animatedObject = new AnimatedObject(viewRepository);
		animatedObject.setView(viewNo);
		animatedObject.setCurrentViewLoop(loopNo);
		animatedObject.setCurrentViewCel(celNo);
		animatedObject.setPosition(new Position(x,y));
		animatedObject.setPriority(priority);
		bufferBackgroundViews.add(animatedObject);
	}

	@Override
	public List<AnimatedObject> getBackgroundViews() {
		return displayedBackgroundViews;
	}

	@Override
	public void clearBackgroundViews() {
		bufferBackgroundViews.clear();
	}

	@Override
	public void clearDisplayedTexts() {
		displayedTexts.clear();
	}

	@Override
	public void setCursorChar(char charAt) {
		this.cursorChar = charAt;
	}

	@Override
	public void setStatusLineOn(boolean b) {
		this.statusLineOn = b;
	}

	@Override
	public boolean isStatusLineOn() {
		return statusLineOn;
	}

	@Override
	public boolean playerControl() {
		return playerControl;
	}

	@Override
	public void setPlayerControl(boolean b) {
		playerControl = b;
	}

	@Override
	public void clearAnimatedObjects() {
		animatedObjects.clear();
	}

	@Override
	public List<Integer> getLatestSaidWords() {
		if (latestSaidWords != null) {
			return latestSaidWords;
		}
		return new ArrayList<Integer>();
	}

	@Override
	public void setLastSaidWords(List<Integer> saidWords) {
		this.latestSaidWords = saidWords;
	}

	@Override
	public void clearLastSaidWords() {
		if (latestSaidWords != null) {
			latestSaidWords.clear();
		}
	}

	@Override
	public void setLatestInput(String textInput) {
		this.latestInput = textInput;
	}

	@Override
	public String getLatestInput() {
		return latestInput;
	}

	@Override
	public int getNumberForWord(String string) {
		return wordsTok.getNumberFor(string);
	}

	@Override
	public InventoryObject getInventoryObject(int inventoryObjectNo) {
		return inventoryObjects.get(inventoryObjectNo);
	}

	@Override
	public void showMessage(String message) {
		messageShown = true;
		currentMessage = message;
	}

	@Override
	public String getCurrentMessage() {
		return currentMessage;
	}

	@Override
	public boolean messageShowing() {
		return messageShown;
	}

	@Override
	public void clearMessage() {
		messageShown = false;
	}

	public List<AnimatedObject> getBufferedBackgroundViews() {
		return bufferBackgroundViews;
	}

	public int getHorizon() {
		return horizon;
	}

	public char getCursorChar() {
		return cursorChar;
	}

	@Override
	public String getString(int i) {
		return strings[i];
	}

	@Override
	public void setAcceptInput(boolean b) {
		this.acceptInput = b;
	}

}
