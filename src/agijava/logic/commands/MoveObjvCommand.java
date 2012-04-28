package agijava.logic.commands;

import agijava.main.IGameState;

public class MoveObjvCommand extends MoveObjCommand {

	@Override
	protected int getX(IGameState gameState) {
		int varx = args.get(1);
		return gameState.getVar(varx);
	}
	
	@Override
		protected int getY(IGameState gameState) {
			int vary = args.get(2);
			return gameState.getVar(vary);
		}
}
