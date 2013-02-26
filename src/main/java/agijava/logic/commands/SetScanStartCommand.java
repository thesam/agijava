package agijava.logic.commands;

import agijava.main.GameState;

public class SetScanStartCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int offset = gameState.currentLogic.getCurrentOffset();
		gameState.scanStarts.put(gameState.currentLogic.getEntryNumber(),offset);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 0;
	}

}
