package agijava.main;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import agijava.logic.Logic;
import agijava.main.LogicCommand;
import agijava.view.View;
import static org.mockito.Mockito.*;

public class GameStateTest {
	private GameState gameState;
	private Logic logic;
	private InventoryObjects inventoryObjects;
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
		gameState.currentLogic = logic;
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
		gameState.currentLogic = null;
		assertFalse(gameState.executeNextCommand());

	}

	@Test
	public void canSetAndGetLogic() throws Exception {
		aGameState();
		aLogic();
		gameState.currentLogic = logic;
		assertEquals(logic, gameState.currentLogic);
	}

	@Test
	public void canIncreaseLogicOffset() throws Exception {
		aGameState();
		aLogic();
		gameState.currentLogic = logic;
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
	public void canAddBackgroundViewToBuffer() throws Exception {
		aGameState();
		View view = mock(View.class);
		when(viewRepository.getView(0)).thenReturn(view);

		gameState.addBackgroundViewToBuffer(0, 0, 0, 0, 0, 0, 0);
		List<AnimatedObject> backgroundViews = gameState
				.bufferBackgroundViews;
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

//TODO: Move to AnimateObjCommandTest
	@Test
	public void canStoreAnimatedObjects() throws Exception {
		fail();
		aGameState();
//		gameState.addAnimatedObject(10);
//		AnimatedObject obj = gameState.getAnimatedObject(10);
//		assertEquals(obj.getNumber(),10);
//		assertTrue(obj.shouldBeUpdated());
//		gameState.addAnimatedObject(20);
//		Collection<AnimatedObject> animatedObjects = gameState.getAnimatedObjects();
//		assertEquals(2,animatedObjects.size());
	}
	
	@Test
	public void usesExistingObjectIfAlreadyAnimated() throws Exception {
		fail();
		aGameState();
//		gameState.addAnimatedObject(10);
//		AnimatedObject obj = gameState.getAnimatedObject(10);
//		gameState.addAnimatedObject(10);
//		AnimatedObject obj2 = gameState.getAnimatedObject(10);
//		assertEquals(obj,obj2);
	}
	
	@Test
	public void setsAnimatedObjectZeroToEgo() throws Exception {
		fail();
		aGameState();
//		gameState.addAnimatedObject(0);
//		AnimatedObject obj = gameState.getAnimatedObject(0);
//		assertTrue(obj.isEgo());
	}
	
	@Test
	public void canClearAllAnimatedObjects() throws Exception {
		fail();
		aGameState();
//		gameState.addAnimatedObject(10);
//		gameState.addAnimatedObject(20);
//		gameState.clearAnimatedObjects();
//		Collection<AnimatedObject> animatedObjects = gameState.getAnimatedObjects();
//		assertEquals(0,animatedObjects.size());
	}	
	
}
