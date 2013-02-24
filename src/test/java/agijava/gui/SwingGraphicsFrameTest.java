package agijava.gui;

import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import agijava.gui.SwingGraphicsFrame;

public class SwingGraphicsFrameTest {
	
	private SwingGraphicsFrame frame;

	@Before
	public void setup() throws Exception {
		BufferedImage img = mock(BufferedImage.class);
		frame = new SwingGraphicsFrame(1,1,1,null,img,null,0,0);
	}
	
	@Test
	public void canBeCreated() throws Exception {
		assertNotNull(frame);
	}
	
}
