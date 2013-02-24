package agijava.logic.commands;

import agijava.main.GameState;

public class ResetvCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int varNo = args.get(0);
		int flagNo = gameState.getVar(varNo);
		gameState.flags[flagNo] = false;
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}

}
