package agijava.view;

import static org.junit.Assert.*;

import org.junit.Test;

import agijava.view.Cel;
import agijava.view.Loop;
import static org.mockito.Mockito.*;

public class LoopTest {
	
	@Test
	public void canAddAndGetCel() throws Exception {
		Loop loop = new Loop();
		Cel cel = mock(Cel.class);
		loop.addCel(cel);
		assertEquals(cel,loop.getCels().get(0));
	}

}
