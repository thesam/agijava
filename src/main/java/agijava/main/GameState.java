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
	public boolean isGameExited; // TODO: Default value
	public boolean haveKey;
	public final boolean[] flags;
	public String currentMessage;
	public Map<Integer, AnimatedObject> animatedObjects;
	public final Stack<Logic> logicStack;
	public List<AnimatedObject> displayedBackgroundViews;
	public int newRoomNumber;
	public final PictureRepository pictureRepository;
	public final InventoryObjects inventoryObjects;
	public boolean newRoomWaiting;
	public boolean statusLineOn;
	public boolean messageShown;
	public final int[] vars;
	public final String[] strings;
	public char cursorChar;
	public Logic currentLogic;
	public Picture currentPicture;
	public Picture bufferPicture;
	public List<Text> displayedTexts;
	public final Map<Integer, Integer> scanStarts;
	public List<AnimatedObject> bufferBackgroundViews;
	public String latestInput = "";
	public boolean acceptInput;

	public final LogicRepository logicRepository;

	public final WordsTok wordsTok;

	public GameState(LogicRepository logicRepository,
			PictureRepository pictureRepository, ViewRepository viewRepository,
			WordsTok wordsTok, InventoryObjects inventoryObjects) {
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

	public boolean controller(int controllerNo) {
		// TODO Auto-generated method stub
		return false;
	}

}
