package agijava.io;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import agijava.io.ResourceReference;
import agijava.io.ViewFactory;
import agijava.view.IView;

public class ViewFactoryTest {

	private static final int IGNORED_BYTE = 0xFF;
	private ViewFactory factory;
	private Resource res;
	private List<Integer> resourceBytes;
	
	@Before
	public void setup() throws Exception {
		aViewBuilder();
		resourceBytes = new ArrayList<Integer>();
		when(res.getRawData()).thenReturn(resourceBytes);
		
	}

	@Test
	public void canBeCreated() throws Exception {
		aViewBuilder();

		assertNotNull(factory);
	}

	@Test
	public void canBuildViewFromResourceWithNoLoop() throws Exception {
		populateByteListForResource(0);


		IView view = factory.getView();

		assertNotNull(view);
		assertEquals(0, view.getLoops().size());
	}

	@Test
	public void canBuildViewFromResourceWithOneLoopAndNoCel() throws Exception {
		populateByteListForResource(1);

		IView view = factory.getView();

		assertNotNull(view);
		assertNotNull(view.getLoops());
		assertEquals(1, view.getLoops().size());
		assertEquals(0, view.getLoops().get(0).getCels().size());
	}

	private void populateByteListForResource(int numberOfLoops) {
		int offset = 0;

		offset++;
		resourceBytes.add(IGNORED_BYTE);

		offset++;
		resourceBytes.add(IGNORED_BYTE);

		offset++;
		resourceBytes.add(numberOfLoops);

		offset++;
		resourceBytes.add(IGNORED_BYTE);

		offset++;
		resourceBytes.add(IGNORED_BYTE);

		offset++;
		offset++;
		resourceBytes.add(offset & 0x0f); // loop offset LSB
		resourceBytes.add((offset >> 8) & 0x0f); // loop offset MSB

		resourceBytes.add(0); // number of cels
		offset++;
	}

	private ViewFactory aViewBuilder() throws IOException {
		res = mock(Resource.class);
		factory = new ViewFactory(res);
		return factory;
	}
}
