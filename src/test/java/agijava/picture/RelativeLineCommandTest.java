package agijava.picture;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class RelativeLineCommandTest {
	@Test
	public void canBeCreated() throws Exception {
		RelativeLineCommand cmd = new RelativeLineCommand();
		assertNotNull(cmd);
	}
}
