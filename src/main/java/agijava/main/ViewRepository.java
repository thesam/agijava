package agijava.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import agijava.io.ResourceReader;
import agijava.io.ResourceReaderFactory;
import agijava.io.ResourceReference;
import agijava.io.ViewFactory;
import agijava.view.View;

public class ViewRepository {
	
	private Map<Integer,View> allViews;

	public ViewRepository(List<ResourceReference> viewReferences) {
		allViews = new HashMap<Integer,View>();
		for (ResourceReference resourceReference : viewReferences) {
			try {
				ResourceReader resReader = ResourceReaderFactory.getInstance(resourceReference);
				ViewFactory reader = new ViewFactory(resReader.read());
				View view = reader.getView();		
				allViews.put(resourceReference.getEntryNumber(),view);
			} catch (Exception e) {
				System.err.println("TODO: Handle incorrect view better!");
			}
		}
	}

	public View getView(int viewNo) {
		View requestedView = allViews.get(viewNo);
		return requestedView;
	}

}
