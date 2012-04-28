package agijava.io;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import static org.mockito.Mockito.*;

import agijava.io.ResourceReference;
import agijava.io.ViewReader;
import agijava.view.IView;

public class ViewReaderTest {

	private ViewReader viewReader;
	private ResourceReader resourceReader;

	@Test
	public void canBeCreated() throws Exception {
		aViewReader();
		
		assertNotNull(viewReader);
	}

	@Test
	public void canGetView() throws Exception {
		aViewReader();
		
		IView view = viewReader.getView();
		
		assertNotNull(view);
	}

	private ViewReader aViewReader() throws IOException {
		Resource res = mock(Resource.class);
		viewReader = new ViewReader(res);
		return viewReader;
	}
}
