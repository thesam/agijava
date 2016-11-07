package agijava.logic.commands;

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
		GameState gameState = mock(GameState.class);
		Logic currentLogic = mock(Logic.class);
		List<Integer> args = new ArrayList<Integer>();
		args.add(5);
		
		cmd.setArgs(args);
		
//		when(gameState.vars[5)).thenReturn(10);
//		when(gameState.currentLogic).thenReturn(currentLogic);
		when (currentLogic.getMessage(10)).thenReturn("HELLO");
		
		cmd.execute(gameState);
		
//		verify(gameState).vars[5);
//		verify(gameState).getCurrentLogic();
		verify(currentLogic).getMessage(10);
		
//		verify(gameState).showMessage("HELLO");
	}
}
