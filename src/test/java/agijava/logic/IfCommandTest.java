package agijava.logic;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import agijava.io.RawByteArray;
import agijava.logic.EvaluatedTestStatement;
import agijava.main.GameState;

@SuppressWarnings("unused")
public class IfCommandTest {

	private static final int BYTE_EQUALN = 0x01;
	private static final int BYTE_EQUALV = 0x02;
	private static final int BYTE_LESSN = 0x03;
	private static final int BYTE_LESSV = 0x04;
	private static final int BYTE_GREATERN = 0x05;
	private static final int BYTE_GREATERV = 0x06;
	private static final int BYTE_ISSET = 0x07;
	private static final int BYTE_ISSETV = 0x08;
	private static final int BYTE_HAS = 0x09;
	
	private static final int BYTE_CONTROLLER = 0x0C;
	
	private static final int BYTE_SAID = 0x0E;
	
	private static final int BYTE_IF = 0xFF;
	private static final int BYTE_OR = 0xFC;
	private static final int BYTE_NOT = 0xFD;
	private IfCommand ifCommand;
	private ArrayList<Integer> args;
	private GameState gameState;
	private TestStatementEvaluator evaluator;
	private List<Integer> input;
	private EvaluatedTestStatement evaluatedTestStatement;


	public void anIfCommand() throws Exception {
		evaluator = mock(TestStatementEvaluator.class);
		evaluatedTestStatement = mock(EvaluatedTestStatement.class);
		when(evaluator.createStatementsFromBytes((RawByteArray) anyObject(),(GameState) anyObject())).thenReturn(evaluatedTestStatement);
		
		anEmptyArgumentList();
		ifCommand = new IfCommand(evaluator);
		// TODO: Boo! The Factory should create a new ifCommand every time without any resets..
		ifCommand.reset();
		gameState = mock(GameState.class);
	}

	private void anEmptyArgumentList() {
		input = new ArrayList<Integer>();
	}
	
	@Test
	public void requiresOneByteArgumentAtATime() throws Exception {
		anIfCommand();
		assertEquals(1,ifCommand.getArgsSizeInBytes());
	}
	
	@Test
	public void needsTwoBytesAfterLastIfByteInArguments() throws Exception {
		anIfCommand();
		assertFalse(ifCommand.hasAllNeededArgs());
		input.add(BYTE_IF);
		ifCommand.setArgs(input);
		assertFalse(ifCommand.hasAllNeededArgs());
		anEmptyArgumentList();
		input.add(0);
		ifCommand.setArgs(input);
		assertFalse(ifCommand.hasAllNeededArgs());
		anEmptyArgumentList();
		input.add(0);
		ifCommand.setArgs(input);
		assertTrue(ifCommand.hasAllNeededArgs());
	}
	
	@Test
	public void doesNotJumpForwardWhenStatementIsTrue() throws Exception {
		anIfCommand();
		aTrueTestStatement();
		threeArguments();
		ifCommand.execute(gameState);
//		verify(gameState,never()).jumpForward(anyInt());
		fail();
	}

	@Test
	public void jumpsForwardWhenStatementIsFalse() throws Exception {
		anIfCommand();
		aFalseTestStatement();
		threeArguments();
		ifCommand.execute(gameState);
//		verify(gameState).jumpForward(anyInt());
		fail();
		
	}
	
	private void aFalseTestStatement() {
		when(evaluatedTestStatement.getValue()).thenReturn(false);
	}

	private void aTrueTestStatement() {
		when(evaluatedTestStatement.getValue()).thenReturn(true);
	}
	
