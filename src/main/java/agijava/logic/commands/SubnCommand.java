package agijava.logic.commands;

import agijava.main.GameState;

public class SubnCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		// TODO Auto-generated method stub
		int varNo = args.get(0);
		int value = args.get(1);
		int oldValue = gameState.getVar(varNo);
		int newValue = oldValue - value;
		gameState.vars[varNo] = newValue;

	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}

}
