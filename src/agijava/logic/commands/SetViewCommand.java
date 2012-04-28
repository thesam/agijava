package agijava.logic.commands;

import agijava.main.IAnimatedObject;
import agijava.main.IGameState;

public class SetViewCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int objNo = args.get(0);
		int viewNo = getViewNo(gameState);
		IAnimatedObject animatedObject = gameState.getAnimatedObject(objNo);
		animatedObject.setView(viewNo);
		animatedObject.setCurrentViewLoop(0);
		animatedObject.setCurrentViewCel(0);
	}

	protected int getViewNo(IGameState gameState) {
		return args.get(1);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}


}

