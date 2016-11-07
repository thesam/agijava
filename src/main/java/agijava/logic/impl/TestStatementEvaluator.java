package agijava.logic.impl;

import java.util.ArrayList;
import java.util.List;

import agijava.io.RawByteArray;
import agijava.logic.IEvaluatedTestStatement;
import agijava.logic.ITestStatementEvaluator;
import agijava.logic.impl.GroupedStatement.GroupType;
import agijava.main.IGameState;
import agijava.main.impl.AnimatedObject;
import agijava.main.impl.GameEngine;
import agijava.main.impl.InventoryObject;

public class TestStatementEvaluator implements ITestStatementEvaluator {

	private static final int BYTE_EQUALN = 0x01;
	private static final int BYTE_EQUALV = 0x02;
	private static final int BYTE_LESSN = 0x03;
	private static final int BYTE_LESSV = 0x04;
	private static final int BYTE_GREATERN = 0x05;
	private static final int BYTE_GREATERV = 0x06;
	private static final int BYTE_ISSET = 0x07;
	private static final int BYTE_ISSETV = 0x08;
	private static final int BYTE_HAS = 0x09;

	private static final int BYTE_NOT = 0xFD;
	@SuppressWarnings("unused")
	private static final int BYTE_ELSE_GOTO = 0xFE;
	private static final int BYTE_OBJINROOM = 0x0A;
	private static final int BYTE_CONTROLLER = 0x0C;

	private static final int BYTE_HAVEKEY = 0x0D;
	private static final int BYTE_COMPARESTRINGS = 0x0F;

	private static final int BYTE_POSN = 0x0B;
	private static final int BYTE_OBJINBOX = 0x10;
	private static final int BYTE_CENTERPOSN = 0x11;

	private static final int BYTE_RIGHTPOSN = 0x12;

	private static final int BYTE_SAID = 0x0E;
	private static final int BYTE_OR = 0xFC;

	public IEvaluatedTestStatement createStatementsFromBytes(
			RawByteArray testStatementBytes, IGameState gameState) {
		GroupedStatement allStatements = new GroupedStatement();
		GroupedStatement currentGroupOfStatements = new GroupedStatement();

		boolean insideElseBlock = false;
		while (testStatementBytes.hasMoreBytes()) {
			int nextByte = testStatementBytes.getNextAndStep();
			boolean shouldBeNegated = false;

			boolean atStartOrEndOfElseBlock = false;
			if (nextByte == BYTE_OR) {
				atStartOrEndOfElseBlock = true;
				setGroupType(currentGroupOfStatements, insideElseBlock);
				insideElseBlock = !insideElseBlock;

				if (testStatementBytes.hasMoreBytes()) { // may be at the end of the block
					nextByte = testStatementBytes.getNextAndStep();
				}
			}
			if (nextByte != BYTE_OR) {
				if (isNot(nextByte)) {
					shouldBeNegated = true;
					nextByte = testStatementBytes.getNextAndStep();
				}
				IEvaluatedTestStatement maybeAStatement = createNextStatement(
						testStatementBytes, gameState, nextByte,
						shouldBeNegated);
				if (atStartOrEndOfElseBlock) {
					allStatements.add(currentGroupOfStatements);
					currentGroupOfStatements = new GroupedStatement();
				}
				currentGroupOfStatements.add(maybeAStatement);
			}
		}
		allStatements.add(currentGroupOfStatements);
		return allStatements;
	}

	private void setGroupType(GroupedStatement currentGroupOfStatements,
			boolean insideElseBlock) {
		if (insideElseBlock) {
			// end of current else block
			currentGroupOfStatements.setGroupType(GroupType.OR);
		} else {
			// start of new else block
			currentGroupOfStatements.setGroupType(GroupType.AND);
		}
	}

