package agijava.logic.impl;

import agijava.logic.commands.AbstractLogicCommand;
import agijava.main.IGameState;

public class GotoCommand extends AbstractLogicCommand {

	@Override
	public int getArgsSizeInBytes() {
		return 2;
	}

	// @Override
	// protected String createArgumentList(List<Integer> args) {
	// int part1 = args.get(0);
	// int part2 = args.get(1);
	// int number = part2 << 8 | part1;
	// if (isNegative(number)) {
	// return ""+getAsNegative(number);
	// }
	// return ""+number;
	// }

	private int getAsNegative(int number) {
		return (number | (0xFFFF << 16));
	}

	private boolean isNegative(int number) {
		int filter = 1 << 15;
		int signedBit = number & filter;
		return signedBit == filter;
	}

	@Override
	public void execute(IGameState gameState) {
		int part1 = args.get(0);
		int part2 = args.get(1);
		int number = part2 << 8 | part1;
		if (isNegative(number)) {
			number = getAsNegative(number);
		}
		gameState.jumpForward(number);
	}

}
