package agijava.logic.commands;

import agijava.main.GameState;

public class DecrementCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int varNo = args.get(0);
		int value = gameState.getVar(varNo);
		value--;
		gameState.vars[varNo] = value;
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}

}
