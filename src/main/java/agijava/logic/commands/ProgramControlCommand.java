package agijava.logic.commands;

import agijava.main.GameState;

public class ProgramControlCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		gameState.playerControl = false;
	}

	@Override
	public int getArgsSizeInBytes() {
		return 0;
	}

}
