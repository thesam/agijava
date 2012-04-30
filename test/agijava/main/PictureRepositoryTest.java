package agijava.main;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import agijava.main.impl.PictureRepository;

public class PictureRepositoryTest {
	@Test
	public void canBeCreated() throws Exception {
		PictureRepository repo = new PictureRepository(null);
		assertNotNull(repo);
	}
}
