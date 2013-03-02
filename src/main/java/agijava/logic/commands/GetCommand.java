package agijava.logic.commands;


import agijava.main.GameEngine;
import agijava.main.GameState;
import agijava.main.InventoryObject;

public class GetCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int inventoryObjectNo = args.get(0);
		InventoryObject object = gameState.inventoryObjects.get(inventoryObjectNo);
		object.setRoomNumber(GameEngine.PLAYER_INVENTORY_ROOM);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}


}
