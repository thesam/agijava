package agijava.gui;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import agijava.gui.impl.PrioCalculator;

public class PrioCalculatorTest {
	@Test
	public void canBeCreated() throws Exception {
		PrioCalculator prioCalculator = new PrioCalculator();
		assertNotNull(prioCalculator);
	}
}
