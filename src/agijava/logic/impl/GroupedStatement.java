package agijava.logic.impl;

import java.util.ArrayList;
import java.util.List;

import agijava.logic.IEvaluatedTestStatement;

public class GroupedStatement implements IEvaluatedTestStatement {

	public enum GroupType {OR, AND};
	
	private GroupType groupType;
	private List<IEvaluatedTestStatement> statements;
	
	public GroupedStatement(List<IEvaluatedTestStatement> statements,GroupType groupType) {
		this.groupType = groupType;
		this.statements = statements;
	}

	public GroupedStatement() {
		this(new ArrayList<IEvaluatedTestStatement>(),GroupType.AND);
	}

	@Override
	public boolean getValue() {
		if (groupType == GroupType.AND) { 
			for (IEvaluatedTestStatement statement : statements) {
				if (statement.getValue() == false) {
					return false;
				}
			}
			return true;
		} else {
			for (IEvaluatedTestStatement statement : statements) {
				if (statement.getValue() == true) {
					return true;
				}
			}
			return false;		
		}
	}

	public void add(IEvaluatedTestStatement statement) {
		statements.add(statement);
	}

	public int size() {
		return statements.size();
	}

	public void setGroupType(GroupType groupType) {
		this.groupType = groupType;
		
	}

	public boolean contains(IEvaluatedTestStatement statement) {
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
