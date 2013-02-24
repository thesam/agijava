package agijava.io;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import agijava.view.impl.Cel;

public class CelFactoryTest {
	private CelFactory celFactory;

	@Before
	public void setup() throws Exception {
		celFactory = new CelFactory();
	}
	
	@Test
	public void canBeCreated() throws Exception {
		assertNotNull(celFactory);
	}
	
	@Test
	public void canReadEmptyZeroByZeroCel() throws Exception {
		List<Integer> celBytes = generateCelBytes(0,0);
		
		Cel cel = celFactory.getCel(celBytes, 0, 0);
		
		assertNotNull(cel);
		assertEquals(0,cel.getHeight());
		assertEquals(0,cel.getWidth());
	}
	
	@Test
	public void canReadEmptyOneByOneCel() throws Exception {
		List<Integer> celBytes = generateCelBytes(1,1);
		
		Cel cel = celFactory.getCel(celBytes, 0, 0);
		
		assertNotNull(cel);
		assertEquals(1,cel.getHeight());
		assertEquals(1,cel.getWidth());
	}

	private List<Integer> generateCelBytes(int width,int height) {
		List<Integer> celBytes = new ArrayList<Integer>();
		celBytes.add(width); // width 
		celBytes.add(height); // height
		celBytes.add(0); // transparency and mirroring
		
		celBytes.add(0); // always read!!
		return celBytes;
	}
}
