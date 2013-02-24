package agijava.picture;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class FillCommandTest {
	private FillCommand cmd;

	@Before
	public void setup() throws Exception {
		cmd = new FillCommand();
	}
	
	@Test
	public void canBeCreated() throws Exception {
		assertNotNull(cmd);
	}
	
	@Test
	public void fillsPictureWhenTwoCoordinatesHaveBeenPassed() throws Exception {
		IPicture picture = mock(IPicture.class);
		
		cmd.run(picture, 1);
		cmd.run(picture, 2);
		
		verify(picture).fillFrom(1, 2);
		
	}
	
	@Test
	public void needsArguments() throws Exception {
		assertTrue(cmd.needsArguments());
	}
}
