package agijava.logic.commands;


import agijava.main.IGameState;
import agijava.main.impl.AnimatedObject;

public class StopMotionCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int objNo = args.get(0);
		AnimatedObject animatedObject = gameState.getAnimatedObject(objNo);
		animatedObject.setMoving(false);
		if (objNo == 0) {
			new ProgramControlCommand().execute(gameState);
		}
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}

}
