package agijava.logic.commands;

import agijava.main.IGameState;

public class LoadPicCommand extends AbstractLogicCommand {

	@SuppressWarnings("unused")
	@Override
	public void execute(IGameState gameState) {
		//TODO: Always load all pictures?
		int varNo = args.get(0);
		int picNo = gameState.getVar(varNo);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}


}
