package agijava.logic.commands;

import agijava.main.GameState;

public class SetCelvCommand extends SetCelCommand {

	@Override
	protected int getCelNo(GameState gameState) {
		int varNo = args.get(1);
		int celNo = gameState.getVar(varNo);
		return celNo;
	}

}
