package agijava.logic.commands;

import agijava.main.GameState;

public class ReturnCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		gameState.returnToCallingLogic();
	}

	@Override
	public int getArgsSizeInBytes() {
		return 0;
	}

}
