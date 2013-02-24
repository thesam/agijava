package agijava.logic.commands;

import agijava.main.AnimatedObject;
import agijava.main.GameState;
import agijava.main.Position;

public class MoveObjCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int objNo = args.get(0);
		int x = getX(gameState);
		int y = getY(gameState);
		int stepsize = args.get(3);
		int finishFlag = args.get(4);
		AnimatedObject animatedObject = gameState.getAnimatedObject(objNo);
		animatedObject.setMoving(true);
		animatedObject.setMovingToDestination(true);
		animatedObject.setDestination(new Position(x,y));
		animatedObject.setPixelStepSize(stepsize);
		animatedObject.setMoveFinishedFlagNo(finishFlag);
		
	}

	protected int getY(GameState gameState) {
		return args.get(2);
	}

	protected int getX(GameState gameState) {
		return args.get(1);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 5;
	}

}
