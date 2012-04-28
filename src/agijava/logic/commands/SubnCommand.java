package agijava.logic.commands;

import agijava.main.IGameState;

public class SubnCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		// TODO Auto-generated method stub
		int varNo = args.get(0);
		int value = args.get(1);
		int oldValue = gameState.getVar(varNo);
		int newValue = oldValue - value;
		gameState.setVar(varNo, newValue);

	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}

}
