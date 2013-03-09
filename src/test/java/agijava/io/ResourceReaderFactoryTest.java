package agijava.io;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResourceReaderFactoryTest {
	@Test
	public void canBeCreated() throws Exception {
		assertNotNull(new ResourceReaderFactory());
	}
}
