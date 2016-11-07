package agijava.main;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import agijava.gui.GameGui;
import agijava.main.IGameState;

public class GameEngineTest {
	private GameEngine gameEngine;
	private IGameState gameState;
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
		gameEngine.run();
		verify(gameState).setFlag(5);
	}

	@Test
	public void callsLogic0WhenRun() throws Exception {
		aGameEngine();
		anExitedGameState();
		gameEngine.run();
		verify(gameState).callNewLogic(0);
	}
	
	@Test
	public void refreshesGuiOnRunningGame() throws Exception {
		aGameEngine();
		when(gameState.isGameExited()).thenReturn(false).thenReturn(true);
		gameEngine.run();
		verify(gameState,times(2)).isGameExited();
		verify(runningGame).refreshGui();
	}
	
	private void aGameEngine() {
		gameState = mock(IGameState.class);
		runningGame = mock(RunningGame.class);
		controller = mock(GameGui.class);
		gameEngine = new GameEngine(gameState, runningGame, null, controller);
	}

	private void anExitedGameState() {
		when(gameState.isGameExited()).thenReturn(true);
	}
}
