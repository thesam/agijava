package agijava.logic.commands;

import agijava.main.IGameState;

public class SetScanStartCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int offset = gameState.getCurrentLogic().getCurrentOffset();
		gameState.setScanStart(gameState.getCurrentLogic().getEntryNumber(),offset);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 0;
	}

}
