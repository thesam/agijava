package agijava.logic;

import java.util.ArrayList;
import java.util.List;

import agijava.io.RawByteArray;
import agijava.main.GameState;
import agijava.main.LogicCommand;

public class IfCommand implements LogicCommand {

	private static final int BYTE_IF = 0xFF;
	private List<Integer> args = new ArrayList<Integer>();
	private boolean testStatementBytesHaveBeenRead;
	private int remainingSizeBytesNeeded = 2;
	private TestStatementEvaluator evaluator;

	public IfCommand(TestStatementEvaluator evaluator) {
		this.evaluator = evaluator;
	}

	@Override
	public void execute(GameState gameState) {
		int blockSize = (args.get(args.size() - 1) << 8)
				| args.get(args.size() - 2);
		List<Integer> testStatementBytes = args.subList(0, args.size() - 3);
		RawByteArray rawByteArray = new RawByteArray(testStatementBytes);
		if (ifStatementIsNotTrue(gameState, rawByteArray)) {
			skipContentOfIfBlock(gameState, blockSize);
		}
	}

	private boolean ifStatementIsNotTrue(GameState gameState,
			RawByteArray rawByteArray) {
		return !evaluateTestStatement(rawByteArray, gameState);
	}

	private void skipContentOfIfBlock(GameState gameState, int blockSize) {
		gameState.currentLogic.increaseOffset(blockSize);
	}

	private boolean evaluateTestStatement(RawByteArray testStatementBytes,
			GameState gameState) {
		EvaluatedTestStatement andedStatements = evaluator
				.createStatementsFromBytes(testStatementBytes, gameState);
		return andedStatements.getValue();
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}

	@Override
	public void setArgs(List<Integer> args) {
		this.args.addAll(args);
		if (testStatementBytesHaveBeenRead) {
			remainingSizeBytesNeeded--;
		}

	}

	@Override
	public boolean hasAllNeededArgs() {
		// måste ha
		// 1.fram till oxFF
		// 2. två till för storlek

		if (args.size() > 0) {
			Integer lastByte = args.get(args.size() - 1);
			if (testStatementBytesHaveBeenRead) {
				if (remainingSizeBytesNeeded == 0) {
					return true;
				}
			} else {
				if (lastByte == BYTE_IF) {
					testStatementBytesHaveBeenRead = true;
				}
			}
		}
		return false;
	}

	@Override
	public void reset() {
		args.clear();
		testStatementBytesHaveBeenRead = false;
		remainingSizeBytesNeeded = 2;
	}

}
