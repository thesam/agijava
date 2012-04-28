package agijava.logic;

import static org.junit.Assert.*;

import org.junit.Test;

import agijava.logic.impl.EvaluatedTestStatement;

public class EvaluatedTestStatementTest {
	private EvaluatedTestStatement evaluatedTestStatement;

	@Test
	public void canRememberATrueBoolean() throws Exception {
		aTrueStatement();
		assertEquals(true, evaluatedTestStatement.getValue());
	}

	private void aTrueStatement() {
		evaluatedTestStatement = new EvaluatedTestStatement(true);
	}

	@Test
	public void canRememberAFalseBoolean() throws Exception {
		evaluatedTestStatement = new EvaluatedTestStatement(false);
		assertEquals(false, evaluatedTestStatement.getValue());
	}
	
	@Test
	public void canBeNegated() throws Exception {
		aTrueStatement();
		evaluatedTestStatement.setNegated(true);
		assertEquals(false,evaluatedTestStatement.getValue());
	}
}
