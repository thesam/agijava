package agijava.picture;

import static org.junit.Assert.*;

import org.junit.Test;

import agijava.picture.impl.PicDir;

public class PicDirTest {
	@Test
	public void canBeCreated() throws Exception {
		PicDir cmd = new PicDir(null);
		assertNotNull(cmd);
	}
}
