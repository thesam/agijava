package agijava.logic.commands;

import agijava.main.IGameState;

public class ResetvCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int varNo = args.get(0);
		int flagNo = gameState.getVar(varNo);
		gameState.reset(flagNo);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}

}
