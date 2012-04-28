package agijava.logic.commands;

import agijava.main.IGameState;

public class PlayerControlCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		gameState.setPlayerControl(true);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 0;
	}


}
