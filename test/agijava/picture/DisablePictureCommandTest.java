package agijava.picture;

import static org.junit.Assert.*;

import org.junit.Test;

import agijava.picture.impl.DisablePictureCommand;

public class DisablePictureCommandTest {
	@Test
	public void canBeCreated() throws Exception {
		DisablePictureCommand cmd = new DisablePictureCommand();
		assertNotNull(cmd);
	}
}
