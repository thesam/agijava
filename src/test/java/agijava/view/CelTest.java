package agijava.view;

import static org.junit.Assert.*;

import org.junit.Test;

import agijava.view.Cel;

public class CelTest {
	@Test
	public void canBeCreated() throws Exception {
		Cel cel = new Cel(0, 0, 0);
		assertNotNull(cel);
	}
	
	@Test
	public void canStoreOnePixel() throws Exception {
		Cel cel = new Cel(1,1,0);
		assertEquals(-1, cel.getPixel(0, 0));
		cel.setPixel(0, 0, 5);
		assertEquals(5, cel.getPixel(0, 0));
	}
	
	@Test
	public void canHaveTransparentPixels() throws Exception {
		Cel cel = new Cel(1,1,99);
		assertFalse(cel.isTransparentAt(0, 0));
		cel.setPixel(0, 0, 99);
		assertTrue(cel.isTransparentAt(0, 0));
	}
	
	@Test
	public void canStoreDimensions() throws Exception {
		Cel cel = new Cel(7,8,0);
		assertEquals(8,cel.getHeight());
		assertEquals(7,cel.getWidth());
	}
	
	@Test
	public void canFillAllEmptyPixelsWithTransparency() throws Exception {
		Cel cel = new Cel(10,10,0);
		assertEquals(-1,cel.getPixel(0, 0));
		cel.setPixel(1, 0, 5);
		cel.fillAllEmptyPixelsWithTransparency();
		assertEquals(0,cel.getPixel(0, 0));
		assertEquals(5,cel.getPixel(1, 0));
	}
	
	@Test
	public void canGetTransparencyIndex() throws Exception {
		Cel cel = new Cel(10,10,55);
		assertEquals(55, cel.getTransparency());
	}
	
	@Test
	public void canAppendPixelsToEmptyRow() throws Exception {
		Cel cel = new Cel(3,1,55);
		cel.appendPixelsToRow(0, 33, 2);
		assertEquals(33, cel.getPixel(0, 0));
		assertEquals(33, cel.getPixel(1, 0));
		assertEquals(-1, cel.getPixel(2, 0));
	}
	
	@Test
	public void canAppendPixelsToRowWithContent() throws Exception {
		Cel cel = new Cel(3,1,55);
		cel.setPixel(0, 0, 37);
		cel.appendPixelsToRow(0, 33, 2);
		assertEquals(37, cel.getPixel(0, 0));
		assertEquals(33, cel.getPixel(1, 0));
		assertEquals(33, cel.getPixel(2, 0));
	}
	
}
