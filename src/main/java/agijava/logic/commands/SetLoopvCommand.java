package agijava.logic.commands;

import agijava.main.AnimatedObject;
import agijava.main.GameState;

public class SetLoopvCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int objNo = args.get(0);
		int varNo = args.get(1);
		int loopNo = gameState.vars[varNo];
		AnimatedObject animatedObject = gameState.animatedObjects.get(objNo);
		animatedObject.setCurrentViewLoop(loopNo);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}

}
