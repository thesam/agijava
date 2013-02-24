package agijava.logic.commands;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import agijava.main.GameState;

public class AcceptInputCommandTest {
	@Test
	public void acceptsInputWithoutArguments() throws Exception {
		GameState state = mock(GameState.class);
		
		AcceptInputCommand cmd = new AcceptInputCommand();
		
		cmd.execute(state);
		
		verify(state).setAcceptInput(true);
		assertEquals(0,cmd.getArgsSizeInBytes());
	}
}
