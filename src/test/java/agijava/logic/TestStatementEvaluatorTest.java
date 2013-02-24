package agijava.logic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import agijava.io.RawByteArray;
import agijava.main.AnimatedObject;
import agijava.main.GameEngine;
import agijava.main.GameState;
import agijava.main.InventoryObject;
import agijava.main.Position;
import agijava.main.WordsTok;
import static org.mockito.Mockito.*;

public class TestStatementEvaluatorTest {

	private static final int BYTE_EQUALN = 0x01;
	private static final int BYTE_EQUALV = 0x02;
	private static final int BYTE_LESSN = 0x03;
	private static final int BYTE_LESSV = 0x04;
	private static final int BYTE_GREATERN = 0x05;
	private static final int BYTE_GREATERV = 0x06;
	private static final int BYTE_ISSET = 0x07;
	private static final int BYTE_ISSETV = 0x08;
	private static final int BYTE_HAS = 0x09;

	@SuppressWarnings("unused")
	private static final int BYTE_ELSE_GOTO = 0xFE;
	private static final int BYTE_OBJINROOM = 0x0A;
	@SuppressWarnings("unused")
	private static final int BYTE_POSN = 0x0B;
	private static final int BYTE_CONTROLLER = 0x0C;

	private static final int BYTE_HAVEKEY = 0x0D;
	private static final int BYTE_SAID = 0x0E;
	private static final int BYTE_COMPARESTRINGS = 0x0F;

	private static final int BYTE_OBJINBOX = 0x10;
	@SuppressWarnings("unused")
	private static final int BYTE_CENTERPOSN = 0x11;
	@SuppressWarnings("unused")
	private static final int BYTE_RIGHTPOSN = 0x12;

	@SuppressWarnings("unused")
	private static final int BYTE_IF = 0xFF;
	private static final int BYTE_OR = 0xFC;
	private static final int BYTE_NOT = 0xFD;

	private TestStatementEvaluator testStatementEvaluator;
	private GameState gameState;
	private EvaluatedTestStatement statement;
	private RawByteArray rawByteArray;
	private List<Integer> inputList = new ArrayList<Integer>();

	@Test
	public void evaluatesEmptyRawByteArrayAsTrue() throws Exception {
		anEvaluator();
		EvaluatedTestStatement createStatementsFromBytes = testStatementEvaluator
				.createStatementsFromBytes(rawByteArray, gameState);

		assertTrue(createStatementsFromBytes.getValue());
	}

	@Test
	public void canEvaluateTrueEqualnStatement() throws Exception {
		anEvaluator();
		anEqualnTestForVars5();
		inputList.add(6);

		when(gameState.getVar(5)).thenReturn(6);

		statementIsEvaluated();

		verify(gameState).getVar(5);
		statementIsTrue();
	}

	@Test
	public void canEvaluateTrueEqualvStatement() throws Exception {
		anEvaluator();
		anEqualvTestForVars5And6();
		when(gameState.getVar(5)).thenReturn(7);
		when(gameState.getVar(6)).thenReturn(7);

		statementIsEvaluated();

		verify(gameState).getVar(5);
		verify(gameState).getVar(6);
		statementIsTrue();
	}

	@Test
	public void canEvaluateTrueLessnStatement() throws Exception {
		anEvaluator();
		aLessnTestForVar5();
		when(gameState.getVar(5)).thenReturn(6);
		inputList.add(7);

		statementIsEvaluated();

		verify(gameState).getVar(5);
		statementIsTrue();
	}

	@Test
	public void canEvaluateTrueLessvStatement() throws Exception {
		anEvaluator();
		aLessvTestForVar5And6();
		when(gameState.getVar(5)).thenReturn(4);
		when(gameState.getVar(6)).thenReturn(5);

		statementIsEvaluated();

		verify(gameState).getVar(5);
		verify(gameState).getVar(6);
		statementIsTrue();
	}

