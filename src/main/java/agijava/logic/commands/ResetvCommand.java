package agijava.logic.commands;

import agijava.main.GameState;

public class ResetvCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int varNo = args.get(0);
		int flagNo = gameState.getVar(varNo);
		gameState.reset(flagNo);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}

}
