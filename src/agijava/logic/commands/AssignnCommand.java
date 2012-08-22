package agijava.logic.commands;

import agijava.main.IGameState;

public class AssignnCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int varNo = args.get(0);
		int newValue = args.get(1);
		gameState.setVar(varNo,newValue);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}

}
