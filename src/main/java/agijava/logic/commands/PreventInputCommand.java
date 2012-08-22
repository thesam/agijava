package agijava.logic.commands;

import agijava.main.IGameState;

public class PreventInputCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		gameState.setAcceptInput(false);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 0;
	}

}
