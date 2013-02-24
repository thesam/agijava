package agijava.logic.commands;

import agijava.main.AnimatedObject;
import agijava.main.GameState;
import agijava.main.AnimatedObject.LoopDirection;

public class ReverseLoopCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int objNo = args.get(0);
		int finishFlagNo = args.get(1);
		AnimatedObject animatedObject = gameState.getAnimatedObject(objNo);
		animatedObject.setLoopFinishedFlag(finishFlagNo);
		animatedObject.setInSingleLoop(true);
		animatedObject.setSingleLoopDirection(LoopDirection.REVERSE);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}

}
