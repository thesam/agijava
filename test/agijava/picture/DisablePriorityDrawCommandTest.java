package agijava.picture;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import agijava.picture.impl.DisablePictureCommand;
import agijava.picture.impl.DisablePriorityDrawCommand;

public class DisablePriorityDrawCommandTest {
	private DisablePriorityDrawCommand cmd;

	@Before
	public void setup() throws Exception {
		cmd = new DisablePriorityDrawCommand();
	}
	
	@Test
	public void canBeCreated() throws Exception {
		assertNotNull(cmd);
	}
	
	@Test
	public void disablesPriorityDrawing() throws Exception {
		IPicture picture = mock(IPicture.class);
		cmd.run(picture, 0);
		
		verify(picture).setPriorityDrawingEnabled(false);
	}
}
