package agijava.logic.commands;

import agijava.main.IGameState;

public class StatusLineOffCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		gameState.setStatusLineOn(false);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 0;
	}


}
