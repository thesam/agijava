package agijava.logic.commands;

import agijava.main.GameState;

public class AssignvCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int targetVar = args.get(0);
		int sourceVar = args.get(1);
		gameState.vars[targetVar] = gameState.vars[sourceVar];
		
	}

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}


}
