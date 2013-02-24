package agijava.logic.commands;

import agijava.main.GameState;

public class RindirectCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int destinationVarNo = args.get(0);
		int pointerVarNo = args.get(1);
		int sourceVarNo = gameState.getVar(pointerVarNo);
		int newValue = gameState.getVar(sourceVarNo);
		gameState.setVar(destinationVarNo, newValue);
		
	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}


}
