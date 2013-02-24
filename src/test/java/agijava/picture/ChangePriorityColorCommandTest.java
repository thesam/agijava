package agijava.picture;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class ChangePriorityColorCommandTest {
	private ChangePriorityColorCommand cmd;

	@Before
	public void setup() throws Exception {
		cmd = new ChangePriorityColorCommand();
	}
	
	@Test
	public void canBeCreated() throws Exception {
		assertNotNull(cmd);
	}
	
	@Test
	public void needsArguments() throws Exception {
		assertTrue(cmd.needsArguments());
	}
	
	@Test
	public void changesPriorityColorInPicture() throws Exception {
		Picture pic = mock(Picture.class);
		
		cmd.run(pic, 0x55);
		
		verify(pic).setPriorityColor(0x55);
	}
	
	@Test
	public void enablesPriorityDrawing() throws Exception {
		Picture pic = mock(Picture.class);
		
		cmd.run(pic, 0x55);
		
		verify(pic).setPriorityDrawingEnabled(true);
	}
}
