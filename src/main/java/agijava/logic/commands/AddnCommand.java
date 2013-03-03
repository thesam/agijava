package agijava.logic.commands;

import agijava.main.GameState;

public class AddnCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int varNo = args.get(0);
		int valueToAdd = args.get(1);
		int oldValue = gameState.getVar(varNo);
		gameState.vars[varNo] = oldValue + valueToAdd;
	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}

}
