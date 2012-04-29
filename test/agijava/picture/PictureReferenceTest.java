package agijava.picture;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import agijava.picture.impl.PictureReference;

public class PictureReferenceTest {
	@Test
	public void canBeCreated() throws Exception {
		PictureReference cmd = new PictureReference(0, 0, 0);
		assertNotNull(cmd);
	}
}
