package agijava.logic;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import agijava.io.RawByteArray;
import agijava.logic.EvaluatedTestStatement;
import agijava.main.GameState;

public class IfCommandTest {
	private static final int BYTE_IF = 0xFF;
	private IfCommand ifCommand;
	private ArrayList<Integer> args;
	private GameState gameState;
	private TestStatementEvaluator evaluator;
	private List<Integer> input;
	private EvaluatedTestStatement evaluatedTestStatement;

	@Before
	public void setup() throws Exception {
		evaluator = mock(TestStatementEvaluator.class);
		evaluatedTestStatement = mock(EvaluatedTestStatement.class);
		when(
				evaluator.createStatementsFromBytes((RawByteArray) anyObject(),
						(GameState) anyObject())).thenReturn(
				evaluatedTestStatement);

		input = new ArrayList<Integer>();
		ifCommand = new IfCommand(evaluator);

		gameState = new GameState(null, null, null, null, null);
		gameState.currentLogic = mock(Logic.class);
	}

	@Test
	public void requiresOneByteArgumentAtATime() throws Exception {
		assertEquals(1, ifCommand.getArgsSizeInBytes());
	}

	@Test
	public void needsTwoBytesAfterLastIfByteInArguments() throws Exception {
		assertFalse(ifCommand.hasAllNeededArgs());
		input.add(BYTE_IF);
		ifCommand.setArgs(input);
		assertFalse(ifCommand.hasAllNeededArgs());
		input.clear();
		input.add(0);
		ifCommand.setArgs(input);
		assertFalse(ifCommand.hasAllNeededArgs());
		input.clear();
		input.add(0);
		ifCommand.setArgs(input);
		assertTrue(ifCommand.hasAllNeededArgs());
	}

	@Test
	public void doesNotJumpForwardWhenStatementIsTrue() throws Exception {
		aTrueTestStatement();
		threeArguments();
		ifCommand.execute(gameState);
		assertEquals(0, gameState.currentLogic.getCurrentOffset());
	}

	private void aTrueTestStatement() {
		when(evaluatedTestStatement.getValue()).thenReturn(true);
	}

	@Test
	public void jumpsForwardWhenStatementIsFalse() throws Exception {
		aFalseTestStatement();
		threeArguments();
		ifCommand.execute(gameState);
		verify(gameState.currentLogic).increaseOffset(3 << 8 | 2);
	}

	private void aFalseTestStatement() {
		when(evaluatedTestStatement.getValue()).thenReturn(false);
	}

	private void threeArguments() {
		input.add(5);
		ifCommand.setArgs(input);
		input.set(0, 2);
		ifCommand.setArgs(input);
		input.set(0, 3);
		ifCommand.setArgs(input);
	}

}
