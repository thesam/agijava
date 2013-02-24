package agijava.logic.commands;

import agijava.main.AnimatedObject;
import agijava.main.GameState;

public class SetViewCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int objNo = args.get(0);
		int viewNo = getViewNo(gameState);
		AnimatedObject animatedObject = gameState.getAnimatedObject(objNo);
		animatedObject.setView(viewNo);
		animatedObject.setCurrentViewLoop(0);
		animatedObject.setCurrentViewCel(0);
	}

	protected int getViewNo(GameState gameState) {
		return args.get(1);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}


}

