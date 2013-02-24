package agijava.logic.commands;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import agijava.main.IGameState;
import agijava.main.impl.AnimatedObject;

public class SetCelvCommandTest {
	private SetCelvCommand cmd;
	private IGameState state;
	private AnimatedObject animatedObject;

	@Test
	public void canSetCelWithVariableValue() throws Exception {
		aGameState();
		aSetCelvCommand();

		List<Integer> args = new ArrayList<Integer>();

		args.add(100);
		args.add(50);
		
		animatedObject = mock(AnimatedObject.class);
		when(state.getAnimatedObject(100)).thenReturn(animatedObject);
		when(state.getVar(50)).thenReturn(1);

		cmd.setArgs(args);
		cmd.execute(state);

		verify(state).getAnimatedObject(100);
		verify(state).getVar(50);
		verify(animatedObject).setCurrentViewCel(1);

	}

	private void aGameState() {
		state = mock(IGameState.class);
	}

	private void aSetCelvCommand() {
		cmd = new SetCelvCommand();
	}
}
