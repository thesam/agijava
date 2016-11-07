package agijava.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import agijava.logic.Logic;
import agijava.picture.Picture;

public class GameState {
	
	public int horizon; // TODO: Default value
	public boolean playerControl; // TODO: Default value

	public final ViewRepository viewRepository;
	// State
	public boolean isGameExited;  // TODO: Default value
	public boolean haveKey;
	public boolean[] flags;
	public String currentMessage;
	public Map<Integer, AnimatedObject> animatedObjects;
	public final Stack<Logic> logicStack;
	public List<AnimatedObject> displayedBackgroundViews;
	public int newRoomNumber;
	private final LogicRepository logicRepository;
	private final PictureRepository pictureRepository;
	private final WordsTok wordsTok;
	public final InventoryObjects inventoryObjects;
	
	public boolean newRoomWaiting;
	public boolean statusLineOn;
	public boolean messageShown;
	
	private int[] vars;
	private String[] strings;

	private char cursorChar;

	private Logic currentLogic;
	private Picture currentPicture;
	private Picture bufferPicture;
	public List<Text> displayedTexts;

	private Map<Integer, Integer> scanStarts;
	private List<AnimatedObject> bufferBackgroundViews;
	private List<Integer> latestSaidWords;
	
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

	public int getVar(int var) {
		return vars[var];
	}

	public void setVar(int varNo, int newValue) {
		vars[varNo] = newValue;
	}

	public boolean controller(int controllerNo) {
		// TODO Auto-generated method stub
		return false;
	}

	public void jumpForward(int blockSize) {
		currentLogic.increaseOffset(blockSize);
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
	
	public void showPictureFromBuffer() {
		currentPicture = bufferPicture;
		displayedBackgroundViews = bufferBackgroundViews;
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

	public void setNewRoomWaiting(boolean b) {
		this.newRoomWaiting = b;
	}

	public void setCursorChar(char charAt) {
		this.cursorChar = charAt;
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

	public boolean isMessageShowing() {
		return messageShown;
	}

	public List<AnimatedObject> getBufferedBackgroundViews() {
		return bufferBackgroundViews;
	}

	public void setAcceptInput(boolean b) {
		this.acceptInput = b;
	}
}
