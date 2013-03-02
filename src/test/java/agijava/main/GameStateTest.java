package agijava.main;

import static org.junit.Assert.*;

import org.junit.Test;

import agijava.logic.Logic;
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

	private void aLogic() {
		logic = mock(Logic.class);
	}

	@Test
	public void canSetAndGetLogic() throws Exception {
		aGameState();
		aLogic();
		gameState.currentLogic = logic;
		assertEquals(logic, gameState.currentLogic);
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
//		assertFalse(gameState.controller(5));
//		assertFalse(gameState.controller(99));
		fail();
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
