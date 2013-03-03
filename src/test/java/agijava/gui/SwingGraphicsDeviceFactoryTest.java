package agijava.gui;

import static org.junit.Assert.*;

import org.junit.Test;

public class SwingGraphicsDeviceFactoryTest {
	@Test
	public void canBeCreated() throws Exception {
		assertNotNull(new SwingGraphicsDeviceFactory());
	}
}
