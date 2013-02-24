package agijava.logic.commands;

import agijava.main.AnimatedObject;
import agijava.main.GameState;

public class SetPriorityCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int objNo = args.get(0);
		int prio = args.get(1);
		AnimatedObject animatedObject = gameState.animatedObjects.get(objNo);
		animatedObject.setPriority(prio);
		
	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}

}
