package agijava.main;

import agijava.gui.GameGui;
import agijava.logic.commands.CallCommand;

import java.util.ArrayList;
import java.util.Collection;

public class RunningGame {

	private GuiDrawer drawer;
	private ObjectUpdater updater;
	private GameState gameState;

	public RunningGame(GuiDrawer drawer, ObjectUpdater updater,
			GameState gameState) {
		this.drawer = drawer;
		this.updater = updater;
		this.gameState = gameState;
	}

	public void refreshGui() {
		drawer.clearScreen();
		drawer.drawCurrentPictureToGui();
		drawer.drawBackgroundViews();
		drawer.drawAnimatedObjects();
		drawer.drawStatusLine();
		drawer.drawCurrentInputLine();
		drawer.drawDisplayedTexts();
		drawer.drawCurrentMessage();
		drawer.updateToScreen();
	}

	public void updateAnimatedObjects() {
		Collection<AnimatedObject> animatedObjects = gameState
				.animatedObjects.values();
		for (AnimatedObject animatedObject : animatedObjects) {
			if (animatedObject.shouldBeUpdated()) {
				updater.updateLoopAndCel(animatedObject);
				updater.updateSingleLoop(animatedObject);
				if (animatedObject.isMoving()) {
					updater.updatePosition(animatedObject);
					updater.stopIfDestinationIsReached(animatedObject);
				}
			}
		}
	}

    public boolean isExited() {
        return gameState.isGameExited;
    }

    public void initGameState() {
        gameState.flags[GameEngine.FLAG_NEW_ROOM] = true;
        reloadFirstLogic();
    }

    public boolean executeNextCommand() {
        if (gameState.currentLogic == null) {
            return false;
        } else {
            LogicCommand nextCommand = gameState.currentLogic.getNextCommand();
            nextCommand.execute(gameState);
            return true;
        }
    }

    public void reloadFirstLogic() {
        callNewLogic(gameState,0);
    }



    private void callNewLogic(GameState gameState2, int i) {
        CallCommand callCommand = new CallCommand();
        ArrayList<Integer> roomNumber = new ArrayList<Integer>();
        roomNumber.add(0);
        callCommand.setArgs(roomNumber);
        callCommand.execute(gameState2);
    }

    public void resetInputState() {
        gameState.latestInput = "";
        gameState.flags[GameEngine.FLAG_TEXT_ENTERED] = false;
        gameState.flags[GameEngine.FLAG_TEXT_ACCEPTED] = false;
    }

    public void handleNewRoom() {
        if (gameState.newRoomWaiting) {
            gameState.newRoomWaiting = false;
            // Commands stop.update and unanimate are issued to all objects;
            AnimatedObject ego = gameState.animatedObjects.get(0);
            if (ego != null) {
                int egoView = ego.getView().getNumber();
                gameState.vars[GameEngine.VAR_EGO_VIEW_NO] = egoView;
            }
            gameState.animatedObjects.clear();
            // All resources except Logic(0) are discarded;
            // Command player.control is issued;
            // unblock command is issued;
            // set.horizon(36) command is issued;
            // v1 is assigned the value of v0; v0 is assigned n (or the value of
            // vn when the command is new.room.v); v4 is assigned 0; v5 is
            // assigned 0; v16 is assigned the ID number of the VIEW resource
            // that was associated with Ego (the player character).
            gameState.vars[GameEngine.VAR_PREVIOUS_ROOM_NO] = gameState.vars[GameEngine.VAR_ROOM_NO];
            gameState.vars[GameEngine.VAR_ROOM_NO] = gameState.newRoomNumber;
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
            gameState.flags[GameEngine.FLAG_NEW_ROOM] = true;
            // Clear keyboard input buffer and return to the main AGI loop.
        } else {
            gameState.flags[GameEngine.FLAG_NEW_ROOM] = false;
        }
    }

    public void updateEgoDirection() {
        AnimatedObject ego = gameState.animatedObjects.get(0);
        if (ego != null) {
            ego.setDirection(gameState.vars[GameEngine.VAR_EGO_DIRECTION]);
        }
    }

    public void waitForUserToCloseMessage(GameGui gui) {
        if (gameState.messageShown) {
            waitForKeyPress(gui);
            gameState.messageShown = false;
        }
    }

    private void waitForKeyPress(GameGui gui) {
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
