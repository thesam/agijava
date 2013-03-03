package agijava.logic.commands;

import agijava.main.GameState;

public class MoveObjvCommand extends MoveObjCommand {

	@Override
	protected int getX(GameState gameState) {
		int varx = args.get(1);
		return gameState.vars[varx];
	}

	@Override
	protected int getY(GameState gameState) {
		int vary = args.get(2);
		return gameState.vars[vary];
	}
}
