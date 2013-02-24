package agijava.logic.commands;

import agijava.main.AnimatedObject;
import agijava.main.IGameState;

public class SetCelCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int objNo = getObjNo();
		int celNo = getCelNo(gameState);
		AnimatedObject animatedObject = gameState.getAnimatedObject(objNo);
		animatedObject.setCurrentViewCel(celNo);
	}

	protected int getCelNo(IGameState gameState) {
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
