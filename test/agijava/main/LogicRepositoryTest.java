package agijava.main;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import agijava.main.impl.LogicRepository;

public class LogicRepositoryTest {
	@Test
	public void canBeCreated() throws Exception {
		LogicRepository repo = new LogicRepository(null);
		assertNotNull(repo);
	}
}
