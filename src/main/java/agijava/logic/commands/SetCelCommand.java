package agijava.logic.commands;

import agijava.main.AnimatedObject;
import agijava.main.GameState;

public class SetCelCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int objNo = getObjNo();
		int celNo = getCelNo(gameState);
		AnimatedObject animatedObject = gameState.animatedObjects.get(objNo);
		animatedObject.setCurrentViewCel(celNo);
	}

	protected int getCelNo(GameState gameState) {
		return args.get(1);
	}

	private Integer getObjNo() {
		return args.get(0);
	}

	@Override
	public int getArgsSizeInBytes() {
		// TODO Auto-generated method stub
		return 2;
	}

}
