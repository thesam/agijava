package agijava.logic.commands;

import agijava.main.GameState;

public class LindirectnCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int varNo = args.get(0);
		int value = args.get(1);
		int destinationVarNo = gameState.getVar(varNo);
		gameState.setVar(destinationVarNo, value);
		
	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}


}
