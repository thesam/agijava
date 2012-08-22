package agijava.logic.commands;

import agijava.main.IGameState;

public class CallCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		gameState.callNewLogic(args.get(0));

	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}

}
