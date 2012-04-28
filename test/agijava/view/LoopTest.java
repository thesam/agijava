package agijava.view;

import static org.junit.Assert.*;

import org.junit.Test;

import agijava.view.impl.Cel;
import agijava.view.impl.Loop;

public class LoopTest {
	
	@Test
	public void canAddAndGetCel() throws Exception {
		Loop loop = new Loop();
		Cel cel = new Cel(0,0);
		loop.addCel(cel);
		assertEquals(cel,loop.getCels().get(0));
	}

}