	@Test
	public void canEvaluateTrueGreaternStatement() throws Exception {
		anEvaluator();
		aGreaternTestForVar5();
		when(gameState.getVar(5)).thenReturn(6);
		inputList.add(5);

		statementIsEvaluated();

		verify(gameState).getVar(5);
		statementIsTrue();
	}

	@Test
	public void canEvaluateTrueGreatervStatement() throws Exception {
		anEvaluator();
		aGreatervTestForVar5And6();
		when(gameState.getVar(5)).thenReturn(6);
		when(gameState.getVar(6)).thenReturn(5);

		statementIsEvaluated();

		verify(gameState).getVar(5);
		verify(gameState).getVar(6);
		statementIsTrue();
	}

	@Test
	public void canEvaluateNegatedStatement() throws Exception {
		anEvaluator();
		inputList.add(BYTE_NOT);
		aLessnTestForVar5();
		when(gameState.getVar(5)).thenReturn(6);
		inputList.add(7);

		statementIsEvaluated();

		verify(gameState).getVar(5);
		statementIsFalse();
	}

	@Test
	public void canEvaluateTrueIssetStatement() throws Exception {
		anEvaluator();
		inputList.add(BYTE_ISSET);
		inputList.add(10);
		when(gameState.getFlag(10)).thenReturn(true);

		statementIsEvaluated();

		verify(gameState).getFlag(10);
		statementIsTrue();
	}

	@Test
	public void canEvaluateTrueIssetvStatement() throws Exception {
		anEvaluator();
		inputList.add(BYTE_ISSETV);
		inputList.add(5);
		when(gameState.getVar(5)).thenReturn(10);
		when(gameState.getFlag(10)).thenReturn(true);

		statementIsEvaluated();

		verify(gameState).getVar(5);
		verify(gameState).getFlag(10);
		statementIsTrue();
	}

	@Test
	public void canEvaluateTrueHasStatement() throws Exception {
		anEvaluator();
		inputList.add(BYTE_HAS);
		inputList.add(100);

		when(gameState.has(100)).thenReturn(true);

		statementIsEvaluated();

		verify(gameState).has(100);
		statementIsTrue();
	}

	@Test
	public void canEvaluateTrueObjInRoomStatement() throws Exception {
		int itemNo = 10;
		int roomNoVar = 20;
		int roomNo = 5;
		
		anEvaluator();
		inputList.add(BYTE_OBJINROOM);
		inputList.add(itemNo);
		inputList.add(roomNoVar);

		InventoryObject inventoryObject = new InventoryObject(roomNo,null);

		when(gameState.getInventoryObject(itemNo)).thenReturn(inventoryObject);
		when(gameState.getVar(roomNoVar)).thenReturn(roomNo);

		statementIsEvaluated();

		verify(gameState).getInventoryObject(itemNo);
		verify(gameState).getVar(roomNoVar);
		statementIsTrue();
	}

	@Test
	public void canEvaluateTrueControllerStatement() throws Exception {
		anEvaluator();
		inputList.add(BYTE_CONTROLLER);
		inputList.add(100);

		when(gameState.controller(100)).thenReturn(true);

		statementIsEvaluated();

		verify(gameState).controller(100);
		statementIsTrue();
	}

	@Test
	public void canEvaluateHaveKeyStatement() throws Exception {
		anEvaluator();
		inputList.add(BYTE_HAVEKEY);
		when(gameState.haveKey()).thenReturn(true);

		statementIsEvaluated();

		verify(gameState).haveKey();
		statementIsTrue();

	}

	@Test
	// TODO: Should check if the whole base of the view is inside the box
	public void canEvaluateTrueObjInBoxStatement() throws Exception {
		anEvaluator();
		AnimatedObject obj = mock(AnimatedObject.class);
		Position pos = mock(Position.class);

		int objNo = 10;
		when(pos.getX()).thenReturn(10);
		when(pos.getY()).thenReturn(10);

		when(obj.getCurrentPosition()).thenReturn(pos);

		when(gameState.getAnimatedObject(objNo)).thenReturn(obj);

		inputList.add(BYTE_OBJINBOX);
		inputList.add(objNo);
		inputList.add(0);
		inputList.add(0);
		inputList.add(11);
		inputList.add(11);

		statementIsEvaluated();

		verify(gameState).getAnimatedObject(objNo);
		verify(obj, atLeastOnce()).getCurrentPosition();
		verify(pos).getX();
		verify(pos).getY();

		statementIsTrue();
	}

