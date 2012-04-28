package agijava.logic.commands;

import agijava.main.IGameState;

public class ShowPicCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
//		gameState.clearBackgroundViews();
		gameState.clearDisplayedTexts();
		gameState.showPictureFromBuffer();

	}

	@Override
	public int getArgsSizeInBytes() {
		return 0;
	}

}
