package agijava.logic;

import agijava.io.RawByteArray;
import agijava.main.IGameState;

public interface ITestStatementEvaluator {

	IEvaluatedTestStatement createStatementsFromBytes(
			RawByteArray testStatementBytes, IGameState gameState);

}
