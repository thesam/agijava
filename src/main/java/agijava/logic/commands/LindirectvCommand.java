package agijava.logic.commands;

import agijava.main.GameState;

public class LindirectvCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int varNo = args.get(0);
		int sourceVarNo = args.get(1);
		int destinationVarNo = gameState.getVar(varNo);
		int value = gameState.getVar(sourceVarNo);
		gameState.vars[destinationVarNo] = value;
		
	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}


}
