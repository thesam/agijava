package agijava.main;

import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import agijava.io.ResourceReference;

public class LogicRepositoryTest {
	@Test
	public void canBeCreated() throws Exception {
		List<ResourceReference> refs = Collections.emptyList();
		LogicRepository repo = new LogicRepository(refs);
		assertNotNull(repo);
	}
}
