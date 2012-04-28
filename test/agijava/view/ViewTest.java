package agijava.view;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import agijava.view.impl.Loop;
import agijava.view.impl.View;

public class ViewTest {
	private View view;

	@Before
	public void setup() throws Exception {
		view = new View(0);
	}
	
	@Test
	public void canHaveLoopAdded() throws Exception {
		ILoop loop = new Loop();
		view.addLoop(loop);
		List<ILoop> loops = view.getLoops();
		assertEquals(loop,loops.get(0));
	}
	
	@Test
	public void canGetEntryNumber() throws Exception {
		view = new View(0);
		assertEquals(view.getNumber(),0);
		view = new View(1);
		assertEquals(view.getNumber(),1);
		view = new View(6);
		assertEquals(view.getNumber(),6);
		
	}
}
