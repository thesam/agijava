package agijava.logic.commands;

import agijava.main.IGameState;
import agijava.main.impl.AnimatedObject;

public class StartMotionCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int objNo = args.get(0);
		AnimatedObject animatedObject = gameState.getAnimatedObject(objNo);
		animatedObject.setMoving(true);
		if (objNo == 0) {
			new PlayerControlCommand().execute(gameState);
		}
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}


}
