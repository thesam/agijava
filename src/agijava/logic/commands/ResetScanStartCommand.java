package agijava.logic.commands;

import agijava.main.IGameState;

public class ResetScanStartCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		gameState.resetScanStart(gameState.getCurrentLogic().getEntryNumber());
	}

	@Override
	public int getArgsSizeInBytes() {
		return 0;
	}

}
