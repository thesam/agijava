package agijava.logic.commands;


import agijava.main.AnimatedObject;
import agijava.main.IGameState;

public class ReverseCycleCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int objNo = args.get(0);
		AnimatedObject animatedObject = gameState.getAnimatedObject(objNo);
		animatedObject.setReverseCycle(true);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}

}
