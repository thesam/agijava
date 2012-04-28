package agijava.logic.commands;

import agijava.main.IAnimatedObject;
import agijava.main.IGameState;

public class ObserveObjsCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int objNo = args.get(0);
		IAnimatedObject obj = gameState.getAnimatedObject(objNo);
		obj.setObserveObjects(true);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}


}