	private IEvaluatedTestStatement createNextStatement(
			RawByteArray testStatementBytes, IGameState gameState,
			int statementType, boolean shouldBeNegated) {
		IEvaluatedTestStatement maybeAStatement = null;
		if (statementType == BYTE_GREATERN) {
			maybeAStatement = createGreaternStatement(testStatementBytes,
					gameState);
		} else if (statementType == BYTE_GREATERV) {
			maybeAStatement = createGreatervStatement(testStatementBytes,
					gameState);
		} else if (statementType == BYTE_EQUALV) {
			maybeAStatement = createEqualvStatement(testStatementBytes,
					gameState);
		} else if (statementType == BYTE_EQUALN) {
			maybeAStatement = createEqualnStatement(testStatementBytes,
					gameState);
		} else if (statementType == BYTE_LESSN) {
			maybeAStatement = createLessnStatement(testStatementBytes,
					gameState);
		} else if (statementType == BYTE_LESSV) {
			maybeAStatement = createLessvStatement(testStatementBytes,
					gameState);
		} else if (statementType == BYTE_ISSET) {
			maybeAStatement = createIssetStatement(testStatementBytes,
					gameState);
		} else if (statementType == BYTE_ISSETV) {
			maybeAStatement = createIssetvStatement(testStatementBytes,
					gameState);
		} else if (statementType == BYTE_HAS) {
			maybeAStatement = createHasStatement(testStatementBytes, gameState);
		} else if (statementType == BYTE_OBJINROOM) {
			maybeAStatement = createObjInRoomStatement(testStatementBytes,
					gameState);
		} else if (statementType == BYTE_CONTROLLER) {
			maybeAStatement = createControllerStatement(testStatementBytes,
					gameState);
		} else if (statementType == BYTE_HAVEKEY) {
			maybeAStatement = createHaveKeyStatement(gameState);
		} else if (statementType == BYTE_COMPARESTRINGS) {
			maybeAStatement = createCompareStringsStatement(testStatementBytes,
					gameState);
		} else if (statementType == BYTE_POSN) {
			maybeAStatement = createPosnStatement(testStatementBytes, gameState);
		} else if (statementType == BYTE_OBJINBOX) {
			maybeAStatement = createObjInBoxStatement(testStatementBytes,
					gameState);
		} else if (statementType == BYTE_CENTERPOSN) {
			maybeAStatement = createCenterPosnStatement(testStatementBytes,
					gameState);
		} else if (statementType == BYTE_RIGHTPOSN) {
			maybeAStatement = createRightPosnStatement(testStatementBytes,
					gameState);
		} else if (statementType == BYTE_SAID) {
			maybeAStatement = createSaidStatement(testStatementBytes, gameState);

		} else {
			throw new IllegalArgumentException(
					"Tried to create statement from invalid bytecode: "
							+ statementType);
		}
		if (shouldBeNegated) {
			maybeAStatement.setNegated(true);
		}
		return maybeAStatement;
	}

	//TODO: Move to new class to be able to test that it uses all bytes
	private IEvaluatedTestStatement createSaidStatement(
			RawByteArray testStatementBytes, IGameState gameState) {
		// this must always be done, since we are parsing the statement it is our responsibility
		List<Integer> wordNumbersFromStatement = getWordNumbersFromStatement(testStatementBytes);

		if (gameStateHasAlreadyAcceptedInput(gameState)) {
			return createStatement(false);
		}

		List<Integer> wordNumbersFromInput = getLatestInputWordNumbers(gameState);
		if (wordNumbersFromInput.equals(wordNumbersFromStatement)) {
			gameState.setFlag(GameEngine.FLAG_TEXT_ACCEPTED);
			gameState.setLatestInput("");
			return createStatement(true);
		} else {
			return createStatement(false);
		}
	}

	private List<Integer> getLatestInputWordNumbers(IGameState gameState) {
		String cleanInput = getLatestInputSanitizedFromGameState(gameState);
		List<Integer> wordNumbersFromInput = translateInputStringToWordNumbers(
				gameState, cleanInput);
		return wordNumbersFromInput;
	}

	private List<Integer> translateInputStringToWordNumbers(
			IGameState gameState, String cleanInput) {
		String[] words = cleanInput.split(" ");
		List<Integer> wordNumbersFromInput = new ArrayList<Integer>();
		for (int word = 0; word < words.length; word++) {
			int wordNo = gameState.getNumberForWord(words[word]);
			wordNumbersFromInput.add(wordNo);
		}
		return wordNumbersFromInput;
	}

	private List<Integer> getWordNumbersFromStatement(
			RawByteArray testStatementBytes) {
		int numberOfWords = testStatementBytes.getNextAndStep();
		List<Integer> wordNumbersFromStatement = new ArrayList<Integer>();
		for (int word = 0; word < numberOfWords; word++) {
			int word1 = testStatementBytes.getNextAndStep();
			int word2 = testStatementBytes.getNextAndStep();
			int wordNumber = (word2 << 8) | word1;
			wordNumbersFromStatement.add(wordNumber);
		}
		return wordNumbersFromStatement;
	}

	private String getLatestInputSanitizedFromGameState(IGameState gameState) {
		String latestInput = gameState.getLatestInput();
		String cleanInput = latestInput.replaceAll("[^A-Za-z ]", "");
		cleanInput = cleanInput.trim();
		return cleanInput;
	}

	private boolean gameStateHasAlreadyAcceptedInput(IGameState gameState) {
		return gameState.getFlag(GameEngine.FLAG_TEXT_ACCEPTED);
	}

