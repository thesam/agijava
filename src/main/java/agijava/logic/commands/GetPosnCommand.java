package agijava.logic.commands;

import agijava.main.IGameState;
import agijava.main.impl.AnimatedObject;

public class GetPosnCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int objNo = args.get(0);
		int xVarNo = args.get(1);
		int yVarNo = args.get(2);
		AnimatedObject animatedObject = gameState.getAnimatedObject(objNo);
		int x = animatedObject.getCurrentPosition().getX();
		int y = animatedObject.getCurrentPosition().getY();
		gameState.setVar(xVarNo, x);
		gameState.setVar(yVarNo, y);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 3;
	}


}
