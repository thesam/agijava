package agijava.logic.commands;

import agijava.main.GameState;

public class PreventInputCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		gameState.acceptInput = false;
	}

	@Override
	public int getArgsSizeInBytes() {
		return 0;
	}

}
