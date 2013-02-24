package agijava.picture;

import static org.junit.Assert.*;

import org.junit.Test;

public class DrawXCornerCommandTest {
	@Test
	public void canBeCreated() throws Exception {
		DrawXCornerCommand cmd = new DrawXCornerCommand();
		assertNotNull(cmd);
	}
}
