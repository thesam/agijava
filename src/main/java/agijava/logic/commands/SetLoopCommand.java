package agijava.logic.commands;

import agijava.main.AnimatedObject;
import agijava.main.GameState;

public class SetLoopCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int objNo = args.get(0);
		int loopNo = args.get(1);
		AnimatedObject animatedObject = gameState.animatedObjects.get(objNo);
		animatedObject.setCurrentViewLoop(loopNo);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}


}
