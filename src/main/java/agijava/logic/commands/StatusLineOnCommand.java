package agijava.logic.commands;

import agijava.main.GameState;

public class StatusLineOnCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		gameState.setStatusLineOn(true);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 0;
	}

}
