package agijava.logic.commands;

import agijava.main.GameState;

public class DrawPicCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int varNo = args.get(0);
		int picNo = gameState.getVar(varNo);
		gameState.clearBackgroundViews();
		gameState.setPictureInBuffer(picNo);
		
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}


}
