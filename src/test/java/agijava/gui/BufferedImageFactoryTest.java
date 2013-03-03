package agijava.gui;

import static org.junit.Assert.*;

import org.junit.Test;

public class BufferedImageFactoryTest {
	@Test
	public void canBeCreated() throws Exception {
		BufferedImageFactory bufferedImageFactory = new BufferedImageFactory();
		assertNotNull(bufferedImageFactory);
	};

}
