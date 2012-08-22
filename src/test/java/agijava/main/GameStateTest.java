package agijava.main;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import agijava.logic.ILogic;
import agijava.main.IAnimatedObject;
import agijava.main.IInventoryObjects;
import agijava.main.ILogicCommand;
import agijava.main.ILogicRepository;
import agijava.main.IPictureRepository;
import agijava.main.IViewRepository;
import agijava.main.IWordsTok;
import agijava.main.impl.AnimatedObject;
import agijava.main.impl.GameState;
import agijava.main.impl.Position;
import agijava.main.impl.Text;
import agijava.picture.IPicture;
import agijava.view.IView;
import static org.mockito.Mockito.*;

public class GameStateTest {
	private GameState gameState;
	private ILogic logic;
	private IInventoryObjects inventoryObjects;
	private String message;
	private ILogicRepository logicRepository;
	private IViewRepository viewRepository;
	private IWordsTok wordsTok;
	private IPictureRepository pictureRepository;

	public void aGameState() throws Exception {
		this.inventoryObjects = mock(IInventoryObjects.class);
		logicRepository = mock(ILogicRepository.class);
		viewRepository = mock(IViewRepository.class);
		wordsTok = mock(IWordsTok.class);
		pictureRepository = mock(IPictureRepository.class);
		gameState = new GameState(logicRepository, pictureRepository, viewRepository, wordsTok,
				inventoryObjects);
	}

	@Test
	public void canExecuteNextCommandFromCurrentLogic() throws Exception {
		aGameState();
		aLogic();
		gameState.setCurrentLogic(logic);
		ILogicCommand command = mock(ILogicCommand.class);
		when(logic.getNextCommand()).thenReturn(command);
		gameState.executeNextCommand();
		verify(command).execute(gameState);

	}

	private void aLogic() {
		logic = mock(ILogic.class);
	}

	@Test
	public void returnsFalseOnExecuteWhenCurrentLogicIsNotSet()
			throws Exception {
		aGameState();
		gameState.setCurrentLogic(null);
		assertFalse(gameState.executeNextCommand());

	}

