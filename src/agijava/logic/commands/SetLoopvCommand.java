package agijava.logic.commands;

import agijava.main.IAnimatedObject;
import agijava.main.IGameState;

public class SetLoopvCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int objNo = args.get(0);
		int varNo = args.get(1);
		int loopNo = gameState.getVar(varNo);
		IAnimatedObject animatedObject = gameState.getAnimatedObject(objNo);
		animatedObject.setCurrentViewLoop(loopNo);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}

}
