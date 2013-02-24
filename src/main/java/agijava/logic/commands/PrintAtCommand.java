package agijava.logic.commands;


import agijava.main.GameState;

public class PrintAtCommand extends PrintCommand {

	protected int getMessageNo(GameState gameState) {
		return args.get(0);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 4;
	}

}