	// @Test
	// public void canEvaluateCenterPosnStatement() throws Exception {
	// fail();
	// }
	//
	// @Test
	// public void canEvaluatePosnStatement() throws Exception {
	// fail();
	// }
	//
	// @Test
	// public void canEvaluateRightPosnStatement() throws Exception {
	// fail();
	// }

	@Test
	public void canEvaluateTrueCompareStringsStatement() throws Exception {
		anEvaluator();

		inputList.add(BYTE_COMPARESTRINGS);
		inputList.add(1);
		inputList.add(2);

		when(gameState.getString(1)).thenReturn("HELLO");
		when(gameState.getString(2)).thenReturn("HELLO");

		statementIsEvaluated();

		statementIsTrue();
	}

	@Test
	public void canEvaluateTrueMultipleWordSaidStatement() throws Exception {
		anEvaluator();
		int numberOfWords = 2;
		int firstWordNumber = 1;
		inputList.add(BYTE_SAID);
		inputList.add(numberOfWords);
		inputList.add(firstWordNumber & 0xff);
		inputList.add(firstWordNumber >> 8);
		inputList.add(firstWordNumber & 0xff);
		inputList.add(firstWordNumber >> 8);

		when(gameState.getFlag(GameEngine.FLAG_TEXT_ACCEPTED)).thenReturn(false);
		when(gameState.getLatestInput()).thenReturn("Hello Hello");
		when(gameState.getNumberForWord("Hello")).thenReturn(1);

		statementIsEvaluated();

		verify(gameState).getFlag(GameEngine.FLAG_TEXT_ACCEPTED);
		verify(gameState).getLatestInput();
		verify(gameState, times(2)).getNumberForWord("Hello");
		verify(gameState).setFlag(GameEngine.FLAG_TEXT_ACCEPTED);
		verify(gameState).setLatestInput("");

		statementIsTrue();
	}

	@Test
	public void doesNotAcceptInputIfOneWordIsUnknown() throws Exception {
		anEvaluator();
		int numberOfWords = 2;
		int firstWordNumber = 1;
		inputList.add(BYTE_SAID);
		inputList.add(numberOfWords);
		inputList.add(firstWordNumber & 0xff);
		inputList.add(firstWordNumber >> 8);
		inputList.add(firstWordNumber & 0xff);
		inputList.add(firstWordNumber >> 8);

		when(gameState.getFlag(GameEngine.FLAG_TEXT_ACCEPTED)).thenReturn(false);
		when(gameState.getLatestInput()).thenReturn("Hello Hellox");
		when(gameState.getNumberForWord("Hello")).thenReturn(1);
		when(gameState.getNumberForWord("Hellox")).thenReturn(
				WordsTok.WORD_NOT_FOUND);

		statementIsEvaluated();

		verify(gameState).getFlag(GameEngine.FLAG_TEXT_ACCEPTED);
		verify(gameState).getLatestInput();
		verify(gameState).getNumberForWord("Hello");
		verify(gameState).getNumberForWord("Hellox");
		verify(gameState, never()).setFlag(GameEngine.FLAG_TEXT_ACCEPTED);
		verify(gameState, never()).setLatestInput("");

		statementIsFalse();
	}

	@Test
	public void doesNotAcceptInputIfInputHasAlreadyBeenAccepted()
			throws Exception {
		anEvaluator();
		int numberOfWords = 1;
		int firstWordNumber = 1;
		inputList.add(BYTE_SAID);
		inputList.add(numberOfWords);
		inputList.add(firstWordNumber & 0xff);
		inputList.add(firstWordNumber >> 8);

		when(gameState.getFlag(GameEngine.FLAG_TEXT_ACCEPTED)).thenReturn(true);

		statementIsEvaluated();

		verify(gameState).getFlag(GameEngine.FLAG_TEXT_ACCEPTED);

		statementIsFalse();
	}

