package agijava.logic.commands;

import agijava.main.AnimatedObject;
import agijava.main.GameState;

public class EraseCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		AnimatedObject animatedObject = gameState.animatedObjects.get(args.get(0));
		animatedObject.setVisible(false);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}


}
