package agijava.gui.impl;

import java.awt.Font;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import agijava.gui.IGraphicsDevice;

public class GraphicsDeviceFactory {

	private static final int GFX_SCREEN_X = 320;
	private static final int GFX_SCREEN_Y = 200;
	private static final int SCALE = 2;

	public static IGraphicsDevice createSwingGraphicsDevice() {
		Font font = ConsoleFontFactory.createFont();
		BufferedImage bufferImage = BufferedImageFactory.createBufferGfxImage(
				GFX_SCREEN_X, GFX_SCREEN_Y);
		JFrame frame = setupJFrame();
		Insets insets = frame.getInsets();
		int x0 = insets.left;
		int y0 = insets.top;
		return new SwingGraphicsFrame(GFX_SCREEN_X, GFX_SCREEN_Y, SCALE, font,bufferImage,frame,x0,y0);
	}

	private static JFrame setupJFrame() {
		JFrame frame = new JFrame();

		frame.setIgnoreRepaint(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(GFX_SCREEN_X * SCALE, GFX_SCREEN_Y * SCALE);

		// Important to set visible before getting inset sizes..
		frame.setVisible(true);
		Insets insets = frame.getInsets();
		int insetWide = insets.left + insets.right;
		int insetTall = insets.top + insets.bottom;
		frame.setSize(frame.getWidth() + insetWide, frame.getHeight()
				+ insetTall);
		frame.createBufferStrategy(2);

		return frame;
	}
}
