package agijava.view;

import static org.junit.Assert.*;

import org.junit.Test;

import agijava.view.impl.Loop;
import static org.mockito.Mockito.*;

public class LoopTest {
	
	@Test
	public void canAddAndGetCel() throws Exception {
		Loop loop = new Loop();
		ICel cel = mock(ICel.class);
		loop.addCel(cel);
		assertEquals(cel,loop.getCels().get(0));
	}

}
