package agijava.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import agijava.logic.Logic;
import agijava.picture.Picture;

public class GameState {

	private LogicRepository logicRepository;
	private PictureRepository pictureRepository;
	private ViewRepository viewRepository;
	private WordsTok wordsTok;
	private InventoryObjects inventoryObjects;
	
	// State
	private boolean isGameExited;
	private boolean haveKey;
	private boolean newRoomWaiting;
	private boolean statusLineOn;
	private boolean playerControl;
	private boolean messageShown;
	private int horizon;
	
	private boolean[] flags;
	private int[] vars;
	private String[] strings;

	private String currentMessage;
	private char cursorChar;

	private Logic currentLogic;
	private Picture currentPicture;
	private Picture bufferPicture;
	private Map<Integer, AnimatedObject> animatedObjects;
	private Stack<Logic> logicStack;
	private List<Text> displayedTexts;

	private Map<Integer, Integer> scanStarts;
	private List<AnimatedObject> displayedBackgroundViews;
	private List<AnimatedObject> bufferBackgroundViews;
	private List<Integer> latestSaidWords;
	
	private int newRoomNumber;
	private String latestInput = "";
	private boolean acceptInput;

	public GameState(LogicRepository logicRepository,
			PictureRepository pictureRepository,
			ViewRepository viewRepository, WordsTok wordsTok,
			InventoryObjects inventoryObjects) {
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
		this.logicStack = new Stack<Logic>();
		this.flags = new boolean[256];
		this.vars = new int[256];
		this.strings = new String[256];
		
		this.acceptInput = true;

	}

	public boolean isGameExited() {
		return isGameExited;
	}

	public int getVar(int var) {
		return vars[var];
	}

	public void setVar(int varNo, int newValue) {
		vars[varNo] = newValue;
	}

	public boolean getFlag(int flagNo) {
		return flags[flagNo];
	}

	public void setFlag(int flagNo) {
		flags[flagNo] = true;
	}
	
	public void reset(int flagNo) {
		flags[flagNo] = false;
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

	public void jumpForward(int blockSize) {
		currentLogic.increaseOffset(blockSize);
	}

	public int getLogicOffset() {
		return currentLogic.getCurrentOffset();
	}

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

	public void returnToCallingLogic() {
		if (logicStack.isEmpty()) {
			currentLogic = null;
		} else {
			currentLogic = logicStack.pop();
		}
	}

	public void clearLogicStack() {
		logicStack.clear();
	}
	
	public Logic getCurrentLogic() {
		return currentLogic;
	}

	public void setCurrentLogic(Logic object) {
		currentLogic = object;
	}
	
	public boolean executeNextCommand() {
		if (currentLogic == null) {
			return false;
		} else {
			LogicCommand nextCommand = currentLogic.getNextCommand();
			nextCommand.execute(this);
			return true;
		}
	}





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

	public AnimatedObject getAnimatedObject(int objNo) {
		return animatedObjects.get(objNo);
	}
	
	public Collection<AnimatedObject> getAnimatedObjects() {
		return animatedObjects.values();
	}
	
	public void clearAnimatedObjects() {
		animatedObjects.clear();
	}

	public void setHorizon(int newHorizon) {
		this.horizon = newHorizon;
	}

	public void showPictureFromBuffer() {
		currentPicture = bufferPicture;
		displayedBackgroundViews = bufferBackgroundViews;
	}
	
	public List<AnimatedObject> getBackgroundViews() {
		return displayedBackgroundViews;
	}
	
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

	public void clearBackgroundViews() {
		bufferBackgroundViews.clear();
	}


	public void setPictureInBuffer(int picNo) {
		bufferPicture = pictureRepository.getPicture(picNo);
	}

	public Picture getCurrentPicture() {
		return currentPicture;
	}



	public void addText(int row, int col, String message) {
		displayedTexts.add(new Text(row, col, message));

	}

	public List<Text> getDisplayedTexts() {
		return displayedTexts;
	}
	
	public void clearDisplayedTexts() {
		displayedTexts.clear();
	}

	public void setString(int stringNo, String message) {
		this.strings[stringNo] = message;
	}
	
	public String getString(int i) {
		return strings[i];
	}

	private int getScanStart(Integer logicNo) {
		if (scanStarts.containsKey(logicNo)) {
			return scanStarts.get(logicNo);
		} else {
			return 0;
		}

	}
	
	public void setScanStart(int entryNumber, int offset) {
		scanStarts.put(entryNumber, offset);
	}

	public void resetScanStart(int entryNumber) {
		scanStarts.remove(entryNumber);
	}

	public void setHaveKey(boolean b) {
		this.haveKey = b;
	}

	public void setNewRoomWaiting(boolean b) {
		this.newRoomWaiting = b;

	}

	public void setNewRoomNumber(int newRoomNumber) {
		this.newRoomNumber = newRoomNumber;
	}

	public boolean isNewRoomWaiting() {
		return newRoomWaiting;
	}

	public int getNewRoomNumber() {
		return this.newRoomNumber;
	}

	public void setCursorChar(char charAt) {
		this.cursorChar = charAt;
	}

	public void setStatusLineOn(boolean b) {
		this.statusLineOn = b;
	}

	public boolean isStatusLineOn() {
		return statusLineOn;
	}

	public boolean playerControl() {
		return playerControl;
	}

	public void setPlayerControl(boolean b) {
		playerControl = b;
	}

	public List<Integer> getLatestSaidWords() {
		if (latestSaidWords != null) {
			return latestSaidWords;
		}
		return new ArrayList<Integer>();
	}

	public void setLastSaidWords(List<Integer> saidWords) {
		this.latestSaidWords = saidWords;
	}

	public void clearLastSaidWords() {
		if (latestSaidWords != null) {
			latestSaidWords.clear();
		}
	}

	public void setLatestInput(String textInput) {
		this.latestInput = textInput;
	}

	public String getLatestInput() {
		return latestInput;
	}

	public int getNumberForWord(String string) {
		return wordsTok.getNumberFor(string);
	}

	public InventoryObject getInventoryObject(int inventoryObjectNo) {
		return inventoryObjects.get(inventoryObjectNo);
	}

	public void showMessage(String message) {
		messageShown = true;
		currentMessage = message;
	}

	public String getCurrentMessage() {
		return currentMessage;
	}

	public boolean isMessageShowing() {
		return messageShown;
	}

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

	public void setAcceptInput(boolean b) {
		this.acceptInput = b;
	}

}
