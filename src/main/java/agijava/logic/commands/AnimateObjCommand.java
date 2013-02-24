package agijava.logic.commands;

import agijava.main.GameState;

public class AnimateObjCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int objNo = args.get(0);
		gameState.addAnimatedObject(objNo);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}

}
