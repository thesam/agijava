package agijava.logic.commands;

import agijava.main.IAnimatedObject;
import agijava.main.IGameState;

public class EraseCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		IAnimatedObject animatedObject = gameState.getAnimatedObject(args.get(0));
		animatedObject.setVisible(false);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}


}
