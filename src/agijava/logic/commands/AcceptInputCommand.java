package agijava.logic.commands;

import agijava.main.IGameState;

public class AcceptInputCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		gameState.setAcceptInput(true);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 0;
	}

}
