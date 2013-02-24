package agijava.logic.commands;

import java.util.Collection;

import agijava.main.AnimatedObject;
import agijava.main.IGameState;

public class UnanimateAllCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		Collection<AnimatedObject> animatedObjects = gameState.getAnimatedObjects();
		for (AnimatedObject animatedObject : animatedObjects) {
			animatedObject.setShouldBeUpdated(false);
			animatedObject.setVisible(false);
		}
	}

	@Override
	public int getArgsSizeInBytes() {
		// TODO Auto-generated method stub
		return 0;
	}

}
