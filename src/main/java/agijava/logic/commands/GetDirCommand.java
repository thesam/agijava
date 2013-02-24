package agijava.logic.commands;

import agijava.main.AnimatedObject;
import agijava.main.GameState;

public class GetDirCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int objNo = args.get(0);
		int varNo = args.get(1);
		AnimatedObject animatedObject = gameState.getAnimatedObject(objNo);
		gameState.setVar(varNo, animatedObject.getDirection());
	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}


}
