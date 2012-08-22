package agijava.main.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import agijava.io.ResourceReader;
import agijava.io.ResourceReaderFactory;
import agijava.io.ResourceReference;
import agijava.io.ViewFactory;
import agijava.main.IViewRepository;
import agijava.view.IView;

public class ViewRepository implements IViewRepository {
	
	private Map<Integer,IView> allViews;

	public ViewRepository(List<ResourceReference> viewReferences) {
		allViews = new HashMap<Integer,IView>();
		for (ResourceReference resourceReference : viewReferences) {
			try {
				ResourceReader resReader = ResourceReaderFactory.getInstance(resourceReference);
				ViewFactory reader = new ViewFactory(resReader.read());
				IView view = reader.getView();		
				allViews.put(resourceReference.getEntryNumber(),view);
			} catch (Exception e) {
				System.err.println("TODO: Handle incorrect view better!");
			}
		}
	}

	@Override
	public IView getView(int viewNo) {
		IView requestedView = allViews.get(viewNo);
		return requestedView;
	}

}
