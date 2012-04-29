package agijava.picture;

import static org.junit.Assert.*;

import org.junit.Test;

import agijava.picture.impl.FillCommand;

public class FillCommandTest {
	@Test
	public void canBeCreated() throws Exception {
		FillCommand cmd = new FillCommand();
		assertNotNull(cmd);
	}
}
