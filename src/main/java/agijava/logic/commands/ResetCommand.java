package agijava.logic.commands;

import agijava.main.GameState;

public class ResetCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int flagNo = args.get(0);
		gameState.reset(flagNo);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}


}
