package agijava.logic.commands;

import agijava.main.IGameState;

public class ReturnCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		gameState.returnToCallingLogic();
	}

	@Override
	public int getArgsSizeInBytes() {
		return 0;
	}

}
