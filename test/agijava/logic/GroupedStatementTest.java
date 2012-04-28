package agijava.logic;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import agijava.logic.impl.GroupedStatement;
import agijava.logic.impl.GroupedStatement.GroupType;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GroupedStatementTest {
	private GroupedStatement groupedStatement;
	private IEvaluatedTestStatement statement;

	@Test
	public void canbeCreated() throws Exception {
		aGroupedStatement();
		assertNotNull(groupedStatement);
	}

	@Test
	public void canHandleOneTrueStatement() throws Exception {
		aGroupedStatement();
		aTrueStatementIsAdded();
		
		assertTrue(groupedStatement.getValue());
	}

	@Test
	public void canHandleOneFalseStatement() throws Exception {
		aGroupedStatement();
		aFalseStatementIsAdded();
		
		assertFalse(groupedStatement.getValue());
	}

	@Test
	public void canStoreGroupType() throws Exception {
		aGroupedStatement();
		groupedStatement.setGroupType(GroupType.AND);
		assertEquals(GroupType.AND, groupedStatement.getGroupType());
		groupedStatement.setGroupType(GroupType.OR);
		assertEquals(GroupType.OR, groupedStatement.getGroupType());
	}
	
	@Test
	public void andsStatementsByDefault() throws Exception {
		aGroupedStatement();
		assertEquals(GroupType.AND,groupedStatement.getGroupType());
	}
	
	@Test
	public void canHandleTwoAndedStatements() throws Exception {
		aGroupedStatement();
		aTrueStatementIsAdded();
		aTrueStatementIsAdded();
		assertEquals(true, groupedStatement.getValue());
		aGroupedStatement();
		aTrueStatementIsAdded();
		aFalseStatementIsAdded();
		assertEquals(false, groupedStatement.getValue());
	}
	
	@Test
	public void canHandleTwoOredStatements() throws Exception {
		anOredGroupedStatement();
		aTrueStatementIsAdded();
		aTrueStatementIsAdded();
		assertTrue(groupedStatement.getValue());
		anOredGroupedStatement();
		aTrueStatementIsAdded();
		aFalseStatementIsAdded();
		assertTrue(groupedStatement.getValue());
		anOredGroupedStatement();
		aFalseStatementIsAdded();
		aFalseStatementIsAdded();
		assertFalse(groupedStatement.getValue());
	}

	private void anOredGroupedStatement() {
		aGroupedStatement();
		groupedStatement.setGroupType(GroupType.OR);
	}
	
	@Test
	public void canReturnNumberOfArguments() throws Exception {
		aGroupedStatement();
		aTrueStatementIsAdded();
		assertEquals(1,groupedStatement.size());
		aTrueStatementIsAdded();
		assertEquals(2,groupedStatement.size());
		aTrueStatementIsAdded();
		assertEquals(3,groupedStatement.size());
	}
	
	@Test (expected=NotImplementedException.class)
	public void throwsExceptionIfNegated() throws Exception {
		aGroupedStatement();
		groupedStatement.setNegated(true);
	}
	
	@Test
	public void canCheckIfAlreadyContainsStatement() throws Exception {
		aGroupedStatement();
		aTrueStatementIsAdded();
		assertTrue(groupedStatement.contains(statement));
		assertFalse(groupedStatement.contains(mock(IEvaluatedTestStatement.class)));
	}

	private void aFalseStatementIsAdded() {
		groupedStatement.add(createStatement(false));
	}

	private void aTrueStatementIsAdded() {
		groupedStatement.add(createStatement(true));
	}

	private IEvaluatedTestStatement createStatement(boolean value) {
		statement = mock(IEvaluatedTestStatement.class);
		when(statement.getValue()).thenReturn(value);
		return statement;
	}

	private void aGroupedStatement() {
		groupedStatement = new GroupedStatement();
	}
	
}
