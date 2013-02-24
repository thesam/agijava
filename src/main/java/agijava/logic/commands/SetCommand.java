package agijava.logic.commands;

import agijava.main.GameState;

public class SetCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int flagNo = args.get(0);
		gameState.flags[flagNo] = true;
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}

}
