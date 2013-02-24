package agijava.logic;

import java.util.ArrayList;
import java.util.List;

public class GroupedStatement implements EvaluatedTestStatement {

	public enum GroupType {OR, AND};
	
	private GroupType groupType;
	private List<EvaluatedTestStatement> statements;
	
	public GroupedStatement(List<EvaluatedTestStatement> statements,GroupType groupType) {
		this.groupType = groupType;
		this.statements = statements;
	}

	public GroupedStatement() {
		this(new ArrayList<EvaluatedTestStatement>(),GroupType.AND);
	}

	@Override
	public boolean getValue() {
		if (groupType == GroupType.AND) { 
			for (EvaluatedTestStatement statement : statements) {
				if (statement.getValue() == false) {
					return false;
				}
			}
			return true;
		} else {
			for (EvaluatedTestStatement statement : statements) {
				if (statement.getValue() == true) {
					return true;
				}
			}
			return false;		
		}
	}

	public void add(EvaluatedTestStatement statement) {
		statements.add(statement);
	}

	public int size() {
		return statements.size();
	}

	public void setGroupType(GroupType groupType) {
		this.groupType = groupType;
		
	}

	public boolean contains(EvaluatedTestStatement statement) {
		return statements.contains(statement);
	}

	@Override
	public void setNegated(boolean b) {
		throw new RuntimeException("Operation not supported.");
	}

	public GroupType getGroupType() {
		return groupType;
	}


}
