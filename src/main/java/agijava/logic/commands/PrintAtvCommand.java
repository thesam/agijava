package agijava.logic.commands;

import agijava.main.GameState;

public class PrintAtvCommand extends PrintAtCommand {

	@Override
	protected int getMessageNo(GameState gameState) {
		int varNo = args.get(0);
		int messageNo = gameState.getVar(varNo);
		return messageNo;
	}
	
}
