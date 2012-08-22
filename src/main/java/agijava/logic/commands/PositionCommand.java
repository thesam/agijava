package agijava.logic.commands;

import agijava.main.IAnimatedObject;
import agijava.main.IGameState;

public class PositionCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int objNo = args.get(0);
		int x = getX(gameState);
		int y = getY(gameState);
		IAnimatedObject animatedObject = gameState.getAnimatedObject(objNo);
		animatedObject.setX(x);
		animatedObject.setY(y);

	}

	protected int getY(IGameState gameState) {
		return args.get(2);
	}

	protected int getX(IGameState gameState) {
		return args.get(1);
	}

	@Override
	public int getArgsSizeInBytes() {
		// TODO Auto-generated method stub
		return 3;
	}

}
