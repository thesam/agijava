package agijava.logic.commands;

import agijava.main.GameState;

public class SetCursorCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int messageNo = args.get(0);
				String message = gameState.getCurrentLogic().getMessage(messageNo);
				gameState.setCursorChar(message.charAt(0));
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}

}
