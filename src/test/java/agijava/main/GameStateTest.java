package agijava.main;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import agijava.logic.Logic;
import agijava.main.LogicCommand;
import agijava.picture.Picture;
import agijava.view.View;
import static org.mockito.Mockito.*;

public class GameStateTest {
	private GameState gameState;
	private Logic logic;
	private InventoryObjects inventoryObjects;
	private String message;
	private LogicRepository logicRepository;
	private ViewRepository viewRepository;
	private WordsTok wordsTok;
	private PictureRepository pictureRepository;

	public void aGameState() throws Exception {
		this.inventoryObjects = mock(InventoryObjects.class);
		logicRepository = mock(LogicRepository.class);
		viewRepository = mock(ViewRepository.class);
		wordsTok = mock(WordsTok.class);
		pictureRepository = mock(PictureRepository.class);
		gameState = new GameState(logicRepository, pictureRepository, viewRepository, wordsTok,
				inventoryObjects);
	}

	@Test
	public void canExecuteNextCommandFromCurrentLogic() throws Exception {
		aGameState();
		aLogic();
		gameState.setCurrentLogic(logic);
		LogicCommand command = mock(LogicCommand.class);
		when(logic.getNextCommand()).thenReturn(command);
		gameState.executeNextCommand();
		verify(command).execute(gameState);

	}

	private void aLogic() {
		logic = mock(Logic.class);
	}

	@Test
	public void returnsFalseOnExecuteWhenCurrentLogicIsNotSet()
			throws Exception {
		aGameState();
		gameState.setCurrentLogic(null);
		assertFalse(gameState.executeNextCommand());

	}

	@Test
	public void canSetAndGetLogic() throws Exception {
		aGameState();
		aLogic();
		gameState.setCurrentLogic(logic);
		assertEquals(logic, gameState.getCurrentLogic());
	}

	@Test
	public void canIncreaseLogicOffset() throws Exception {
		aGameState();
		aLogic();
		gameState.setCurrentLogic(logic);
		int offset = 100;
		gameState.jumpForward(offset);
		verify(logic).increaseOffset(100);
	}

	@Test
	public void canStoreVariableValue() throws Exception {
		aGameState();
		int varNo = 5;
		int varValue = 6;
		gameState.setVar(varNo, varValue);
		assertEquals(varValue, gameState.getVar(varNo));
	}

	@Test
	public void returnsFalseWhenAskedForController() throws Exception {
		aGameState();
		assertFalse(gameState.controller(5));
		assertFalse(gameState.controller(99));
	}

	@Test
	public void canShowMessage() throws Exception {
		aGameState();
		showsMessage();
		assertEquals(message, gameState.currentMessage);
		assertTrue(gameState.isMessageShowing());

	}

	private void showsMessage() {
		message = "Test message";
		gameState.showMessage(message);
	}

	@Test
	public void canStoreTextsToBeDisplayed() throws Exception {
		aGameState();
		String text = "foo";
		int row = 100;
		int col = 200;

		gameState.addText(row, col, text);

		List<Text> displayedTexts = gameState.displayedTexts;
		assertEquals(1, displayedTexts.size());
		Text text2 = displayedTexts.get(0);
		assertEquals(text, text2.getMessage());
		assertEquals(row, text2.getRow());
		assertEquals(col, text2.getCol());
	}

	@Test
	public void canStoreLatestInput() throws Exception {
		aGameState();
		String input = "foo";
		gameState.setLatestInput(input);
		assertEquals(input, gameState.getLatestInput());

	}

	@Test
	public void remembersLastLogicWhenNewLogicIsCalled() throws Exception {
		aGameState();
		gameState.setCurrentLogic(logic);
		Logic otherLogic = mock(Logic.class);
		when(logicRepository.getLogic(5)).thenReturn(otherLogic);
		gameState.callNewLogic(5);
		assertNotSame(logic, gameState.getCurrentLogic());
		gameState.returnToCallingLogic();
		assertEquals(logic, gameState.getCurrentLogic());
	}

	@Test
	public void canAddBackgroundViewToBuffer() throws Exception {
		aGameState();
		View view = mock(View.class);
		when(viewRepository.getView(0)).thenReturn(view);

		gameState.addBackgroundViewToBuffer(0, 0, 0, 0, 0, 0, 0);
		List<AnimatedObject> backgroundViews = gameState
				.getBufferedBackgroundViews();
		AnimatedObject animatedObject = backgroundViews.get(0);
		assertEquals(view, animatedObject.getView());
		assertEquals(animatedObject.getCurrentViewLoop(), 0);
		assertEquals(animatedObject.getCurrentViewCel(), 0);
		assertEquals(animatedObject.getCurrentPosition(), new Position(0,0));
		assertEquals(animatedObject.getPrio(), 0);
	}

	@Test
	public void looksInWordsTokWhenAskedForWordNumber() throws Exception {
		aGameState();
		gameState.getNumberForWord("hej");
		verify(wordsTok).getNumberFor("hej");
		
	}
	
	@Test
	public void looksInInventoryObjectsWhenAskedForInventoryObject() throws Exception {
		aGameState();
		gameState.getInventoryObject(555);
		verify(inventoryObjects).get(555);
	}

	@Test
	public void canStoreLatestSaidWords() throws Exception {
		aGameState();
		List<Integer> words = new ArrayList<Integer>();
		words.add(5);
		gameState.setLastSaidWords(words);
		assertEquals(words,gameState.getLatestSaidWords());
	}
	
