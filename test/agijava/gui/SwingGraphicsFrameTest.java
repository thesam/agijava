package agijava.gui;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.Font;

import org.junit.Test;

import agijava.gui.impl.SwingGraphicsFrame;

public class SwingGraphicsFrameTest {
	@Test
	public void testName() throws Exception {
		Font font = mock(Font.class);
		SwingGraphicsFrame swingGraphicsFrame = new SwingGraphicsFrame(font);
		assertNotNull(swingGraphicsFrame);
	}
}
