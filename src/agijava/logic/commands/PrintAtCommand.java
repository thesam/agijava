package agijava.logic.commands;


import agijava.main.IGameState;

public class PrintAtCommand extends PrintCommand {

	protected int getMessageNo(IGameState gameState) {
		return args.get(0);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 4;
	}

}
