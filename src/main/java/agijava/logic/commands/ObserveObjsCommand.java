package agijava.logic.commands;

import agijava.main.AnimatedObject;
import agijava.main.GameState;

public class ObserveObjsCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int objNo = args.get(0);
		AnimatedObject obj = gameState.animatedObjects.get(objNo);
		obj.setObserveObjects(true);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}


}
