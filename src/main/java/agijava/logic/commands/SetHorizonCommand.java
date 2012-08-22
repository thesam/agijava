package agijava.logic.commands;

import agijava.main.IGameState;

public class SetHorizonCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int newHorizon = args.get(0);
		gameState.setHorizon(newHorizon);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}


}
