package agijava.logic.commands;

import agijava.main.IGameState;

public class AddnCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int varNo = args.get(0);
		int valueToAdd = args.get(1);
		int oldValue = gameState.getVar(varNo);
		gameState.setVar(varNo, oldValue + valueToAdd);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}

}
