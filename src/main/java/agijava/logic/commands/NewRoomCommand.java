package agijava.logic.commands;

import agijava.main.GameState;

public class NewRoomCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int newRoomNumber = args.get(0);
		
		gameState.setNewRoomWaiting(true);
		gameState.setNewRoomNumber(newRoomNumber);
		gameState.clearLogicStack();
		gameState.setCurrentLogic(null);
//		gameState.setVar(GameEngine.ROOM_NO, newRoomNumber);
//		gameState.setFlag(GameEngine.NEW_ROOM);
		
		/*
These commands do the following:
Commands stop.update and unanimate are issued to all objects;
All resources except Logic(0) are discarded;
Command player.control is issued;
unblock command is issued;
set.horizon(36) command is issued;
v1 is assigned the value of v0; v0 is assigned n (or the value of vn when the command is new.room.v); v4 is assigned 0; v5 is assigned 0; v16 is assigned the ID number of the VIEW resource that was associated with Ego (the player character).
Logic(i) resource is loaded where i is the value of v0 !
Set Ego coordinates according to v2:
if Ego touched the bottom edge, put it on the horizon;
if Ego touched the top edge, put it on the bottom edge of the screen;
if Ego touched the right edge, put it at the left and vice versa.
v2 is assigned 0 (meaning Ego has not touched any edges).
f5 is set to 1 (meaning in the first interpreter cycle after the new_room command all initialization parts of all logics loaded and called from the initialization part of the new room's logic will be called. In the subsequent cycle f5 is reset to 0 (see section Interpreter work cycle and the source of the "Thunderstorm" program. This is very important!).
Clear keyboard input buffer and return to the main AGI loop.
		 */
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}


}