	@Test
	public void evaluatesSaidStatementToFalseIfWordsAreKnownButDontMatchStatement()
			throws Exception {
		anEvaluator();
		int numberOfWords = 2;
		int firstWordNumber = 1;
		inputList.add(BYTE_SAID);
		inputList.add(numberOfWords);
		inputList.add(firstWordNumber & 0xff);
		inputList.add(firstWordNumber >> 8);
		inputList.add(firstWordNumber & 0xff);
		inputList.add(firstWordNumber >> 8);

		when(gameState.getFlag(GameEngine.FLAG_TEXT_ACCEPTED)).thenReturn(false);
		when(gameState.getLatestInput()).thenReturn("Hello Hellox");
		when(gameState.getNumberForWord("Hello")).thenReturn(1);
		when(gameState.getNumberForWord("Hellox")).thenReturn(2);

		statementIsEvaluated();

		verify(gameState).getFlag(GameEngine.FLAG_TEXT_ACCEPTED);
		verify(gameState).getLatestInput();
		verify(gameState).getNumberForWord("Hello");
		verify(gameState).getNumberForWord("Hellox");
		verify(gameState, never()).setFlag(GameEngine.FLAG_TEXT_ACCEPTED);
		verify(gameState, never()).setLatestInput("");

		statementIsFalse();
	}

	@Test
	public void canEvaluateTwoStatementsOredTogether() throws Exception {
		anEvaluator();
		inputList.add(BYTE_OR);
		
		inputList.add(BYTE_ISSET);
		inputList.add(5);

		inputList.add(BYTE_ISSET);
		inputList.add(6);
		
		inputList.add(BYTE_OR);
		
		when(gameState.getFlag(5)).thenReturn(true);
		when(gameState.getFlag(6)).thenReturn(false);
		
		statementIsEvaluated();
		
		statementIsTrue();
	}
	
	@Test
	public void canEvaluateTwoStatementsAndedTogether() throws Exception {
		anEvaluator();
		inputList.add(BYTE_ISSET);
		inputList.add(5);

		inputList.add(BYTE_ISSET);
		inputList.add(6);
		
		when(gameState.getFlag(5)).thenReturn(true);
		when(gameState.getFlag(6)).thenReturn(false);
		
		statementIsEvaluated();		
		
		statementIsFalse();
	}

	private void aGreaternTestForVar5() {
		inputList.add(BYTE_GREATERN);
		inputList.add(5);
	}

	private void aGreatervTestForVar5And6() {
		inputList.add(BYTE_GREATERV);
		inputList.add(5);
		inputList.add(6);
	}

	private void aLessvTestForVar5And6() {
		inputList.add(BYTE_LESSV);
		inputList.add(5);
		inputList.add(6);
	}

	private void statementIsTrue() {
		assertTrue(statement.getValue());
	}

	private void statementIsFalse() {
		assertFalse(statement.getValue());
	}

	private void aLessnTestForVar5() {
		inputList.add(BYTE_LESSN);
		inputList.add(5);
	}

	private void anEqualnTestForVars5() {
		inputList.add(BYTE_EQUALN);
		inputList.add(5);
	}

	private void anEqualvTestForVars5And6() {
		inputList.add(BYTE_EQUALV);
		inputList.add(5);
		inputList.add(6);
	}

	private void statementIsEvaluated() {
		rawByteArray = new RawByteArray(inputList);
		statement = testStatementEvaluator.createStatementsFromBytes(
				rawByteArray, gameState);
	}

	private void aGameState() {
		gameState = mock(GameState.class);
	}

	private void anEvaluator() {
		testStatementEvaluator = new TestStatementEvaluator();
		aGameState();
		anEmptyRamByteArray();
	}

	private void anEmptyRamByteArray() {
		rawByteArray = new RawByteArray();
	}
}
