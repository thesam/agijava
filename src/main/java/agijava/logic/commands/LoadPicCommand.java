package agijava.logic.commands;

import agijava.main.GameState;

public class LoadPicCommand extends AbstractLogicCommand {

	@SuppressWarnings("unused")
	@Override
	public void execute(GameState gameState) {
		//TODO: Always load all pictures?
		int varNo = args.get(0);
		int picNo = gameState.vars[varNo];
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}


}
