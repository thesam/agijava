package agijava.logic.commands;

import agijava.main.AnimatedObject;
import agijava.main.IGameState;

public class EraseCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		AnimatedObject animatedObject = gameState.getAnimatedObject(args.get(0));
		animatedObject.setVisible(false);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}


}
