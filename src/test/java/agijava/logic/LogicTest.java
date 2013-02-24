package agijava.logic;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import agijava.io.RawByteArray;
import static org.mockito.Mockito.*;

public class LogicTest {
	@Test
	public void canBeCreated() throws Exception {
		RawByteArray rawByteArray = mock(RawByteArray.class);
		Logic logic = new Logic(0, rawByteArray, null);
		assertNotNull(logic);
	}
}
