package agijava.logic.commands;

import agijava.main.AnimatedObject;
import agijava.main.GameState;

public class AnimateObjCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int objNo = args.get(0);
		addAnimatedObject(objNo,gameState);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}
	
	private void addAnimatedObject(int objNo, GameState gameState) {
		AnimatedObject obj = null;
		obj = getAnimatedObjectWithNumber(objNo,gameState);
		obj.setShouldBeUpdated(true);
		gameState.animatedObjects.put(objNo, obj);

	}
	
	private AnimatedObject getAnimatedObjectWithNumber(int objNo, GameState gameState) {
		AnimatedObject obj;
		if (gameState.animatedObjects.containsKey(objNo)) {
			obj = gameState.animatedObjects.get(objNo);
		} else {
			obj = new AnimatedObject(gameState.viewRepository);
		}
		if (objNo == 0) {
			obj.setEgo(true);
		}
		obj.setNumber(objNo);
		return obj;
	}

}
