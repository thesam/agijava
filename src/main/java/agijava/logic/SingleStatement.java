package agijava.logic;


public class SingleStatement implements EvaluatedTestStatement {

	private boolean b;
	private boolean isNegated = false;

	public SingleStatement(boolean b) {
		this.b = b;
	}

	public boolean getValue() {
		boolean value = b;
		if (isNegated) {
			value = !value;
		} 
		return value;
	}

	@Override
	public void setNegated(boolean b) {
		this.isNegated  = b;
	}

}
