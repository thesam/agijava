package agijava.picture;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class DisablePictureCommandTest {
	
	private DisablePictureCommand cmd;

	@Before
	public void setup() throws Exception {
		cmd = new DisablePictureCommand();
	}
	
	@Test
	public void canBeCreated() throws Exception {
		assertNotNull(cmd);
	}
	
	@Test
	public void disablesPictureDrawing() throws Exception {
		IPicture picture = mock(IPicture.class);
		cmd.run(picture, 0);
		
		verify(picture).setPictureDrawingEnabled(false);
	}
}
