package agijava.logic.commands;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import agijava.logic.ILogic;
import agijava.main.IGameState;

public class PrintAtvCommandTest {
	@Test
	public void usesFirstParameterAsVariableForMessageNumber() throws Exception {
		PrintAtvCommand cmd = new PrintAtvCommand();
		IGameState gameState = mock(IGameState.class);
		ILogic currentLogic = mock(ILogic.class);
		List<Integer> args = new ArrayList<Integer>();
		args.add(5);
		
		cmd.setArgs(args);
		
		when(gameState.getVar(5)).thenReturn(10);
		when(gameState.getCurrentLogic()).thenReturn(currentLogic);
		when (currentLogic.getMessage(10)).thenReturn("HELLO");
		
		cmd.execute(gameState);
		
		verify(gameState).getVar(5);
		verify(gameState).getCurrentLogic();
		verify(currentLogic).getMessage(10);
		
		verify(gameState).showMessage("HELLO");
	}
}
