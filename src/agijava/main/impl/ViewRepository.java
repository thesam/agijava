package agijava.main.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import agijava.io.ResourceReference;
import agijava.main.IViewRepository;
import agijava.view.IView;
import agijava.view.impl.ViewReader;

public class ViewRepository implements IViewRepository {
	
	private Map<Integer,IView> allViews;

	public ViewRepository(List<ResourceReference> viewReferences) {
		allViews = new HashMap<Integer,IView>();
		for (ResourceReference resourceReference : viewReferences) {
			try {
				ViewReader reader = new ViewReader(resourceReference);
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
