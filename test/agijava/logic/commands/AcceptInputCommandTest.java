package agijava.logic.commands;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import agijava.main.IGameState;

public class AcceptInputCommandTest {
	@Test
	public void acceptsInputWithoutArguments() throws Exception {
		IGameState state = mock(IGameState.class);
		
		AcceptInputCommand cmd = new AcceptInputCommand();
		
		cmd.execute(state);
		
		verify(state).setAcceptInput(true);
		assertEquals(0,cmd.getArgsSizeInBytes());
	}
}
