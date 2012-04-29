package agijava.view;

import static org.junit.Assert.*;

import org.junit.Test;

import agijava.view.impl.Cel;

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
}
