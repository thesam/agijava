package agijava.logic.commands;

import agijava.main.IAnimatedObject;
import agijava.main.IGameState;
import agijava.main.impl.AnimatedObject.LoopDirection;

public class EndOfLoopCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int objNo = args.get(0);
		int finishFlagNo = args.get(1);
		IAnimatedObject animatedObject = gameState.getAnimatedObject(objNo);
		animatedObject.setLoopFinishedFlag(finishFlagNo);
		animatedObject.setInSingleLoop(true);
		animatedObject.setSingleLoopDirection(LoopDirection.FORWARD);

	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}

}
