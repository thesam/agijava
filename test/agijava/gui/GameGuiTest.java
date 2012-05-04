package agijava.gui;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.event.KeyListener;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import agijava.gui.IGraphicsDevice;
import agijava.gui.impl.GameGui;
import agijava.gui.impl.IPrioBuffer;
import agijava.main.impl.Text;
import agijava.picture.IPicture;
import agijava.view.ICel;
import agijava.view.ILoop;
import agijava.view.IView;

public class GameGuiTest {

	private static final int MENU_BAR_HEIGHT = 8;
	private GameGui gui;
	private IGraphicsDevice graphics;
	private IPicture pic;
	private IPrioCalculator prioCalculator;
	private IView view;
	private ILoop loop;
	private ICel cel;
	private int x0;
	private int y0;
	private int prio;
	private IPrioBuffer prioBuffer;

	@Before
	public void setup() throws Exception {
		prioCalculator = mock(IPrioCalculator.class);
		graphics = mock(IGraphicsDevice.class);
		prioBuffer = mock(IPrioBuffer.class);
		pic = mock(IPicture.class);
		gui = new GameGui(graphics, prioCalculator, prioBuffer);
	}

	@Test
	public void canDrawPictureWithOneBlackPixelToGraphicsDevicePictureArea()
			throws Exception {
		aPicWithOneBlackPixel();

		pictureIsDrawn();

		firstPixelOfPictureIsAskedFor();
		firstPixelInPictureAreaOfGraphicsDeviceIsDrawnBlack();
	}

	private void firstPixelOfPictureIsAskedFor() {
		verify(pic).getPictureColorAt(0, 0);
	}

	private void pictureIsDrawn() {
		gui.drawPicture(pic);
	}

	@Test
	public void canDrawPictureWithOneWhitePixelToGraphicsDevicePictureArea()
			throws Exception {
		aPicWithOneWhitePixel();

		pictureIsDrawn();

		firstPixelOfPictureIsAskedFor();
		firstPixelInPicAreaIsDrawnWhite();
	}

	private void aPicWithOneWhitePixel() {
		aPicWithOnePixel(IGraphicsDevice.WHITE);
	}

	@Test
	public void canDrawCurrentInputLineWithPrefixAndSuffixOnRow23()
			throws Exception {
		gui.setCurrentInputLine("hej");
		gui.drawCurrentInputLine();
		verify(graphics).printText(0, 23, ">" + "hej" + "_",
				IGraphicsDevice.WHITE);
	}

	@Test
	public void drawsCelToPictureIfCelPrioIsHigherThanBackgroundPrio()
			throws Exception {
		aPicWithOneBlackPixel();
		aOnePixelWhiteCel();
		prio = 5;
		aPrioBufferThatReturns(4);

		pictureIsDrawn();
		gui.drawCel(cel, x0, y0, prio);

		firstPixelInPicAreaIsDrawnWhite();
	}

	private void aPrioBufferThatReturns(int prio) {
		when(prioBuffer.getPixel(anyInt(), anyInt())).thenReturn(prio);
	}

	@Test
	public void doesNotDrawToPictureIfCelPrioIsLowerThanBackgroundPrio()
			throws Exception {
		aPicWithOneBlackPixel();
		aOnePixelWhiteCel();
		prio = 5;
		aPrioBufferThatReturns(10);

		pictureIsDrawn();
		gui.drawCel(cel, x0, y0, prio);

		firstPixelInPictureAreaOfGraphicsDeviceIsDrawnBlack();
	}

	@Test
	public void usesCalculatedPrioIfNotSet() throws Exception {
		// given
		aPicWithOneBlackPixel();
		aOnePixelWhiteCel();
		prio = -1;
		pictureIsDrawn();
		gui.drawCel(cel, x0, y0, prio);
		// then
		prioCalculatorIsUsed();
	}

	@Test
	public void doesNotCalculatePrioIfSet() throws Exception {
		// given
		aPicWithOneBlackPixel();
		aOnePixelWhiteCel();
		prio = 5;
		pictureIsDrawn();
		gui.drawCel(cel, x0, y0, prio);
		// then
		prioCalculatorIsNotUsed();
		// firstPixelInPicIsBlack();
	}

	@Test
	public void doesNotOverwritePixelIfPixelWithHigherPrioHasAlreadyBeenDrawn()
			throws Exception {
		aPicWithOneBlackPixel();
		aOnePixelWhiteCel();
		aPrioBufferThatReturns(20);
		
		gui.drawCel(cel, 0, 0, 19);

		theFirstPixelInPictureAreaIsNotOverWritten();
	}

