package agijava.picture;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class PlotWithPenCommandTest {
	@Test
	public void canBeCreated() throws Exception {
		PlotWithPenCommand cmd = new PlotWithPenCommand();
		assertNotNull(cmd);
	}
}
