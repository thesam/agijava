package agijava.io;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ResourceTest {
	@Test
	public void canBeCreated() throws Exception {
		Resource resource = createResourceWithEntryNumber(0);
		assertNotNull(resource);
	}

	private Resource createResourceWithEntryNumber(int number) {
		return new Resource(null, number);
	}
	
	@Test
	public void canGetEntryNumber() throws Exception {
		Resource resource = createResourceWithEntryNumber(100);
		assertEquals(100,resource.getEntryNumber());
	}
	
	@Test
	public void canGetRawData() throws Exception {
		ArrayList<Integer> rawdata = new ArrayList<Integer>();
		Resource resource = new Resource(rawdata,0);
		assertEquals(rawdata,resource.getRawData());
	}
}
