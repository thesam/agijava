package agijava.picture;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import agijava.picture.impl.PictureCommandFactory;

public class PictureCommandFactoryTest {
	
	private PictureCommandFactory factory;

	@Before
	public void setup() throws Exception {
		factory = new PictureCommandFactory();
	}
	
	@Test
	public void canBeCreated() throws Exception {
		assertNotNull(factory);
	}
}
