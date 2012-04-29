package agijava.main;


import static org.junit.Assert.*;

import org.junit.Test;

import agijava.main.impl.ObjectUpdater;

public class ObjectUpdaterTest {
	private ObjectUpdater objectUpdater;

	@Test
	public void canBeCreated() throws Exception {		objectUpdater = new ObjectUpdater(null, null);
		assertNotNull(objectUpdater);
	}
	
}
