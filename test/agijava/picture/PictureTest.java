package agijava.picture;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import agijava.picture.impl.Picture;

public class PictureTest {

	private static final int DEFAULT_PICTURE_COLOR = 15;
	private static final int DEFAULT_PRIO_COLOR = 4;
	private static final int HEIGHT = 168;
	private static final int WIDTH = 160;
	private Picture picture;

	@Before
	public void setup() throws Exception {
		picture = new Picture();
	}

	@Test
	public void canBeCreated() throws Exception {
		assertNotNull(picture);
	}
	
	@Test
	public void is160PixelsWide() throws Exception {
		assertEquals(WIDTH,picture.getWidth());
	}
	
	@Test
	public void is168PixelsHigh() throws Exception {
		assertEquals(HEIGHT,picture.getHeight());
	}
	
	@Test
	public void hasDefaultPictureColor() throws Exception {
		for(int x = 0; x < WIDTH; x++) {
			for (int y= 0; y < HEIGHT; y++) {
				assertEquals(DEFAULT_PICTURE_COLOR,picture.getPictureColorAt(x, y));
			}
		}
	}
	
	@Test
	public void hasDefaultPriorityColor() throws Exception {
		for(int x = 0; x < WIDTH; x++) {
			for (int y= 0; y < HEIGHT; y++) {
				assertEquals(DEFAULT_PRIO_COLOR,picture.getPrioColorAt(x, y));
			}
		}
	}
	
	@Test
	public void canDrawPictureColorOnly() throws Exception {
		picture.setPictureDrawingEnabled(true);
		picture.setPriorityDrawingEnabled(false);
		picture.setPictureColor(10);
		picture.drawPixel(0, 0);
		assertEquals(10,picture.getPictureColorAt(0, 0));
		assertEquals(DEFAULT_PRIO_COLOR,picture.getPrioColorAt(0, 0));
	}
	
	@Test
	public void canDrawPriorityColorOnly() throws Exception {
		picture.setPictureDrawingEnabled(false);
		picture.setPriorityDrawingEnabled(true);
		picture.setPriorityColor(10);
		picture.drawPixel(0, 0);
		assertEquals(DEFAULT_PICTURE_COLOR,picture.getPictureColorAt(0, 0));
		assertEquals(10,picture.getPrioColorAt(0, 0));		
	}
	
	@Test
	public void canDrawPictureAndPriorityColorAtTheSameTime() throws Exception {
		picture.setPictureDrawingEnabled(true);
		picture.setPriorityDrawingEnabled(true);
		picture.setPictureColor(11);
		picture.setPriorityColor(10);
		picture.drawPixel(0, 0);
		assertEquals(11,picture.getPictureColorAt(0, 0));
		assertEquals(10,picture.getPrioColorAt(0, 0));				
	}
	
	@Test
	public void canFillEmptyPictureWithPictureColor() throws Exception {
		picture.setPictureDrawingEnabled(true);
		picture.setPictureColor(11);
		picture.fillFrom(0, 0);
		for(int x = 0; x < WIDTH; x++) {
			for (int y= 0; y < HEIGHT; y++) {
				assertEquals(11,picture.getPictureColorAt(x, y));
			}
		}
	}
	
	@Test
	public void doesNotOverwriteNonDefaultPicturePixelsWhenFillingWithPictureColor() throws Exception {
		picture.setPictureDrawingEnabled(true);
		picture.setPictureColor(11);
		picture.drawPixel(0, 0);
		picture.setPictureColor(12);
		picture.fillFrom(0, 0);
		picture.fillFrom(0, 1);
		
		assertEquals(11,picture.getPictureColorAt(0, 0));
	}
	
	@Test
	public void doesNotOverwriteNonDefaultPrioPixelsWhenFillingWithPriorityColorOnly() throws Exception {
		picture.setPriorityDrawingEnabled(true);
		picture.setPriorityColor(11);
		picture.drawPixel(0, 0);
		picture.setPictureColor(12);
		picture.fillFrom(0, 0);
		picture.fillFrom(0, 1);
		
		assertEquals(11,picture.getPrioColorAt(0, 0));
	}
	
	@Test
	public void doesNotFillAnyPictureOrPrioOnNonDefaultPicturePixelsWhenBothDrawingModesAreEnabled() throws Exception {
		picture.setPictureDrawingEnabled(true);
		picture.setPictureColor(11);
		picture.drawPixel(0, 0);
		
		picture.setPriorityDrawingEnabled(true);
		picture.setPriorityColor(12);
		
		picture.fillFrom(0, 0);
		picture.fillFrom(0, 1);
		
		assertEquals(11, picture.getPictureColorAt(0, 0));
		assertEquals(DEFAULT_PRIO_COLOR, picture.getPrioColorAt(0, 0));
	}
	
	@Test
	public void usesPrioFromClosestPixelWithColorBelowForDrawingSpecialPrioPixels() throws Exception {
		picture.setPriorityDrawingEnabled(true);
		picture.setPriorityColor(1);
		picture.drawPixel(0, 0);
		int prioForDrawing = picture.getPrioForDrawingAt(0, 0);
		assertEquals(DEFAULT_PRIO_COLOR,prioForDrawing);
	}
}
