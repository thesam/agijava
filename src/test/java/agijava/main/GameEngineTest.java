package agijava.main;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import agijava.gui.GameGui;
import agijava.logic.Logic;
import agijava.main.GameState;

public class GameEngineTest {
	private GameEngine gameEngine;
	private GameState gameState;
	private RunningGame runningGame;
	private GameGui controller;

	@Test
	public void canBeCreated() throws Exception {
		aGameEngine();
		assertNotNull(gameEngine);
	}

	@Test
	public void returnsIfGameStateIsExited() throws Exception {
		aGameEngine();
		anExitedGameState();
		gameEngine.run();
	}

	@Test
	public void setsNewRoomFlagInGameStateWhenRun() throws Exception {
		aGameEngine();
		anExitedGameState();
		gameEngine.initGameState();
		assertTrue(gameState.flags[5]);
	}

	@Test
	public void refreshesGuiOnRunningGame() throws Exception {
		aGameEngine();
		gameEngine.tick();
		verify(runningGame).refreshGui();
	}
	
	private void aGameEngine() {
		LogicRepository logicRepository = mock(LogicRepository.class);
		Logic logic = mock(Logic.class);
		
		when(logicRepository.getLogic(0)).thenReturn(logic);
		when(logic.getNextCommand()).thenReturn(mock(LogicCommand.class));
		gameState = new GameState(logicRepository, null, null, null, null);
		runningGame = mock(RunningGame.class);
		controller = mock(GameGui.class);
		gameEngine = new GameEngine(gameState, runningGame, null, controller);
	}

	private void anExitedGameState() {
		gameState.isGameExited = true;
	}
}
