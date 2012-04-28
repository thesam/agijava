package agijava.picture;

import static org.junit.Assert.*;

import org.junit.Test;

import agijava.picture.impl.ChangePenCommand;

public class ChangePenCommandTest {
	private ChangePenCommand changePenCommand;

	@Test
	public void canBeCreated() throws Exception {
		aChangePenCommand();
	}

	private void aChangePenCommand() {
		changePenCommand = new ChangePenCommand();
	}

	@Test
	public void doesNotTryToDoAnythingToPicture() throws Exception {
		aChangePenCommand();
		changePenCommand.run(null, 0);
	}

	@Test
	public void needsArguments() throws Exception {
		aChangePenCommand();
		assertTrue(changePenCommand.needsArguments());
	}

}
