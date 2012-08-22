package agijava.main;

import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import agijava.io.ResourceReference;
import agijava.main.impl.ViewRepository;

public class ViewRepositoryTest {
	@Test
	public void canBeCreated() throws Exception {
		List<ResourceReference> refs = Collections.emptyList();
		ViewRepository repo = new ViewRepository(refs);
		assertNotNull(repo);
	}
}
