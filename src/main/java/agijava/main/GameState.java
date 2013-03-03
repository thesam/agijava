package agijava.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import agijava.logic.Logic;
import agijava.picture.Picture;

public class GameState {
	
	public boolean isGameExited; // TODO: Default value

	public final LogicRepository logicRepository;
	public final PictureRepository pictureRepository;
	public final ViewRepository viewRepository;
	public final WordsTok wordsTok;
	public final InventoryObjects inventoryObjects;
	
	public final boolean[] flags = new boolean[256];
	public int[] vars = new int[256];
	public String[] strings = new String[256];
	
	public Map<Integer, AnimatedObject> animatedObjects = new HashMap<Integer, AnimatedObject>();
	public List<AnimatedObject> bufferBackgroundViews = new ArrayList<AnimatedObject>();
	public List<AnimatedObject> displayedBackgroundViews = new ArrayList<AnimatedObject>();
	
	public Picture currentPicture;
	public Picture bufferPicture;

	public Logic currentLogic;
	public Stack<Logic> logicStack = new Stack<Logic>();
	public Map<Integer, Integer> scanStarts = new HashMap<Integer, Integer>();
	
	public int horizon; // TODO: Default value
	public boolean playerControl; // TODO: Default value
	public boolean haveKey;
	public boolean statusLineOn;
	public char cursorChar;
	
	public int newRoomNumber;
	public boolean newRoomWaiting;

	public boolean messageShown;
	public String currentMessage;
	
	public List<Text> displayedTexts = new ArrayList<Text>();
	
	public String latestInput = "";
	public boolean acceptInput = true;

	public GameState(LogicRepository logicRepository,
			PictureRepository pictureRepository, ViewRepository viewRepository,
			WordsTok wordsTok, InventoryObjects inventoryObjects) {
		this.logicRepository = logicRepository;
		this.pictureRepository = pictureRepository;
		this.viewRepository = viewRepository;
		this.wordsTok = wordsTok;
		this.inventoryObjects = inventoryObjects;
	}

	public boolean controller(int controllerNo) {
		// TODO Auto-generated method stub
		return false;
	}

}
