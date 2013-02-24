package agijava.gui;

import static org.junit.Assert.*;

import org.junit.Test;

import agijava.gui.PrioBuffer;

public class PrioBufferTest {
	@Test
	public void canBeCreated() throws Exception {
		PrioBuffer prioBuffer = new PrioBuffer(0,0);
		assertNotNull(prioBuffer);
	}
}
