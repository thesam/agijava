package agijava.logic.commands;

import agijava.main.IAnimatedObject;
import agijava.main.IGameState;

public class ObserveBlocksCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int objNo = args.get(0);
		IAnimatedObject animatedObject = gameState.getAnimatedObject(objNo);
		animatedObject.setIgnoreBlocks(false);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}

}
