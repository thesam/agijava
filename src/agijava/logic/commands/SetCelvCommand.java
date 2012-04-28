package agijava.logic.commands;

import agijava.main.IGameState;

public class SetCelvCommand extends SetCelCommand {

	@Override
	protected int getCelNo(IGameState gameState) {
		int varNo = args.get(1);
		int celNo = gameState.getVar(varNo);
		return celNo;
	}

}
