package agijava.logic.commands;


import agijava.main.IGameState;

public class PrintvCommand extends PrintCommand {
	@Override
	protected int getMessageNo(IGameState gameState) {
		int varNo = args.get(0);
		return gameState.getVar(varNo);
	}
}
