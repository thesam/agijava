package agijava.logic.commands;

import agijava.main.GameState;

public class AssignnCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int varNo = args.get(0);
		int newValue = args.get(1);
		gameState.setVar(varNo,newValue);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}

}
