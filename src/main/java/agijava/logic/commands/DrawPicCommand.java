package agijava.logic.commands;

import agijava.main.GameState;

public class DrawPicCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int varNo = args.get(0);
		int picNo = gameState.vars[varNo];
		gameState.bufferBackgroundViews.clear();
		setPictureInBuffer(picNo,gameState);
		
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}
	
	private void setPictureInBuffer(int picNo, GameState gameState) {
		gameState.bufferPicture = gameState.pictureRepository.getPicture(picNo);
	}


}
