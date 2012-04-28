package agijava.io;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResourceReferenceTest {

	@Test
	public void canStoreEntryNumber() throws Exception {
		ResourceReference resourceReference = new ResourceReference(5, 0, 0);
		assertEquals(5, resourceReference.getEntryNumber());
	}

	@Test
	public void canStoreVolNumber() throws Exception {
		ResourceReference resourceReference = new ResourceReference(0, 5, 0);
		assertEquals(5, resourceReference.getVolNumber());
	}

	@Test
	public void canStoreOffset() throws Exception {
		ResourceReference resourceReference = new ResourceReference(0, 0, 5);
		assertEquals(5, resourceReference.getOffset());
	}
}
