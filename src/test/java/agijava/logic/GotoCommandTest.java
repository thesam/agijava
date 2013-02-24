package agijava.logic;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import agijava.logic.commands.GotoCommand;

public class GotoCommandTest {
	@Test
	public void canBeCreated() throws Exception {
		GotoCommand cmd = new GotoCommand();
		assertNotNull(cmd);
	}
}
