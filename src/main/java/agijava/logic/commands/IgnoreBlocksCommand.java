package agijava.logic.commands;

import agijava.main.AnimatedObject;
import agijava.main.GameState;

public class IgnoreBlocksCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int objNo = args.get(0);
		AnimatedObject animatedObject = gameState.animatedObjects.get(objNo);
		animatedObject.setIgnoreBlocks(true);
		

	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}

}
