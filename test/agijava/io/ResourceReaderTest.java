package agijava.io;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResourceReaderTest {
	@Test
	public void canBeCreated() throws Exception {
		ResourceReader resourceReader = new ResourceReader(null,null);
		assertNotNull(resourceReader);
	}
}