	@Test
	public void returnsEmptyListIfNoSaidWordsAreSet() throws Exception {
		aGameState();
		List<Integer> latestSaidWords = gameState.getLatestSaidWords();
		assertTrue(latestSaidWords.isEmpty());
	}
	
	@Test
	public void canForgetLastSaidWords() throws Exception {
		aGameState();
		List<Integer> words = new ArrayList<Integer>();
		words.add(5);
		gameState.setLastSaidWords(words);
		gameState.clearLastSaidWords();
		assertTrue(gameState.getLatestSaidWords().isEmpty());
	}
	
	@Test
	public void canMovePictureInBufferToCurrentPicture() throws Exception {
		aGameState();
		Picture fakePicture = mock(Picture.class);
		when(pictureRepository.getPicture(100)).thenReturn(fakePicture);
		gameState.setPictureInBuffer(100);
		Picture currentPicture = gameState.getCurrentPicture();
		assertNull(currentPicture);
		gameState.showPictureFromBuffer();
		assertEquals(fakePicture,gameState.getCurrentPicture());
		
	}
	
//TODO: Move to AnimateObjCommandTest
//	@Test
//	public void canStoreAnimatedObjects() throws Exception {
//		aGameState();
//		gameState.addAnimatedObject(10);
//		AnimatedObject obj = gameState.getAnimatedObject(10);
//		assertEquals(obj.getNumber(),10);
//		assertTrue(obj.shouldBeUpdated());
//		gameState.addAnimatedObject(20);
//		Collection<AnimatedObject> animatedObjects = gameState.getAnimatedObjects();
//		assertEquals(2,animatedObjects.size());
//	}
	
//	@Test
//	public void usesExistingObjectIfAlreadyAnimated() throws Exception {
//		aGameState();
//		gameState.addAnimatedObject(10);
//		AnimatedObject obj = gameState.getAnimatedObject(10);
//		gameState.addAnimatedObject(10);
//		AnimatedObject obj2 = gameState.getAnimatedObject(10);
//		assertEquals(obj,obj2);
//	}
//	
//	@Test
//	public void setsAnimatedObjectZeroToEgo() throws Exception {
//		aGameState();
//		gameState.addAnimatedObject(0);
//		AnimatedObject obj = gameState.getAnimatedObject(0);
//		assertTrue(obj.isEgo());
//	}
//	
//	@Test
//	public void canClearAllAnimatedObjects() throws Exception {
//		aGameState();
//		gameState.addAnimatedObject(10);
//		gameState.addAnimatedObject(20);
//		gameState.clearAnimatedObjects();
//		Collection<AnimatedObject> animatedObjects = gameState.getAnimatedObjects();
//		assertEquals(0,animatedObjects.size());
//	}	
	@Test
	public void returnsToPreviousLogicWhenSecondLogicEnds() throws Exception {
		aGameState();
		Logic firstLogic = mock(Logic.class);
		Logic secondLogic = mock(Logic.class);
		when(logicRepository.getLogic(0)).thenReturn(firstLogic);
		when(logicRepository.getLogic(1)).thenReturn(secondLogic);
		gameState.callNewLogic(0);
		assertEquals(firstLogic,gameState.getCurrentLogic());
		gameState.callNewLogic(1);
		assertEquals(secondLogic,gameState.getCurrentLogic());
		gameState.returnToCallingLogic();
		assertEquals(firstLogic,gameState.getCurrentLogic());
		
	}
	
	@Test
	public void canListCurrentlyDisplayedBackgroundViews() throws Exception {
		aGameState();
		gameState.addBackgroundViewToBuffer(0, 0, 0, 0, 0, 0, 0);
		gameState.showPictureFromBuffer();
		List<AnimatedObject> backgroundViews = gameState.displayedBackgroundViews;
		assertEquals(1,backgroundViews.size());
	}
	
	@Test
	public void canStoreString() throws Exception {
		aGameState();
		gameState.setString(100, "test");
		assertEquals("test",gameState.getString(100));
		
	}
	
	@Test
	public void setsOffsetOnLogicIfScanStartSet() throws Exception {
		aGameState();
		aLogic();
		when(logicRepository.getLogic(100)).thenReturn(logic);
		gameState.setScanStart(100, 200);
		gameState.callNewLogic(100);
		verify(logic).setOffset(200);
	}
	
	@Test
	public void canClearDisplayedTexts() throws Exception {
		aGameState();
		gameState.addText(0, 0, "foo");
		gameState.addText(0, 0, "foo");
		assertEquals(2,gameState.displayedTexts.size());
		gameState.clearDisplayedTexts();
		assertEquals(0,gameState.displayedTexts.size());
	}
	
	@Test
	public void canClearBackgroundViews() throws Exception {
		aGameState();
		gameState.addBackgroundViewToBuffer(0, 0, 0, 0, 0, 0, 0);
		gameState.showPictureFromBuffer();
		assertEquals(1,gameState.displayedBackgroundViews.size());
		gameState.clearBackgroundViews();
		assertEquals(0,gameState.displayedBackgroundViews.size());
	}
	
	@Test
	public void canRemoveScanStart() throws Exception {
		aGameState();
		aLogic();
		when(logicRepository.getLogic(0)).thenReturn(logic);
		gameState.setScanStart(0, 200);
		gameState.callNewLogic(0);
		verify(logic).setOffset(200);
		
		gameState.resetScanStart(0);
		
		verify(logic).setOffset(anyInt());
		
	}
	
}
