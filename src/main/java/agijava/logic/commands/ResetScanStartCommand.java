package agijava.logic.commands;

import agijava.main.GameState;

public class ResetScanStartCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		gameState.resetScanStart(gameState.getCurrentLogic().getEntryNumber());
	}

	@Override
	public int getArgsSizeInBytes() {
		return 0;
	}

}
