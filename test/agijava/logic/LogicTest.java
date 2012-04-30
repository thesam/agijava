package agijava.logic;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import agijava.logic.impl.Logic;

public class LogicTest {
	@Test
	public void canBeCreated() throws Exception {
		Logic logic = new Logic(0, null, null);
		assertNotNull(logic);
	}
}
