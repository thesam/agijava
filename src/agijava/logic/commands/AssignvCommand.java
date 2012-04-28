package agijava.logic.commands;

import agijava.main.IGameState;

public class AssignvCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int targetVar = args.get(0);
		int sourceVar = args.get(1);
		gameState.setVar(targetVar, gameState.getVar(sourceVar));
		
	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}


}
