package agijava.gui;

import java.awt.image.BufferedImage;

import org.junit.Test;
import static org.mockito.Mockito.*;

import agijava.gui.impl.SwingGraphicsFrame;

public class SwingGraphicsFrameTest {
	@Test
	public void canBeCreated() throws Exception {
		BufferedImage img = mock(BufferedImage.class);
		new SwingGraphicsFrame(1,1,1,null,img,null,0,0);
	}
}
