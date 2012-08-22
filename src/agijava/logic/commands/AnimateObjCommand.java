package agijava.logic.commands;

import agijava.main.IGameState;

public class AnimateObjCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int objNo = args.get(0);
		gameState.addAnimatedObject(objNo);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}

}
