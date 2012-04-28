package agijava.logic.commands;

import agijava.main.IGameState;

public class SetCursorCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int messageNo = args.get(0);
				String message = gameState.getCurrentLogic().getMessage(messageNo);
				gameState.setCursorChar(message.charAt(0));
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}

}
