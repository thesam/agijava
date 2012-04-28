package agijava.logic.commands;

import agijava.main.IGameState;

public class StatusLineOnCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		gameState.setStatusLineOn(true);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 0;
	}

}
