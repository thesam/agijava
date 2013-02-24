package agijava.logic.commands;

import agijava.main.AnimatedObject;
import agijava.main.GameState;
import agijava.main.Position;

public class PositionCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int objNo = args.get(0);
		int x = getX(gameState);
		int y = getY(gameState);
		AnimatedObject animatedObject = gameState.getAnimatedObject(objNo);
		animatedObject.setPosition(new Position(x,y));
	}

	protected int getY(GameState gameState) {
		return args.get(2);
	}

	protected int getX(GameState gameState) {
		return args.get(1);
	}

	@Override
	public int getArgsSizeInBytes() {
		// TODO Auto-generated method stub
		return 3;
	}

}
