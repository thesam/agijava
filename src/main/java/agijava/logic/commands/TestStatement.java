package agijava.logic.commands;

public class TestStatement {

	private boolean negated;
	private String statementString;
	private boolean ored;

	public void setNegated(boolean b) {
		negated = b;
	}

	public void setString(String testStatementString) {
		this.statementString = testStatementString;
	}

	public String getString() {
		return statementString;
	}

	public boolean isNegated() {
		return negated;
	}

	public void setOred(boolean oredStatement) {
		this.ored = oredStatement;

	}

	public boolean isOred() {
		return ored;
	}

}
