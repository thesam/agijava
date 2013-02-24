package agijava.logic.commands;

import agijava.main.IGameState;
import agijava.main.impl.AnimatedObject;

public class ObserveObjsCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int objNo = args.get(0);
		AnimatedObject obj = gameState.getAnimatedObject(objNo);
		obj.setObserveObjects(true);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}


}
