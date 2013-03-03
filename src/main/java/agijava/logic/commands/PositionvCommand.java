package agijava.logic.commands;


import agijava.main.GameState;

public class PositionvCommand extends PositionCommand {
	
	@Override
	protected int getY(GameState gameState) {
		int vary = args.get(2);
		return gameState.vars[vary];
	}

	@Override
	protected int getX(GameState gameState) {
		int varx = args.get(1);
		return gameState.vars[varx];
	}

}
