package agijava.picture;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import agijava.picture.impl.RelativeLineCommand;

public class RelativeLineCommandTest {
	@Test
	public void canBeCreated() throws Exception {
		RelativeLineCommand cmd = new RelativeLineCommand();
		assertNotNull(cmd);
	}
}
