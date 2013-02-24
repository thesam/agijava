package agijava.picture;

import static org.junit.Assert.*;

import org.junit.Test;

public class DrawYCornerCommandTest {
	@Test
	public void canBeCreated() throws Exception {
		DrawYCornerCommand cmd = new DrawYCornerCommand();
		assertNotNull(cmd);
	}
}
