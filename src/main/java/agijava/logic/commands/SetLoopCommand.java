package agijava.logic.commands;

import agijava.main.IGameState;
import agijava.main.impl.AnimatedObject;

public class SetLoopCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int objNo = args.get(0);
		int loopNo = args.get(1);
		AnimatedObject animatedObject = gameState.getAnimatedObject(objNo);
		animatedObject.setCurrentViewLoop(loopNo);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}


}
