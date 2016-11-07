package agijava.main.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import agijava.io.LogicFactory;
import agijava.io.Resource;
import agijava.io.ResourceReaderFactory;
import agijava.io.ResourceReference;
import agijava.logic.ILogic;
import agijava.main.ILogicRepository;

public class LogicRepository implements ILogicRepository {

	private Map<Integer,ILogic> allLogic;
	
	public LogicRepository(List<ResourceReference> logicReferences) throws IOException {
		allLogic = new HashMap<Integer,ILogic>();
		for (ResourceReference resourceReference : logicReferences) {
			try {
				Resource res = ResourceReaderFactory.getInstance(resourceReference).read();
				LogicFactory logicReader = new LogicFactory(res);
				ILogic logic = logicReader.getLogic();		
				allLogic.put(resourceReference.getEntryNumber(),logic);
			} catch (Exception e) {
				System.err.println("Logic " + resourceReference.getEntryNumber() + " failed to load. TODO: Handle incorrect logic better!");
			}
		}
	}

	@Override
	public ILogic getLogic(Integer logicNo) {
		ILogic requestedLogic = allLogic.get(logicNo);
		requestedLogic.reset();
		return requestedLogic;
	}

}
