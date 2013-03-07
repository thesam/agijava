package agijava.logic.commands;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import agijava.main.AnimatedObject;
import agijava.main.GameState;

public class SetCelvCommandTest {
	private SetCelvCommand cmd;
	private GameState state;
	private AnimatedObject animatedObject;

	@Test
	public void canSetCelWithVariableValue() throws Exception {
		aGameState();
		aSetCelvCommand();

		List<Integer> args = new ArrayList<Integer>();

		args.add(100);
		args.add(50);
		
		animatedObject = mock(AnimatedObject.class);
		state.animatedObjects.put(100, animatedObject);
		state.vars[50] = 1;

		cmd.setArgs(args);
		cmd.execute(state);

		verify(animatedObject).setCurrentViewCel(1);

	}

	private void aGameState() {
		state = new GameState(null, null, null, null, null);
	}

	private void aSetCelvCommand() {
		cmd = new SetCelvCommand();
	}
}
