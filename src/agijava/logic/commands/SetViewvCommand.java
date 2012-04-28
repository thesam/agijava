package agijava.logic.commands;

import agijava.main.IGameState;

public class SetViewvCommand extends SetViewCommand {

	@Override
	protected int getViewNo(IGameState gameState) {
		int varNo = args.get(1);
		return gameState.getVar(varNo);
	}

}
