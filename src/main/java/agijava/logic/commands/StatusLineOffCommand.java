package agijava.logic.commands;

import agijava.main.GameState;

public class StatusLineOffCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		gameState.setStatusLineOn(false);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 0;
	}


}