	private void theFirstPixelInPictureAreaIsNotOverWritten() {
		verify(graphics,never()).drawPixel(eq(0), eq(getTopYForPictureArea()), anyInt());
	}

	@Test
	public void canPrintTextDirectlyToGraphicsDevice() throws Exception {
		Text text = new Text(0, 0, "HEJ");
		gui.printText(text);
		verify(graphics).printText(0, 0, "HEJ", IGraphicsDevice.WHITE);
	}

	@Test
	public void canUpdateOnGraphicsDevice() throws Exception {
		gui.updateToScreen();
		verify(graphics).updateToScreen();
	}

	@Test
	public void canClearScreen() throws Exception {
		gui.clearScreen();
		verify(graphics).clearScreen();
	}

	@Test
	public void canSetCurrentInputLine() throws Exception {
		String foo = "foo";
		gui.setCurrentInputLine(foo);
		assertEquals(foo, gui.getCurrentInputLine());
	}

	@Test
	public void canRemoveOneCharacterFromInputLine() throws Exception {
		gui.setCurrentInputLine("aa");
		gui.chopInput();
		assertEquals("a", gui.getCurrentInputLine());
	}

	@Test
	public void doesNotRemoveAnythingFromEmptyInputLine() throws Exception {
		gui.setCurrentInputLine("");
		gui.chopInput();
		assertEquals("", gui.getCurrentInputLine());
	}

	@Test
	public void canClearInputLine() throws Exception {
		gui.setCurrentInputLine("aaa");
		gui.clearCurrentInputLine();
		assertEquals("", gui.getCurrentInputLine());
	}

	@Test
	public void canAppendToInputLine() throws Exception {
		gui.setCurrentInputLine("a");
		gui.appendInput("b");
		assertEquals("ab", gui.getCurrentInputLine());
	}

	@Test
	public void canAddKeyListenerToGraphicsDevice() throws Exception {
		KeyListener listener = mock(KeyListener.class);
		gui.addKeyListener(listener);
		verify(graphics).addKeyListener(listener);
	}

	@Test
	public void canDrawEightPixelHighStatusBar() throws Exception {
		gui.drawStatusBar();
		for (int x = 0; x < graphics.getWidth(); x++) {
			for (int y = 0; y < 8; y++) {
				verify(graphics).drawPixel(x, y, IGraphicsDevice.WHITE);
			}
		}
	}

	private void firstPixelInPicAreaIsDrawn(int colorIndex) {
		verify(graphics).drawPixel(0, getTopYForPictureArea(), colorIndex);
	}

	private void firstPixelInPicAreaIsDrawnWhite() {
		firstPixelInPicAreaIsDrawn(IGraphicsDevice.WHITE);
	}

	private int getTopYForPictureArea() {
		return MENU_BAR_HEIGHT;
	}

	private void prioCalculatorIsUsed() {
		verify(prioCalculator).getPrioBasedOnPosition(y0);
	}

	private void firstPixelInPictureAreaOfGraphicsDeviceIsDrawnBlack() {
		firstPixelInPicAreaIsDrawn(IGraphicsDevice.BLACK);
	}

	private void aOnePixelWhiteCel() {
		view = mock(IView.class);
		loop = mock(ILoop.class);
		ILoop loops[] = { loop };
		when(view.getLoops()).thenReturn(Arrays.asList(loops));
		cel = mock(ICel.class);
		ICel cels[] = { cel };
		when(loop.getCels()).thenReturn(Arrays.asList(cels));
		when(cel.getPixel(0, 0)).thenReturn(IGraphicsDevice.WHITE);
		x0 = 0;
		y0 = 1; // coordinates are at the bottom
		when(cel.getHeight()).thenReturn(1);
		when(cel.getWidth()).thenReturn(1);
	}

	private void aPicWithOneBlackPixel() {
		aPicWithOnePixel(IGraphicsDevice.BLACK);
	}

	private void aPicWithOnePixel(int colorIndex) {
		when(pic.getPictureColorAt(0, 0)).thenReturn(colorIndex);
		when(pic.getHeight()).thenReturn(1);
		when(pic.getWidth()).thenReturn(1);
	}

	private void prioCalculatorIsNotUsed() {
		verify(prioCalculator, never()).getPrioBasedOnPosition(anyInt());
	}

}
