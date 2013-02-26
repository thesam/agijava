package agijava.logic.commands;

import agijava.main.GameState;

public class ShowPicCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
//		gameState.clearBackgroundViews();
		gameState.displayedTexts.clear();
		showPictureFromBuffer(gameState);

	}

	@Override
	public int getArgsSizeInBytes() {
		return 0;
	}

	private void showPictureFromBuffer(GameState gameState) {
		gameState.currentPicture = gameState.bufferPicture;
		gameState.displayedBackgroundViews = gameState.bufferBackgroundViews;
	}
}
