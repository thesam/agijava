package agijava.logic.commands;

import agijava.main.GameState;

public class ClearLinesCommand extends AbstractLogicCommand {

	@SuppressWarnings("unused")
	@Override
	public void execute(GameState gameState) {
		int firstLine = args.get(0);
		int lastLine = args.get(1);
		int colorNo = args.get(2);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 3;
	}

}
