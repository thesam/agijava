package agijava.logic.commands;


import agijava.main.GameState;

public class PrintvCommand extends PrintCommand {
	@Override
	protected int getMessageNo(GameState gameState) {
		int varNo = args.get(0);
		return gameState.vars[varNo];
	}
}
