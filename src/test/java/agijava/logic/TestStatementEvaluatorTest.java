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
import agijava.main.InventoryObjects;
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

	private static final int BYTE_ELSE_GOTO = 0xFE;
	private static final int BYTE_OBJINROOM = 0x0A;
	private static final int BYTE_POSN = 0x0B;
	private static final int BYTE_CONTROLLER = 0x0C;

	private static final int BYTE_HAVEKEY = 0x0D;
	private static final int BYTE_SAID = 0x0E;
	private static final int BYTE_COMPARESTRINGS = 0x0F;

	private static final int BYTE_OBJINBOX = 0x10;
	private static final int BYTE_CENTERPOSN = 0x11;
	private static final int BYTE_RIGHTPOSN = 0x12;

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

		gameState.vars[5] = 6;

		statementIsEvaluated();

		statementIsTrue();
	}

	@Test
	public void canEvaluateTrueEqualvStatement() throws Exception {
		anEvaluator();
		anEqualvTestForVars5And6();
		// when(gameState.vars[5)).thenReturn(7);
		// when(gameState.vars[6)).thenReturn(7);

		statementIsEvaluated();

		// verify(gameState).vars[5);
		// verify(gameState).vars[6);
		statementIsTrue();
	}

	@Test
	public void canEvaluateTrueLessnStatement() throws Exception {
		anEvaluator();
		aLessnTestForVar5();
		gameState.vars[5] = 6;
		inputList.add(7);

		statementIsEvaluated();

		// verify(gameState).vars[5);
		statementIsTrue();
	}

	@Test
	public void canEvaluateTrueLessvStatement() throws Exception {
		anEvaluator();
		aLessvTestForVar5And6();
		gameState.vars[5] = 4;
		gameState.vars[6] = 5;

		statementIsEvaluated();

		statementIsTrue();
	}

	@Test
	public void canEvaluateTrueGreaternStatement() throws Exception {
		anEvaluator();
		aGreaternTestForVar5();
		gameState.vars[5] = 6;
		inputList.add(5);

		statementIsEvaluated();

		statementIsTrue();
	}

	@Test
	public void canEvaluateTrueGreatervStatement() throws Exception {
		anEvaluator();
		aGreatervTestForVar5And6();
		gameState.vars[5] = 6;
		gameState.vars[6] = 5;

		statementIsEvaluated();

		statementIsTrue();
	}

	@Test
	public void canEvaluateNegatedStatement() throws Exception {
		anEvaluator();
		inputList.add(BYTE_NOT);
		aLessnTestForVar5();
		gameState.vars[5] = 6;
		inputList.add(7);

		statementIsEvaluated();

		statementIsFalse();
	}

	@Test
	public void canEvaluateTrueIssetStatement() throws Exception {
		anEvaluator();
		inputList.add(BYTE_ISSET);
		inputList.add(10);
		gameState.flags[10] = true;

		statementIsEvaluated();

		statementIsTrue();
	}

	@Test
	public void canEvaluateTrueIssetvStatement() throws Exception {
		anEvaluator();
		inputList.add(BYTE_ISSETV);
		inputList.add(5);
		gameState.vars[5] = 10;
		gameState.flags[10] = true;

		statementIsEvaluated();

		statementIsTrue();
	}

	@Test
	public void canEvaluateTrueHasStatement() throws Exception {
		anEvaluator();
		inputList.add(BYTE_HAS);
		inputList.add(100);

		InventoryObject inventoryObject = new InventoryObject(GameEngine.PLAYER_INVENTORY_ROOM, "");
		gameState.inventoryObjects.add(100, inventoryObject);

		statementIsEvaluated();

		// verify(gameState).has(100);
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

		InventoryObject inventoryObject = new InventoryObject(roomNo, null);
		gameState.inventoryObjects.add(itemNo, inventoryObject);
		gameState.vars[roomNoVar] = roomNo;

		statementIsEvaluated();

		statementIsTrue();
	}

//	@Test
//	public void canEvaluateTrueControllerStatement() throws Exception {
//		anEvaluator();
//		inputList.add(BYTE_CONTROLLER);
//		inputList.add(100);
//
//		// when(gameState.controller(100)).thenReturn(true);
//
//		statementIsEvaluated();
//
//		// verify(gameState).controller(100);
//		statementIsTrue();
//	}

	@Test
	public void canEvaluateHaveKeyStatement() throws Exception {
		anEvaluator();
		inputList.add(BYTE_HAVEKEY);
		gameState.haveKey = true;

		statementIsEvaluated();

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

		gameState.animatedObjects.put(objNo, obj);

		inputList.add(BYTE_OBJINBOX);
		inputList.add(objNo);
		inputList.add(0);
		inputList.add(0);
		inputList.add(11);
		inputList.add(11);

		statementIsEvaluated();

		verify(obj, atLeastOnce()).getCurrentPosition();
		verify(pos).getX();
		verify(pos).getY();

		statementIsTrue();
	}

//	@Test
//	public void canEvaluateCenterPosnStatement() throws Exception {
//		fail();
//	}

//	@Test
//	public void canEvaluatePosnStatement() throws Exception {
//		fail();
//	}

//	@Test
//	public void canEvaluateRightPosnStatement() throws Exception {
//		fail();
//	}

	@Test
	public void canEvaluateTrueCompareStringsStatement() throws Exception {
		anEvaluator();

		inputList.add(BYTE_COMPARESTRINGS);
		inputList.add(1);
		inputList.add(2);

		gameState.strings[1] = "HELLO";
		gameState.strings[2] = "HELLO";

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

		gameState.flags[GameEngine.FLAG_TEXT_ACCEPTED] = false;
		gameState.latestInput = "Hello Hello";
		gameState.wordsTok.addWord(1, "Hello");

		statementIsEvaluated();

		assertTrue(gameState.flags[GameEngine.FLAG_TEXT_ACCEPTED]);
		assertEquals("",gameState.latestInput);

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

		gameState.flags[GameEngine.FLAG_TEXT_ACCEPTED] = false;
		// when(gameState.getLatestInput()).thenReturn("Hello Hellox");
		// when(gameState.getNumberForWord("Hello")).thenReturn(1);
		// when(gameState.getNumberForWord("Hellox")).thenReturn(
		// WordsTok.WORD_NOT_FOUND);

		statementIsEvaluated();

		// verify(gameState).getLatestInput();
		// verify(gameState).getNumberForWord("Hello");
		// verify(gameState).getNumberForWord("Hellox");
		assertFalse(gameState.flags[GameEngine.FLAG_TEXT_ACCEPTED]);
		// verify(gameState, never()).setLatestInput("");

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

		gameState.flags[GameEngine.FLAG_TEXT_ACCEPTED] = true;

		statementIsEvaluated();

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

		gameState.flags[GameEngine.FLAG_TEXT_ACCEPTED] = false;
		// when(gameState.getLatestInput()).thenReturn("Hello Hellox");
		// when(gameState.getNumberForWord("Hello")).thenReturn(1);
		// when(gameState.getNumberForWord("Hellox")).thenReturn(2);

		statementIsEvaluated();

		// verify(gameState).getLatestInput();
		// verify(gameState).getNumberForWord("Hello");
		// verify(gameState).getNumberForWord("Hellox");
		assertFalse(gameState.flags[GameEngine.FLAG_TEXT_ACCEPTED]);
		// verify(gameState, never()).setLatestInput("");

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

		gameState.flags[5] = true;
		gameState.flags[6] = false;

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

		gameState.flags[5] = true;
		gameState.flags[6] = false;

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
		gameState = new GameState(null, null, null, new WordsTok(),
				new InventoryObjects());
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
