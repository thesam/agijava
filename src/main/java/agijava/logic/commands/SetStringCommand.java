package agijava.logic.commands;

import agijava.main.GameState;

public class SetStringCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int stringNo = args.get(0);
		int messageNo = args.get(1);
		String message = gameState.currentLogic.getMessage(messageNo);
		gameState.strings[stringNo] = message;
	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}

}
