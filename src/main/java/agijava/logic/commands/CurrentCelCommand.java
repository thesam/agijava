package agijava.logic.commands;

import agijava.main.AnimatedObject;
import agijava.main.GameState;

public class CurrentCelCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int objNo = args.get(0);
		int varNo = args.get(1);
		AnimatedObject animatedObject = gameState.animatedObjects.get(objNo);
		gameState.vars[varNo] = animatedObject.getCurrentViewCel();
	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}

}
