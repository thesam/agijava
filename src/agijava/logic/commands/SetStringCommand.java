package agijava.logic.commands;

import agijava.main.IGameState;

public class SetStringCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int stringNo = args.get(0);
		int messageNo = args.get(1);
		String message = gameState.getCurrentLogic().getMessage(messageNo);
		gameState.setString(stringNo,message);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}

}
