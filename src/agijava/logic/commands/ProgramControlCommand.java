package agijava.logic.commands;

import agijava.main.IGameState;

public class ProgramControlCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		gameState.setPlayerControl(false);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 0;
	}

}
