package agijava.logic.commands;

import agijava.main.GameState;

public class PlayerControlCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		gameState.setPlayerControl(true);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 0;
	}


}