	private IEvaluatedTestStatement createRightPosnStatement(
			RawByteArray testStatementBytes, IGameState gameState) {
		IEvaluatedTestStatement maybeAStatement;
		int objNo = testStatementBytes.getNextAndStep();
		int coord1 = testStatementBytes.getNextAndStep();
		int coord2 = testStatementBytes.getNextAndStep();
		int coord3 = testStatementBytes.getNextAndStep();
		int coord4 = testStatementBytes.getNextAndStep();
		maybeAStatement = createStatement(rightPosn(objNo, coord1, coord2,
				coord3, coord4));
		return maybeAStatement;
	}

	private IEvaluatedTestStatement createCenterPosnStatement(
			RawByteArray testStatementBytes, IGameState gameState) {
		IEvaluatedTestStatement maybeAStatement;
		int objNo = testStatementBytes.getNextAndStep();
		int coord1 = testStatementBytes.getNextAndStep();
		int coord2 = testStatementBytes.getNextAndStep();
		int coord3 = testStatementBytes.getNextAndStep();
		int coord4 = testStatementBytes.getNextAndStep();
		maybeAStatement = createStatement(centerPosn(objNo, coord1, coord2,
				coord3, coord4));
		return maybeAStatement;
	}

	private IEvaluatedTestStatement createObjInBoxStatement(
			RawByteArray testStatementBytes, IGameState gameState) {
		IEvaluatedTestStatement maybeAStatement;
		int objNo = testStatementBytes.getNextAndStep();
		int coord1 = testStatementBytes.getNextAndStep();
		int coord2 = testStatementBytes.getNextAndStep();
		int coord3 = testStatementBytes.getNextAndStep();
		int coord4 = testStatementBytes.getNextAndStep();
		maybeAStatement = createStatement(objInBox(objNo, coord1, coord2,
				coord3, coord4, gameState));
		return maybeAStatement;
	}

	private IEvaluatedTestStatement createPosnStatement(
			RawByteArray testStatementBytes, IGameState gameState) {
		IEvaluatedTestStatement maybeAStatement;
		int objNo = testStatementBytes.getNextAndStep();
		int coord1 = testStatementBytes.getNextAndStep();
		int coord2 = testStatementBytes.getNextAndStep();
		int coord3 = testStatementBytes.getNextAndStep();
		int coord4 = testStatementBytes.getNextAndStep();
		maybeAStatement = createStatement(posn(gameState, objNo, coord1,
				coord2, coord3, coord4));
		return maybeAStatement;
	}

	private IEvaluatedTestStatement createCompareStringsStatement(
			RawByteArray testStatementBytes, IGameState gameState) {
		IEvaluatedTestStatement maybeAStatement;
		int stringNo1 = testStatementBytes.getNextAndStep();
		int stringNo2 = testStatementBytes.getNextAndStep();
		maybeAStatement = createStatement(compareStrings(stringNo1, stringNo2,
				gameState));
		return maybeAStatement;
	}

	private IEvaluatedTestStatement createHaveKeyStatement(IGameState gameState) {
		return createStatement(gameState.haveKey());
	}

	private IEvaluatedTestStatement createControllerStatement(
			RawByteArray testStatementBytes, IGameState gameState) {
		IEvaluatedTestStatement maybeAStatement;
		int controllerNo = testStatementBytes.getNextAndStep();
		maybeAStatement = createStatement(gameState.controller(controllerNo));
		return maybeAStatement;
	}

	private IEvaluatedTestStatement createObjInRoomStatement(
			RawByteArray testStatementBytes, IGameState gameState) {
		IEvaluatedTestStatement maybeAStatement;
		int itemNo = testStatementBytes.getNextAndStep();
		int varNo = testStatementBytes.getNextAndStep();
		maybeAStatement = createStatement(objInRoom(itemNo, varNo, gameState));
		return maybeAStatement;
	}

	private IEvaluatedTestStatement createHasStatement(
			RawByteArray testStatementBytes, IGameState gameState) {
		IEvaluatedTestStatement maybeAStatement;
		int itemNo = testStatementBytes.getNextAndStep();
		maybeAStatement = createStatement(gameState.has(itemNo));
		return maybeAStatement;
	}

	private IEvaluatedTestStatement createIssetvStatement(
			RawByteArray testStatementBytes, IGameState gameState) {
		IEvaluatedTestStatement maybeAStatement;
		int flagNo = testStatementBytes.getNextAndStep();
		maybeAStatement = createStatement(gameState.getFlag(gameState
				.getVar(flagNo)));
		return maybeAStatement;
	}

