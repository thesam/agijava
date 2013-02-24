package agijava.logic.commands;

import agijava.main.GameState;

public class SetHorizonCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int newHorizon = args.get(0);
		gameState.horizon = newHorizon;
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}


}
