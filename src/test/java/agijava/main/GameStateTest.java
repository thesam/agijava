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
	public void returnsFalseWhenAskedForController() throws Exception {
		aGameState();
		assertFalse(gameState.controller(5));
		assertFalse(gameState.controller(99));
	}


	
}
