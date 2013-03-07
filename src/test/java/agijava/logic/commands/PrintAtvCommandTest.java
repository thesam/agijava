package agijava.logic.commands;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import agijava.logic.Logic;
import agijava.main.GameState;

public class PrintAtvCommandTest {
	@Test
	public void usesFirstParameterAsVariableForMessageNumber() throws Exception {
		PrintAtvCommand cmd = new PrintAtvCommand();
		GameState gameState = new GameState(null, null, null, null, null);
		Logic currentLogic = mock(Logic.class);
		List<Integer> args = new ArrayList<Integer>();
		args.add(5);

		cmd.setArgs(args);

		gameState.vars[5] = 10;
		gameState.currentLogic = currentLogic;
		when(currentLogic.getMessage(10)).thenReturn("HELLO");

		cmd.execute(gameState);

		assertTrue(gameState.messageShown);
		assertEquals("HELLO",gameState.currentMessage);
	}
}
