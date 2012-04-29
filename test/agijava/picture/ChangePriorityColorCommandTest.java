package agijava.picture;

import static org.junit.Assert.*;

import org.junit.Test;

import agijava.picture.impl.ChangePriorityColorCommand;

public class ChangePriorityColorCommandTest {
	@Test
	public void canBeCreated() throws Exception {
		ChangePriorityColorCommand cmd = new ChangePriorityColorCommand();
		assertNotNull(cmd);
	}
}
