package agijava.main;

import static org.mockito.Mockito.*;

import org.junit.Test;

import agijava.main.IGameState;
import agijava.main.IRunningGame;
import agijava.main.impl.GameEngine;

public class GameEngineTest {
	private GameEngine gameEngine;
	private IGameState gameState;
	private IRunningGame runningGame;

	@Test
	public void canBeCreated() throws Exception {
		aGameEngine();
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
		when(gameState.gameExited()).thenReturn(false).thenReturn(true);
		gameEngine.run();
		verify(gameState,times(2)).gameExited();
		verify(runningGame).refreshGui();
	}
	
	private void aGameEngine() {
		gameState = mock(IGameState.class);
		runningGame = mock(IRunningGame.class);
		IInputListener listener = mock(IInputListener.class);
		gameEngine = new GameEngine(gameState, runningGame, listener,null);
	}

	private void anExitedGameState() {
		when(gameState.gameExited()).thenReturn(true);
	}
}
