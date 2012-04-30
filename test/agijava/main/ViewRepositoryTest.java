package agijava.main;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import agijava.main.impl.ViewRepository;

public class ViewRepositoryTest {
	@Test
	public void canBeCreated() throws Exception {
		ViewRepository repo = new ViewRepository(null);
		assertNotNull(repo);
	}
}
