package agijava.logic.commands;

import agijava.main.AnimatedObject;
import agijava.main.GameState;

public class GetPosnCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int objNo = args.get(0);
		int xVarNo = args.get(1);
		int yVarNo = args.get(2);
		AnimatedObject animatedObject = gameState.animatedObjects.get(objNo);
		int x = animatedObject.getCurrentPosition().getX();
		int y = animatedObject.getCurrentPosition().getY();
		gameState.vars[xVarNo] = x;
		gameState.vars[yVarNo] = y;
	}

	@Override
	public int getArgsSizeInBytes() {
		return 3;
	}


}
