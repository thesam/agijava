package agijava.logic.commands;

import agijava.main.IGameState;

public class GetStringCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		// TODO Auto-generated method stub
		System.err.println("getString called.");
	}

	@Override
	public int getArgsSizeInBytes() {
		return 5;
	}

}
