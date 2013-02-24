package agijava.logic.commands;

import agijava.main.IGameState;
import agijava.main.impl.AnimatedObject;

public class CurrentLoopCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int objNo = args.get(0);
		int varNo = args.get(1);
		AnimatedObject animatedObject = gameState.getAnimatedObject(objNo);
		gameState.setVar(varNo, animatedObject.getCurrentViewLoop());
	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}

}
