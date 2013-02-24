package agijava.logic;

import java.util.ArrayList;
import java.util.List;

import agijava.io.RawByteArray;
import agijava.main.GameState;
import agijava.main.LogicCommand;

public class IfCommand implements LogicCommand {

	private static final int BYTE_IF = 0xFF;
	private List<Integer> args;
	private boolean testStatementPassed;
	private int remainingSizeBytesNeeded;
	private TestStatementEvaluator evaluator;

	public IfCommand() {
		this(new TestStatementEvaluator());
	}

	public IfCommand(TestStatementEvaluator evaluator) {
		this.evaluator = evaluator;
		args = new ArrayList<Integer>();
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
		gameState.jumpForward(blockSize);
	}

	private boolean evaluateTestStatement(RawByteArray testStatementBytes,
			GameState gameState) {
		IEvaluatedTestStatement andedStatements = evaluator
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
		if (testStatementPassed) {
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
			if (testStatementPassed) {
				if (remainingSizeBytesNeeded == 0) {
					return true;
				}
			} else {
				if (lastByte == BYTE_IF) {
					testStatementPassed = true;
				}
			}
		}
		return false;
	}

	@Override
	public void reset() {
		if (args != null) {
			args.clear();
		}
		testStatementPassed = false;
		remainingSizeBytesNeeded = 2;
	}

}
