package agijava.gui;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConsoleFontFactoryTest {
	@Test
	public void canBeCreated() throws Exception {
		ConsoleFontFactory consoleFontFactory = new ConsoleFontFactory();
		assertNotNull(consoleFontFactory);
	};

}