	@Test
	public void returnsFalseWhenAskedIfGameIsExited() throws Exception {
		aGameState();
		assertFalse(gameState.isGameExited());
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
	public void canSetFlagState() throws Exception {
		aGameState();
		int flagNo = 5;
		gameState.setFlag(flagNo);
		assertTrue(gameState.getFlag(flagNo));
	}

	@Test
	public void canCheckIfPlayerHasItem() throws Exception {
		aGameState();
		int itemNo = 100;
		gameState.has(itemNo);
		verify(inventoryObjects).playerHas(itemNo);
	}

	@Test
	public void neverHasKey() throws Exception {
		aGameState();
		assertFalse(gameState.haveKey());
	}

	@Test
	public void returnsFalseWhenAskedForController() throws Exception {
		aGameState();
		assertFalse(gameState.controller(5));
		assertFalse(gameState.controller(99));
	}

	@Test
	public void canRememberThatObjectIsAnimated() throws Exception {
		aGameState();
		gameState.addAnimatedObject(10);
		IAnimatedObject animatedObject = gameState.getAnimatedObject(10);
		assertNotNull(animatedObject);
		assertNull(gameState.getAnimatedObject(9));
	}

	@Test
	public void canShowMessage() throws Exception {
		aGameState();
		showsMessage();
		assertEquals(message, gameState.getCurrentMessage());
		assertTrue(gameState.isMessageShowing());

	}

	@Test
	public void canClearMessage() throws Exception {
		aGameState();
		showsMessage();
		gameState.clearMessage();
		assertFalse(gameState.isMessageShowing());
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

		List<Text> displayedTexts = gameState.getDisplayedTexts();
		assertEquals(1, displayedTexts.size());
		Text text2 = displayedTexts.get(0);
		assertEquals(text, text2.getMessage());
		assertEquals(row, text2.getRow());
		assertEquals(col, text2.getCol());
	}

	@Test
	public void canStoreStatusLineState() throws Exception {
		aGameState();
		gameState.setStatusLineOn(true);
		assertEquals(true, gameState.isStatusLineOn());
		gameState.setStatusLineOn(false);
		assertEquals(false, gameState.isStatusLineOn());

	}

	@Test
	public void canStorePlayerControlState() throws Exception {
		aGameState();
		gameState.setPlayerControl(true);
		assertTrue(gameState.playerControl());
		gameState.setPlayerControl(false);
		assertFalse(gameState.playerControl());
	}

	@Test
	public void canResetFlag() throws Exception {
		aGameState();
		gameState.setFlag(100);
		assertTrue(gameState.getFlag(100));
		gameState.reset(100);
		assertFalse(gameState.getFlag(100));
	}

	@Test
	public void canFetchCurrentOffsetFromLogic() throws Exception {
		aGameState();
		aLogic();
		gameState.setCurrentLogic(logic);
		gameState.getLogicOffset();
		verify(logic).getCurrentOffset();
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
		ILogic otherLogic = mock(ILogic.class);
		when(logicRepository.getLogic(5)).thenReturn(otherLogic);
		gameState.callNewLogic(5);
		assertNotSame(logic, gameState.getCurrentLogic());
		gameState.returnToCallingLogic();
		assertEquals(logic, gameState.getCurrentLogic());
	}

	@Test
	public void canAddBackgroundViewToBuffer() throws Exception {
		aGameState();
		IView view = mock(IView.class);
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
	public void canStoreHorizon() throws Exception {
		aGameState();
		gameState.setHorizon(5);
		assertEquals(5,gameState.getHorizon());
		
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
	public void canSetHaveKey() throws Exception {
		aGameState();
		assertFalse(gameState.haveKey());
		gameState.setHaveKey(true);
		assertTrue(gameState.haveKey());
	}
	
	@Test
	public void canMovePictureInBufferToCurrentPicture() throws Exception {
		aGameState();
		IPicture fakePicture = mock(IPicture.class);
		when(pictureRepository.getPicture(100)).thenReturn(fakePicture);
		gameState.setPictureInBuffer(100);
		IPicture currentPicture = gameState.getCurrentPicture();
		assertNull(currentPicture);
		gameState.showPictureFromBuffer();
		assertEquals(fakePicture,gameState.getCurrentPicture());
		
	}
	
	@Test
	public void canStoreAnimatedObjects() throws Exception {
		aGameState();
		gameState.addAnimatedObject(10);
		IAnimatedObject obj = gameState.getAnimatedObject(10);
		assertEquals(obj.getNumber(),10);
		assertTrue(obj.shouldBeUpdated());
		gameState.addAnimatedObject(20);
		Collection<AnimatedObject> animatedObjects = gameState.getAnimatedObjects();
		assertEquals(2,animatedObjects.size());
	}
	
	@Test
	public void usesExistingObjectIfAlreadyAnimated() throws Exception {
		aGameState();
		gameState.addAnimatedObject(10);
		IAnimatedObject obj = gameState.getAnimatedObject(10);
		gameState.addAnimatedObject(10);
		IAnimatedObject obj2 = gameState.getAnimatedObject(10);
		assertEquals(obj,obj2);
	}
	
	@Test
	public void setsAnimatedObjectZeroToEgo() throws Exception {
		aGameState();
		gameState.addAnimatedObject(0);
		IAnimatedObject obj = gameState.getAnimatedObject(0);
		assertTrue(obj.isEgo());
	}
	
	@Test
	public void returnsToPreviousLogicWhenSecondLogicEnds() throws Exception {
		aGameState();
		ILogic firstLogic = mock(ILogic.class);
		ILogic secondLogic = mock(ILogic.class);
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
	public void canClearAllAnimatedObjects() throws Exception {
		aGameState();
		gameState.addAnimatedObject(10);
		gameState.addAnimatedObject(20);
		gameState.clearAnimatedObjects();
		Collection<AnimatedObject> animatedObjects = gameState.getAnimatedObjects();
		assertEquals(0,animatedObjects.size());
	}
	
	@Test
	public void canListCurrentlyDisplayedBackgroundViews() throws Exception {
		aGameState();
		gameState.addBackgroundViewToBuffer(0, 0, 0, 0, 0, 0, 0);
		gameState.showPictureFromBuffer();
		List<AnimatedObject> backgroundViews = gameState.getBackgroundViews();
		assertEquals(1,backgroundViews.size());
	}
	
	@Test
	public void canStoreCursorChar() throws Exception {
		aGameState();
		gameState.setCursorChar('A');
		assertEquals('A',gameState.getCursorChar());
		
	}
	
	@Test
	public void canStoreNewRoomNumber() throws Exception {
		aGameState();
		gameState.setNewRoomNumber(100);
		assertEquals(100,gameState.getNewRoomNumber());
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
	public void canSetNewRoomWaiting() throws Exception {
		aGameState();
		assertFalse(gameState.isNewRoomWaiting());
		gameState.setNewRoomWaiting(true);
		assertTrue(gameState.isNewRoomWaiting());
	}
	
	@Test
	public void canClearStackedLogics() throws Exception {
		aGameState();
		ILogic firstLogic = mock(ILogic.class);
		ILogic secondLogic = mock(ILogic.class);
		when(logicRepository.getLogic(0)).thenReturn(firstLogic);
		when(logicRepository.getLogic(1)).thenReturn(secondLogic);
		gameState.callNewLogic(0);
		gameState.callNewLogic(1);
		gameState.clearLogicStack();
		gameState.returnToCallingLogic();
		assertNull(gameState.getCurrentLogic());
		
	}
	
	@Test
	public void canClearDisplayedTexts() throws Exception {
		aGameState();
		gameState.addText(0, 0, "foo");
		gameState.addText(0, 0, "foo");
		assertEquals(2,gameState.getDisplayedTexts().size());
		gameState.clearDisplayedTexts();
		assertEquals(0,gameState.getDisplayedTexts().size());
	}
	
	@Test
	public void canClearBackgroundViews() throws Exception {
		aGameState();
		gameState.addBackgroundViewToBuffer(0, 0, 0, 0, 0, 0, 0);
		gameState.showPictureFromBuffer();
		assertEquals(1,gameState.getBackgroundViews().size());
		gameState.clearBackgroundViews();
		assertEquals(0,gameState.getBackgroundViews().size());
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
