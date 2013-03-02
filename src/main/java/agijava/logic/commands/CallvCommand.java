package agijava.logic.commands;

import agijava.main.GameState;

public class CallvCommand extends CallCommand {

	@Override
	protected int getRoomNumber(GameState gameState) {
		int varNo = args.get(0);
		int roomNo = gameState.getVar(varNo);
		return roomNo;
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}

}
