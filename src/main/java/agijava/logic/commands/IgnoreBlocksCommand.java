package agijava.logic.commands;

import agijava.main.IGameState;
import agijava.main.impl.AnimatedObject;

public class IgnoreBlocksCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int objNo = args.get(0);
		AnimatedObject animatedObject = gameState.getAnimatedObject(objNo);
		animatedObject.setIgnoreBlocks(true);
		

	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}

}
