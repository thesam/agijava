package agijava.main;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import agijava.gui.GameGui;
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
		gameEngine.run();
		assertTrue(gameState.flags[5]);
	}

	@Test
	public void refreshesGuiOnRunningGame() throws Exception {
		aGameEngine();
		gameEngine.run();
		verify(runningGame).refreshGui();
	}
	
	private void aGameEngine() {
		gameState = new GameState(null, null, null, null, null);
//		gameState.flags = new boolean[255];
		runningGame = mock(RunningGame.class);
		controller = mock(GameGui.class);
		gameEngine = new GameEngine(gameState, runningGame, null, controller);
	}

	private void anExitedGameState() {
		gameState.isGameExited = true;
	}
}