	private IEvaluatedTestStatement createIssetStatement(
			RawByteArray testStatementBytes, IGameState gameState) {
		IEvaluatedTestStatement maybeAStatement;
		int flagNo = testStatementBytes.getNextAndStep();
		maybeAStatement = createStatement(gameState.getFlag(flagNo));
		return maybeAStatement;
	}

	private IEvaluatedTestStatement createLessvStatement(
			RawByteArray testStatementBytes, IGameState gameState) {
		IEvaluatedTestStatement maybeAStatement;
		int var1 = testStatementBytes.getNextAndStep();
		int var2 = testStatementBytes.getNextAndStep();
		maybeAStatement = createStatement(gameState.getVar(var1) < gameState
				.getVar(var2));
		return maybeAStatement;
	}

	private IEvaluatedTestStatement createLessnStatement(
			RawByteArray testStatementBytes, IGameState gameState) {
		IEvaluatedTestStatement maybeAStatement;
		int var = testStatementBytes.getNextAndStep();
		int num = testStatementBytes.getNextAndStep();
		maybeAStatement = createStatement(gameState.getVar(var) < num);
		return maybeAStatement;
	}

	private IEvaluatedTestStatement createEqualnStatement(
			RawByteArray testStatementBytes, IGameState gameState) {
		IEvaluatedTestStatement maybeAStatement;
		int var = testStatementBytes.getNextAndStep();
		int num = testStatementBytes.getNextAndStep();
		maybeAStatement = createStatement(gameState.getVar(var) == num);
		return maybeAStatement;
	}

	private IEvaluatedTestStatement createEqualvStatement(
			RawByteArray testStatementBytes, IGameState gameState) {
		IEvaluatedTestStatement maybeAStatement;
		int var1 = testStatementBytes.getNextAndStep();
		int var2 = testStatementBytes.getNextAndStep();
		maybeAStatement = createStatement(gameState.getVar(var1) == gameState
				.getVar(var2));
		return maybeAStatement;
	}

	private IEvaluatedTestStatement createGreatervStatement(
			RawByteArray testStatementBytes, IGameState gameState) {
		IEvaluatedTestStatement maybeAStatement;
		int var1 = testStatementBytes.getNextAndStep();
		int var2 = testStatementBytes.getNextAndStep();
		maybeAStatement = createStatement(gameState.getVar(var1) > gameState
				.getVar(var2));
		return maybeAStatement;
	}

	private IEvaluatedTestStatement createGreaternStatement(
			RawByteArray testStatementBytes, IGameState gameState) {
		IEvaluatedTestStatement maybeAStatement;
		int var = testStatementBytes.getNextAndStep();
		int num = testStatementBytes.getNextAndStep();
		maybeAStatement = createStatement(gameState.getVar(var) > num);
		return maybeAStatement;
	}

	private IEvaluatedTestStatement createStatement(boolean b) {
		IEvaluatedTestStatement stat = new EvaluatedTestStatement(b);
		return stat;
	}

	private boolean isNot(int nextByte) {
		return nextByte == BYTE_NOT;
	}

	private boolean objInRoom(int itemNo, int varNo, IGameState gameState) {
		InventoryObject inventoryObject = gameState.getInventoryObject(itemNo);
		int roomNo = gameState.getVar(varNo);
		return (inventoryObject.getRoomNumber() == roomNo);

	}

	private boolean objInBox(int objNo, int x0, int y0, int x1, int y1,
			IGameState gameState) {
		//TODO: Check that the whole view base is inside box
		AnimatedObject animatedObject = gameState.getAnimatedObject(objNo);
		int x = animatedObject.getCurrentPosition().getX();
		int y = animatedObject.getCurrentPosition().getY();
		if (x >= x0 && y >= x0 && x <= x1 && y <= y1) {
			return true;
		} else {
			return false;
		}
	}

	private boolean centerPosn(int objNo, int coord1, int coord2, int coord3,
			int coord4) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean rightPosn(int objNo, int coord1, int coord2, int coord3,
			int coord4) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean posn(IGameState gameState, int objNo, int x1, int y1,
			int x2, int y2) {
		AnimatedObject animatedObject = gameState.getAnimatedObject(objNo);
		int x = animatedObject.getCurrentPosition().getX();
		int y = animatedObject.getCurrentPosition().getY();

		// x1 <= x <= x2 and y1 <= y <= y2
		if (x1 <= x && x <= x2 && y1 <= y && y <= y2) {
			return true;
		} else {
			return false;
		}
	}

	private boolean compareStrings(int stringNo1, int stringNo2,
			IGameState gameState) {
		String string1 = gameState.getString(stringNo1);
		String string2 = gameState.getString(stringNo2);
		return string1.equals(string2);
	}
}
