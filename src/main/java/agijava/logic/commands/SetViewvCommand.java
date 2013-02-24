package agijava.logic.commands;

import agijava.main.GameState;

public class SetViewvCommand extends SetViewCommand {

	@Override
	protected int getViewNo(GameState gameState) {
		int varNo = args.get(1);
		return gameState.getVar(varNo);
	}

}
