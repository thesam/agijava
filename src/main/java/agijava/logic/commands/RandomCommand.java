package agijava.logic.commands;

import java.util.Random;

import agijava.main.GameState;

public class RandomCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int min = args.get(0);
		int max = args.get(1);
		int varNo =  args.get(2);
		Random random = new Random();
		if (max-min <= 0) {
			// In SQ2 there is a call where 10 is used as minor. Let's try swapping them.
			int temp = max;
			max = min;
			min = temp;
		}
		int value = random.nextInt(max-min);
		value += min;
		gameState.setVar(varNo, value);

	}

	@Override
	public int getArgsSizeInBytes() {
		// TODO Auto-generated method stub
		return 3;
	}

}