	private void threeArguments() {
		input.add(5);
		ifCommand.setArgs(input);
		ifCommand.setArgs(input);
		ifCommand.setArgs(input);
	}
	
//	@Test
//	public void canParseTrueEqualnTest() throws Exception {
//		int varNumber = 1;
//		int value = 2;
//		gameState.setVar(varNumber,value);
//		gameState.setLogicOffset(0);
//		initTestStatement(BYTE_EQUALN, varNumber, value);
//		assertThatStatementWasTrue();
//	}
//
//	private void assertThatStatementWasTrue() {
//		assertTrue(ifEvaluatedToTrue());
//	}
//	
//	@Test
//	public void canParseTrueTwoAndedGreaterThanTests()
//			throws Exception {
//		int value = 2;
//		int varNumber = 1;
//		gameState.setVar(varNumber, value+1);
//		initTestStatement(BYTE_GREATERN, varNumber, value, BYTE_GREATERN,
//				varNumber, value);
//		assertThatStatementWasTrue();
//	}
//	
//	@Test
//	public void canParseFalseTwoAndedGreaterThanTests()
//			throws Exception {
//		int realValue = 2;
//		int smallerValue = realValue - 1;
//		int biggerValue = realValue + 1;
//		int varNumber = 1;
//		gameState.setVar(varNumber, realValue);
//		initTestStatement(BYTE_GREATERN, varNumber, smallerValue, BYTE_GREATERN,
//				varNumber, biggerValue);
//		assertThatStatementWasFalse();
//	}
//	
//	@Test
//	public void canParseTwoOredFalseGreaterThanTests() throws Exception {
//		int value = 2;
//		int varNumber = 1;
//		gameState.setVar(varNumber, 1);
//		initTestStatement(BYTE_OR, BYTE_GREATERN, varNumber, value, BYTE_GREATERN,
//				varNumber, value, BYTE_OR);
//		assertThatStatementWasFalse();		
//	}
//	
//	@Test
//	public void canParseTwoOredTrueGreaterThanTests() throws Exception {
//		int value = 2;
//		int varNumber = 1;
//		gameState.setVar(varNumber, 3);
//		initTestStatement(BYTE_OR, BYTE_GREATERN, varNumber, value, BYTE_GREATERN,
//				varNumber, value, BYTE_OR);
//		assertThatStatementWasTrue();		
//	}
//	
//	@Test
//	public void canParseOneTrueOneFalseOredGreaterThanTests() throws Exception {
//		int value = 2;
//		int varNumber = 1;
//		gameState.setVar(varNumber, 1);
//		initTestStatement(BYTE_OR, BYTE_GREATERN, varNumber, value, BYTE_GREATERN,
//				varNumber, 0, BYTE_OR);
//		assertThatStatementWasTrue();		
//	}
//
//	@Test
//	public void canParseifStatementWithNegatedEqualnTest() throws Exception {
//		int value = 2;
//		int varNumber = 1;
//		initTestStatement(BYTE_NOT, BYTE_EQUALN, varNumber, value);
//		assertThatStatementWasTrue();
//	}
////	@Test
////	public void canParseTwoAndedAndNottedTests() throws Exception {
////		int value = 2;
////		int varNumber = 1;
////		initTestStatement(BYTE_NOT, BYTE_GREATERN, varNumber, value, BYTE_NOT, BYTE_GREATERN,
////				varNumber, value);
////		assertEquals("if (!(var[1] > 2) && !(var[1] > 2)) {}", parseRawData());		
////	}
//	
//	@Test
//	public void canParseIfStatementWithTrueIssetTest() throws Exception {
//		int flagNo = 5;
//		gameState.setFlag(flagNo);
//		initTestStatement(BYTE_ISSET, flagNo);
//		assertThatStatementWasTrue();
//	}
//	
//	@Test
//	public void canParseIfStatementWithFalseIssetTest() throws Exception {
//		int flagNo = 5;
//		initTestStatement(BYTE_ISSET, flagNo);
//		assertThatStatementWasFalse();
//	}
//	
//	@Test
//	public void canParseFalseIfStatementWithEqualvTest() throws Exception {
//		int firstVar = 3;
//		int secondVar = 5;
//		gameState.setVar(firstVar, 100);
//		gameState.setVar(secondVar, 99);
//		initTestStatement(BYTE_EQUALV, firstVar, secondVar);
//		assertThatStatementWasFalse();
//	}
//	
//	@Test
//	public void canParseTrueIfStatementWithEqualvTest() throws Exception {
//		int firstVar = 3;
//		int secondVar = 5;
//		gameState.setVar(firstVar, 100);
//		gameState.setVar(secondVar, 100);
//		initTestStatement(BYTE_EQUALV, firstVar, secondVar);
//		assertThatStatementWasTrue();
//	}
//	
//	@Test
//	public void canParseTrueIfStatementWithControllerTest() throws Exception {
//		int controllerNo = 5;
//		gameState.setController(controllerNo);
//		initTestStatement(BYTE_CONTROLLER, controllerNo);
//		assertThatStatementWasTrue();
//	}
//	
//	@Test
//	public void canParseFalseIfStatementWithControllerTest() throws Exception {
//		int controllerNo = 5;
//		initTestStatement(BYTE_CONTROLLER, controllerNo);
//		assertThatStatementWasFalse();
//	}
//	
//	@Test
//	public void canParseTrueIfStatementWithSaidTest() throws Exception {
//		// NOTE: One 8-bit with number of words = n
//		// then n 16-bit with word numbers
//		int numberOfWords = 2;
//		// 344 = 0x158
//		// 343 = 0x157
//		gameState.setSaid(0x158, 0x157);
//		initTestStatement(BYTE_SAID, numberOfWords, 0x58, 0x1, 0x57, 0x1);
//		assertThatStatementWasTrue();
//	}
//	
//	@Test
//	public void canParseFalseIfStatementWithSaidTest() throws Exception {
//		// NOTE: One 8-bit with number of words = n
//		// then n 16-bit with word numbers
//		int numberOfWords = 2;
//		// 344 = 0x158
//		// 343 = 0x157
//		initTestStatement(BYTE_SAID, numberOfWords, 0x58, 0x1, 0x57, 0x1);
//		assertThatStatementWasFalse();
//	}
//	
//	private boolean ifEvaluatedToTrue() {
//		ifCommand.execute(gameState);
//		if (gameState.getLogicOffset() == 0) {
//			return true;
//		}
//		return false;
//	}
//
//
//	private void feedArgsToIfCommand() {
//		for (int i = 0; i < args.size(); i++) {
//			ifCommand.setArgs(args.subList(i, i+1));
//		}
//	}
//	
////	
////	
////	@Test
////	public void canParseMixOfAndedAndOredStatements() throws Exception {
////		initRawData(BYTE_IF,BYTE_OR,BYTE_ISSET,0x05,BYTE_ISSET,0x06,BYTE_OR,BYTE_NOT,BYTE_ISSET,0x08,BYTE_IF,0,0);
////		//TODO: Investigate operator precedence, should there be more paranthesis?
//////		assertEquals("if ((LogicFunctions.isset(5) || LogicFunctions.isset(6)) && !LogicFunctions.isset(8)) {}", parseRawData());	
////		assertEquals("if (LogicFunctions.isset(5) || LogicFunctions.isset(6) && !(LogicFunctions.isset(8))) {}", parseRawData());	
////	}
////	
////
////
////
//
////
////	@Test
////	public void canParseIfStatementWithLessnTest() throws Exception {
////		int varNumber = 4;
////		int value = 6;
////		initRawData(BYTE_IF, BYTE_LESSN, varNumber, value, BYTE_IF, 0, 0);
////		assertEquals("if (" + generateVarString(varNumber) + " < " + value
////				+ ") {}", parseRawData());
////	}
////
////	@Test
////	public void canParseIfStatementWithLessvTest() throws Exception {
////		int var1 = 4;
////		int var2 = 6;
////		initRawData(BYTE_IF, BYTE_LESSV, var1, var2, BYTE_IF, 0, 0);
////		assertEquals("if (" + generateVarString(var1) + " < "
////				+ generateVarString(var2) + ") {}", parseRawData());
////	}
////
////	@Test
////	public void canParseIfStatementWithGreaternTest() throws Exception {
////		int value = 2;
////		int varNumber = 1;
////		initRawData(BYTE_IF, BYTE_GREATERN, varNumber, value, BYTE_IF, 0, 0);
////
////		assertEquals("if (var[1] > 2) {}", parseRawData());
////	}
////
////	@Test
////	public void canParseIfStatementWithGreatervTest() throws Exception {
////		// 06 greaterv 2 var var
////		int var1 = 4;
////		int var2 = 6;
////		initRawData(BYTE_IF, BYTE_GREATERV, var1, var2, BYTE_IF, 0, 0);
////		assertEquals("if (" + generateVarString(var1) + " > "
////				+ generateVarString(var2) + ") {}", parseRawData());
////	}
////
////
////	@Test
////	public void canParseIfStatementWithIssetvTest() throws Exception {
////		int varNo = 5;
////		initRawData(BYTE_IF, BYTE_ISSETV, varNo, BYTE_IF, 0, 0);
////		assertEquals("if (LogicFunctions.issetv(" + varNo + ")) {}",
////				parseRawData());
////	}
////
////	@Test
////	public void canParseIfStatementWithHasTest() throws Exception {
////		int itemNo = 5;
////		initRawData(BYTE_IF, BYTE_HAS, itemNo, BYTE_IF, 0, 0);
////		assertEquals("if (LogicFunctions.has(" + itemNo + ")) {}",
////				parseRawData());
////	}
////
////	@Test
////	public void canParseIfStatementWithObjInRoomTest() throws Exception {
////		// 0A obj.in.room 2 item var
////		int itemNo = 4;
////		int varNo = 6;
////		initRawData(BYTE_IF, BYTE_OBJINROOM, itemNo, varNo, BYTE_IF, 0, 0);
////		assertEquals("if (LogicFunctions.objInRoom(" + itemNo + ","
////				+ generateVarString(varNo) + ")) {}", parseRawData());
////	}
////
////	@Test
////	public void canParseIfStatementWithPosnTest() throws Exception {
////		int objNo = 99;
////		initRawData(BYTE_IF, BYTE_POSN, objNo, 1, 2, 3, 4, BYTE_IF, 0, 0);
////		assertEquals("if (LogicFunctions.posn(" + objNo + ",1,2,3,4" + ")) {}",
////				parseRawData());
////	}
////
////
////	@Test
////	public void canParseIfStatementWithHaveKeyTest() throws Exception {
////		initRawData(BYTE_IF, BYTE_HAVEKEY, BYTE_IF, 0, 0);
////		assertEquals("if (LogicFunctions.haveKey()) {}", parseRawData());
////	}
////
////
////	@Test
////	public void canParseIfStatementWithCompareStringsTest() throws Exception {
////		// 0F compare.strings 2 str str
////		int stringNo1 = 3;
////		int stringNo2 = 4;
////		initRawData(BYTE_IF, BYTE_COMPARESTRINGS, stringNo1, stringNo2,
////				BYTE_IF, 0, 0);
////		assertEquals("if (LogicFunctions.compareStrings(3,4)) {}",
////				parseRawData());
////	}
////
////	@Test
////	public void canParseIfStatementWithObjInBoxTest() throws Exception {
////		// 10 obj.in.box 5 obj num num num num
////		int objNo = 99;
////		initRawData(BYTE_IF, BYTE_OBJINBOX, objNo, 1, 2, 3, 4, BYTE_IF, 0, 0);
////		assertEquals("if (LogicFunctions.objInBox(" + objNo + ",1,2,3,4"
////				+ ")) {}", parseRawData());
////	}
////
////	@Test
////	public void canParseIfStatementWithCenterPosnTest() throws Exception {
////		// 11 center.posn 5 obj num num num num
////		int objNo = 99;
////		initRawData(BYTE_IF, BYTE_CENTERPOSN, objNo, 1, 2, 3, 4, BYTE_IF, 0, 0);
////		assertEquals("if (LogicFunctions.centerPosn(" + objNo + ",1,2,3,4"
////				+ ")) {}", parseRawData());
////	}
////
////	@Test
////	public void canParseIfStatementWithRightPosnTest() throws Exception {
////		// 12 right.posn 5 obj num num num num
////		int objNo = 99;
////		initRawData(BYTE_IF, BYTE_RIGHTPOSN, objNo, 1, 2, 3, 4, BYTE_IF, 0, 0);
////		assertEquals("if (LogicFunctions.rightPosn(" + objNo + ",1,2,3,4"
////				+ ")) {}", parseRawData());
////	}
//
//
//	private void initTestStatement(int ... argsj) {
//		args = new ArrayList<Integer>();
//		for (int i : argsj) {
//			args.add(i);
//		}
//		args.add(BYTE_IF);
//		args.add(1);
//		args.add(0);
//		feedArgsToIfCommand();
//		
//	}
//
}
