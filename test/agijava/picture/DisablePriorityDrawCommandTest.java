package agijava.picture;

import static org.junit.Assert.*;

import org.junit.Test;

import agijava.picture.impl.DisablePriorityDrawCommand;

public class DisablePriorityDrawCommandTest {
	@Test
	public void canBeCreated() throws Exception {
		DisablePriorityDrawCommand cmd = new DisablePriorityDrawCommand();
		assertNotNull(cmd);
	}
}
