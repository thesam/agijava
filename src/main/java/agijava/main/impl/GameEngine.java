package agijava.main.impl;

import java.io.IOException;

import agijava.gui.impl.GameGui;
import agijava.main.IGameState;

public class GameEngine {

	// Vars
	public final static int VAR_ROOM_NO = 0;
	private static final int VAR_PREVIOUS_ROOM_NO = 1;
	public static final int VAR_EGO_DIRECTION = 6;
	private static final int VAR_EGO_VIEW_NO = 16;

	// Flags
	public static final int FLAG_TEXT_ENTERED = 2;
	public static final int FLAG_TEXT_ACCEPTED = 4;
	public static final int FLAG_NEW_ROOM = 5;
	public static final int FLAG_BLOCK_WRITING_TO_SCRIPT_BUFFER = 7;
	public static final int FLAG_NON_BLOCKING_WINDOWS = 15;

	// Other
	public static final int PLAYER_INVENTORY_ROOM = 255;

	private IGameState gameState;

	private RunningGame runningGame;

	@SuppressWarnings("unused")
	private long lastGuiUpdate = System.currentTimeMillis();
	@SuppressWarnings("unused")
	private long now;
	private final GameGui gui;

	public GameEngine(IGameState gameState, RunningGame runningGame,
			MovementCalculator calculator, GameGui gui) {
		this.gameState = gameState;
		this.runningGame = runningGame;
		this.gui = gui;
	}

	public void run() throws IOException {
		initGameState();
		while (!gameState.isGameExited()) {
			tick();
		}
		// System.out.println(logic.replaceAll(";", ";\n"));
	}

	private void initGameState() {
		gameState.setFlag(FLAG_NEW_ROOM);
		gameState.callNewLogic(0);
	}

	private void tick() {
		boolean couldExecuteCommand = gameState.executeNextCommand();
		if (!couldExecuteCommand) {
			resetInputState();
			gui.handleKeyboardInput();
			handleNewRoom();
			reloadFirstLogic();
			updateEgoDirection();
			runningGame.updateAnimatedObjects();
//			if (enoughTimeHasPassedSinceLastGuiUpdate()) {
//				lastGuiUpdate = now;
				runningGame.refreshGui();
//			}
//			try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			waitForUserToCloseMessage();
		}
	}

//	private boolean enoughTimeHasPassedSinceLastGuiUpdate() {
//		now = System.currentTimeMillis();
//		long delta = now - lastGuiUpdate;
//		if (delta > 40) {
//			return true;
//		} else {
//			return false;
//		}
//	}

	private void resetInputState() {
		gameState.setLatestInput("");
		gameState.reset(GameEngine.FLAG_TEXT_ENTERED);
		gameState.reset(GameEngine.FLAG_TEXT_ACCEPTED);
	}

	private void handleNewRoom() {
		if (gameState.isNewRoomWaiting()) {
			gameState.setNewRoomWaiting(false);
			// Commands stop.update and unanimate are issued to all objects;
			AnimatedObject ego = gameState.getAnimatedObject(0);
			if (ego != null) {
				int egoView = ego.getView().getNumber();
				gameState.setVar(GameEngine.VAR_EGO_VIEW_NO, egoView);
			}
			gameState.clearAnimatedObjects();
			// All resources except Logic(0) are discarded;
			// Command player.control is issued;
			// unblock command is issued;
			// set.horizon(36) command is issued;
			// v1 is assigned the value of v0; v0 is assigned n (or the value of
			// vn when the command is new.room.v); v4 is assigned 0; v5 is
			// assigned 0; v16 is assigned the ID number of the VIEW resource
			// that was associated with Ego (the player character).
			gameState.setVar(GameEngine.VAR_PREVIOUS_ROOM_NO,
					gameState.getVar(GameEngine.VAR_ROOM_NO));
			gameState.setVar(GameEngine.VAR_ROOM_NO,
					gameState.getNewRoomNumber());
			// Logic(i) resource is loaded where i is the value of v0 !
			// Set Ego coordinates according to v2:
			// if Ego touched the bottom edge, put it on the horizon;
			// if Ego touched the top edge, put it on the bottom edge of the
			// screen;
			// if Ego touched the right edge, put it at the left and vice versa.
			// v2 is assigned 0 (meaning Ego has not touched any edges).
			// f5 is set to 1 (meaning in the first interpreter cycle after the
			// new_room command all initialization parts of all logics loaded
			// and called from the initialization part of the new room's logic
			// will be called. In the subsequent cycle f5 is reset to 0 (see
			// section Interpreter work cycle and the source of the
			// "Thunderstorm" program. This is very important!).
			gameState.setFlag(GameEngine.FLAG_NEW_ROOM);
			// Clear keyboard input buffer and return to the main AGI loop.
		} else {
			gameState.reset(FLAG_NEW_ROOM);
		}
	}

	private void updateEgoDirection() {
		AnimatedObject ego = gameState.getAnimatedObject(0);
		if (ego != null) {
			ego.setDirection(gameState.getVar(VAR_EGO_DIRECTION));
		}
	}

	private void reloadFirstLogic() {
		gameState.callNewLogic(0);
	}

	private void waitForUserToCloseMessage() {
		if (gameState.isMessageShowing()) {
			waitForKeyPress();
			gameState.clearMessage();
		}
	}

	private void waitForKeyPress() {
		gui.setWaitingForDeblockingKeyPress(true);
		while (gui.isWaitingForDeblockingKeyPress()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
