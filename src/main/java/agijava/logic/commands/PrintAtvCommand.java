package agijava.logic.commands;

import agijava.main.IGameState;

public class PrintAtvCommand extends PrintAtCommand {

	@Override
	protected int getMessageNo(IGameState gameState) {
		int varNo = args.get(0);
		int messageNo = gameState.getVar(varNo);
		return messageNo;
	}
	
}
