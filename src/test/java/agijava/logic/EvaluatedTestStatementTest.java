package agijava.logic;

import static org.junit.Assert.*;

import org.junit.Test;

public class EvaluatedTestStatementTest {
	private SingleStatement evaluatedTestStatement;

	@Test
	public void canRememberATrueBoolean() throws Exception {
		aTrueStatement();
		assertEquals(true, evaluatedTestStatement.getValue());
	}

	private void aTrueStatement() {
		evaluatedTestStatement = new SingleStatement(true);
	}

	@Test
	public void canRememberAFalseBoolean() throws Exception {
		evaluatedTestStatement = new SingleStatement(false);
		assertEquals(false, evaluatedTestStatement.getValue());
	}
	
	@Test
	public void canBeNegated() throws Exception {
		aTrueStatement();
		evaluatedTestStatement.setNegated(true);
		assertEquals(false,evaluatedTestStatement.getValue());
	}
}
