package agijava.main;

import java.io.IOException;
import java.util.ArrayList;
import agijava.gui.GameGui;
import agijava.logic.commands.CallCommand;

public class GameEngine {

	// Vars
	public final static int VAR_ROOM_NO = 0;
	public static final int VAR_PREVIOUS_ROOM_NO = 1;
	public static final int VAR_EGO_DIRECTION = 6;
	public static final int VAR_EGO_VIEW_NO = 16;

	// Flags
	public static final int FLAG_TEXT_ENTERED = 2;
	public static final int FLAG_TEXT_ACCEPTED = 4;
	public static final int FLAG_NEW_ROOM = 5;
	public static final int FLAG_BLOCK_WRITING_TO_SCRIPT_BUFFER = 7;
	public static final int FLAG_NON_BLOCKING_WINDOWS = 15;

	// Other
	public static final int PLAYER_INVENTORY_ROOM = 255;

	private RunningGame runningGame;

	@SuppressWarnings("unused")
	private long lastGuiUpdate = System.currentTimeMillis();
	@SuppressWarnings("unused")
	private long now;
	private final GameGui gui;

	public GameEngine(RunningGame runningGame, GameGui gui) {
		this.runningGame = runningGame;
		this.gui = gui;
	}

	public void run() throws IOException {
		runningGame.initGameState();
		while (!runningGame.isExited()) {
			tick();
		}
	}

    public void tick() {
        boolean couldExecuteCommand = runningGame.executeNextCommand();
        if (!couldExecuteCommand) {
            runningGame.resetInputState();
            gui.handleKeyboardInput();
            runningGame.handleNewRoom();
            runningGame.reloadFirstLogic();
            runningGame.updateEgoDirection();
            runningGame.updateAnimatedObjects();
            runningGame.refreshGui();
            runningGame.waitForUserToCloseMessage(gui);
        }
    }

}
