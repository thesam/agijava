package agijava.logic.commands;

import agijava.main.AnimatedObject;
import agijava.main.GameState;

public class StopUpdateCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int objNo = args.get(0);
		AnimatedObject animatedObject = gameState.getAnimatedObject(objNo);
		animatedObject.setShouldBeUpdated(false);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}

}
