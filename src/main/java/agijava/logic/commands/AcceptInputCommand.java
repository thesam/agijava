package agijava.logic.commands;

import agijava.main.GameState;

public class AcceptInputCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		gameState.setAcceptInput(true);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 0;
	}

}
