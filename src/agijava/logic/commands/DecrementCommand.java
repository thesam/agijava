package agijava.logic.commands;

import agijava.main.IGameState;

public class DecrementCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int varNo = args.get(0);
		int value = gameState.getVar(varNo);
		value--;
		gameState.setVar(varNo, value);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}

}
