package agijava.logic.commands;

import agijava.main.IGameState;

public class ResetCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int flagNo = args.get(0);
		gameState.reset(flagNo);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}


}
