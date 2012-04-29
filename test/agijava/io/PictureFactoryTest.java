package agijava.io;

import static org.junit.Assert.*;

import org.junit.Test;

public class PictureFactoryTest {
@Test
public void canBeCreated() throws Exception {
	PictureFactory pictureFactory = new PictureFactory(null);
	assertNotNull(pictureFactory);
}
}
