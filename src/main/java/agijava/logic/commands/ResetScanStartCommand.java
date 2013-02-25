package agijava.logic.commands;

import agijava.main.GameState;

public class ResetScanStartCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		gameState.resetScanStart(gameState.currentLogic.getEntryNumber());
	}

	@Override
	public int getArgsSizeInBytes() {
		return 0;
	}

}
