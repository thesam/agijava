package agijava.main;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.event.KeyEvent;

import org.junit.Before;
import org.junit.Test;

import agijava.gui.IGameGui;
import agijava.main.impl.Directions;
import agijava.main.impl.GameEngine;
import agijava.main.impl.InputListener;

public class InputListenerTest {
	private InputListener listener;
	private IGameGui gui;
	private IGameState state;
	private IAnimatedObject ego;
	private KeyEvent event;

	@Before
	public void setup() throws Exception {
		event = mock(KeyEvent.class);
		ego = mock(IAnimatedObject.class);

		gui = mock(IGameGui.class);
		when(gui.getCurrentInputLine()).thenReturn("");

		state = mock(IGameState.class);
		when(state.getAnimatedObject(0)).thenReturn(ego);
		when(state.playerControl()).thenReturn(true);

		listener = new InputListener(gui, state);
	}

	@Test
	public void canBeCreated() throws Exception {
		assertNotNull(listener);
	}

	@Test
	public void canSendTypedCharactersToGui() throws Exception {
		when(event.getKeyChar()).thenReturn('a');

		listener.keyTyped(event);

		verify(gui).appendInput("a");
	}

	@Test
	public void setsKeyPressWaitingOnKeyPress() throws Exception {
		assertFalse(listener.isKeyPressWaiting());

		listener.keyPressed(event);

		assertTrue(listener.isKeyPressWaiting());
	}

	@Test
	public void setsGameStateEgoDirectionIfArrowButtonIsPressedAndPlayerControlIsOn()
			throws Exception {
		when(event.getKeyCode()).thenReturn(KeyEvent.VK_LEFT);
		listener.keyPressed(event);
		verify(state).setVar(GameEngine.VAR_EGO_DIRECTION, Directions.WEST);

		when(event.getKeyCode()).thenReturn(KeyEvent.VK_RIGHT);
		listener.keyPressed(event);
		verify(state).setVar(GameEngine.VAR_EGO_DIRECTION, Directions.EAST);

		when(event.getKeyCode()).thenReturn(KeyEvent.VK_UP);
		listener.keyPressed(event);
		verify(state).setVar(GameEngine.VAR_EGO_DIRECTION, Directions.NORTH);

		when(event.getKeyCode()).thenReturn(KeyEvent.VK_DOWN);
		listener.keyPressed(event);
		verify(state).setVar(GameEngine.VAR_EGO_DIRECTION, Directions.SOUTH);

	}

	@Test
	public void stopsEgoIfMovingInTheSameDirectionAsArrowPressed()
			throws Exception {
		when(ego.getDirection()).thenReturn(Directions.WEST);
		when(ego.isMoving()).thenReturn(true);

		when(event.getKeyCode()).thenReturn(KeyEvent.VK_LEFT);

		listener.keyPressed(event);

		verify(ego).setMoving(false);
	}

	@Test
	public void changesGameStateEgoDirectionIfEgoIsAlreadyMoving()
			throws Exception {
		when(ego.getDirection()).thenReturn(Directions.WEST);
		when(ego.isMoving()).thenReturn(true);

		when(event.getKeyCode()).thenReturn(KeyEvent.VK_RIGHT);

		listener.keyPressed(event);

		verify(state).setVar(GameEngine.VAR_EGO_DIRECTION, Directions.EAST);
	}

	@Test
	public void stopsWaitingForKeyPressWhenEnterIsPushed() throws Exception {
		when(event.getKeyCode()).thenReturn(KeyEvent.VK_ENTER);

		listener.setWaitingForDeblockingKeyPress(true);

		assertTrue(listener.isWaitingForDeblockingKeyPress());

		listener.keyPressed(event);

		assertFalse(listener.isWaitingForDeblockingKeyPress());
	}
}
