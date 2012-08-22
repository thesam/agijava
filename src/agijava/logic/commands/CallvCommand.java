package agijava.logic.commands;

import agijava.main.IGameState;

public class CallvCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int varNo = args.get(0);
		int roomNo = gameState.getVar(varNo);
		gameState.callNewLogic(roomNo);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}

}
