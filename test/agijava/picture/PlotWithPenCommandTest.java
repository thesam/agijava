package agijava.picture;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import agijava.picture.impl.PlotWithPenCommand;

public class PlotWithPenCommandTest {
	@Test
	public void canBeCreated() throws Exception {
		PlotWithPenCommand cmd = new PlotWithPenCommand();
		assertNotNull(cmd);
	}
}
