package agijava.picture;

import static org.junit.Assert.*;

import org.junit.Test;

import agijava.picture.impl.Picture;

public class PictureTest {
	@Test
	public void canBeCreated() throws Exception {
		Picture cmd = new Picture();
		assertNotNull(cmd);
	}
}
