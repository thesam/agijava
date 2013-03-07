package agijava.logic.commands;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import agijava.main.AnimatedObject;
import agijava.main.GameState;

public class AnimateObjCommandTest {

	private AnimateObjCommand cmd;
	private ArrayList<Integer> args;
	private GameState gameState;

	@Before
	public void setup() {
		args = new ArrayList<Integer>();
		cmd = new AnimateObjCommand();
		gameState = new GameState();
	}

	@Test
	public void canStoreAnimatedObjects() throws Exception {
		argsForObject(0);

		assertEquals(0, gameState.animatedObjects.size());
		commandIsExecuted();
		assertEquals(1, gameState.animatedObjects.size());
	}

	private void commandIsExecuted() {
		cmd.execute(gameState);
	}

	private void argsForObject(int i) {
		args.add(i);
		cmd.setArgs(args);
	}

	@Test
	public void usesExistingObjectIfAlreadyAnimated() throws Exception {
		argsForObject(0);
		commandIsExecuted();
		AnimatedObject obj1 = gameState.animatedObjects.get(0);
		
		argsForObject(0);
		commandIsExecuted();
		AnimatedObject obj2 = gameState.animatedObjects.get(0);

		assertEquals(obj1, obj2);
	}

	@Test
	public void setsAnimatedObjectZeroToEgo() throws Exception {
		argsForObject(0);
		commandIsExecuted();
		assertTrue(gameState.animatedObjects.get(0).isEgo());
	}

}
