package agijava.logic.impl;

import agijava.logic.IEvaluatedTestStatement;

public class EvaluatedTestStatement implements IEvaluatedTestStatement {

	private boolean b;
	private boolean isNegated = false;

	public EvaluatedTestStatement(boolean b) {
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
