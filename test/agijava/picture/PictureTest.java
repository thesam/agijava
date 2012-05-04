package agijava.picture;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import agijava.picture.impl.Picture;

public class PictureTest {

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
				assertEquals(15,picture.getPictureColorAt(x, y));
			}
		}
	}
	
	@Test
	public void hasDefaultPriorityColor() throws Exception {
		for(int x = 0; x < WIDTH; x++) {
			for (int y= 0; y < HEIGHT; y++) {
				assertEquals(4,picture.getPrioColorAt(x, y));
			}
		}
	}
}
