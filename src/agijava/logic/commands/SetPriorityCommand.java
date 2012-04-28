package agijava.logic.commands;

import agijava.main.IAnimatedObject;
import agijava.main.IGameState;

public class SetPriorityCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int objNo = args.get(0);
		int prio = args.get(1);
		IAnimatedObject animatedObject = gameState.getAnimatedObject(objNo);
		animatedObject.setPriority(prio);
		
	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}

}
