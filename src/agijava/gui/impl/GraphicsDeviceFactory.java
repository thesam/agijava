package agijava.gui.impl;

import agijava.gui.IGraphicsDevice;

public class GraphicsDeviceFactory {
	public static IGraphicsDevice createSwingGraphicsDevice() {
		return new SwingGraphicsFrame(ConsoleFontFactory.createFont());
	}
}
