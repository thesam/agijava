package agijava.logic.commands;

import agijava.main.GameState;

public class ReturnCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		returnToCallingLogic(gameState);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 0;
	}

	private void returnToCallingLogic(GameState gameState) {
		if (gameState.logicStack.isEmpty()) {
			gameState.currentLogic = null;
		} else {
			gameState.currentLogic = gameState.logicStack.pop();
		}
	}
	
}
