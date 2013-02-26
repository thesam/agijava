package agijava.logic.commands;

import agijava.main.GameState;

public class PlayerControlCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		gameState.playerControl = true;
	}

	@Override
	public int getArgsSizeInBytes() {
		return 0;
	}


}
