package agijava.io;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import static org.mockito.Mockito.*;

import agijava.io.ResourceReference;
import agijava.io.ViewBuilder;
import agijava.view.IView;

public class ViewBuilderTest {

	private ViewBuilder viewReader;
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

	private ViewBuilder aViewReader() throws IOException {
		Resource res = mock(Resource.class);
		viewReader = new ViewBuilder(res);
		return viewReader;
	}
}
