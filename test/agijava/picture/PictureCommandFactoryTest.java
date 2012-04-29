package agijava.picture;

import static org.junit.Assert.*;

import org.junit.Test;

import agijava.picture.impl.PictureCommandFactory;

public class PictureCommandFactoryTest {
	@Test
	public void canBeCreated() throws Exception {
		PictureCommandFactory fac = new PictureCommandFactory();
		assertNotNull(fac);
	}
}
