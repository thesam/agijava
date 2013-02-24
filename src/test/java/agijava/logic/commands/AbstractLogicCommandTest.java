package agijava.logic.commands;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import agijava.main.GameState;

public class AbstractLogicCommandTest {
	private AbstractLogicCommand command;
	private int neededArgs = 0;

	@Before
	public void setup() throws Exception {
		command = new AbstractLogicCommand() {
			
			

			@Override
			public int getArgsSizeInBytes() {
				return neededArgs;
			}
			
			@Override
			public void execute(GameState gameState) {
			}
		};
	}
	
	@Test
	public void canBeCreated() throws Exception {
		assertNotNull(command);
	}
	
	@Test
	public void canSetArgs() throws Exception {
		List<Integer> args = new ArrayList<Integer>();
		command.setArgs(args);
		assertEquals(args,command.getArgs());
	}
	
	@Test
	public void canBeReset() throws Exception {
		command.reset();
		assertEquals(0,command.getArgs().size());
	}
	
	@Test
	public void canCheckIfHasAllNeededArgs() throws Exception {
		List<Integer> args = new ArrayList<Integer>();
		neededArgs = 1;
		
		assertFalse(command.hasAllNeededArgs());
		args.add(0);
		command.setArgs(args);
		assertTrue(command.hasAllNeededArgs());
	}
	
}
