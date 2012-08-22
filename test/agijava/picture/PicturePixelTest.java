package agijava.picture;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import agijava.picture.impl.PicturePixel;

public class PicturePixelTest {
	@Test
	public void PicturePixel() throws Exception {
		PicturePixel cmd = new PicturePixel(0, 0);
		assertNotNull(cmd);
	}
}
