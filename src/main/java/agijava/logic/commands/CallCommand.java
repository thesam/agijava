package agijava.logic.commands;

import agijava.main.GameState;

public class CallCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		gameState.callNewLogic(args.get(0));

	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}

}
