package agijava.logic.commands;

import agijava.main.GameState;

public class ShowPicCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
//		gameState.clearBackgroundViews();
		gameState.displayedTexts.clear();
		gameState.showPictureFromBuffer();

	}

	@Override
	public int getArgsSizeInBytes() {
		return 0;
	}

}
