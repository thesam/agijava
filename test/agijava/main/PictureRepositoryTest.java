package agijava.main;

import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import agijava.io.ResourceReference;
import agijava.main.impl.PictureRepository;

public class PictureRepositoryTest {
	@Test
	public void canBeCreated() throws Exception {
		List<ResourceReference> refs = Collections.emptyList();
		PictureRepository repo = new PictureRepository(refs);
		assertNotNull(repo);
	}
}
