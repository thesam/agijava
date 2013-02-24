package agijava.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import agijava.io.LogicFactory;
import agijava.io.Resource;
import agijava.io.ResourceReaderFactory;
import agijava.io.ResourceReference;
import agijava.logic.Logic;

public class LogicRepository {

	private Map<Integer,Logic> allLogic;
	
	public LogicRepository(List<ResourceReference> logicReferences) throws IOException {
		allLogic = new HashMap<Integer,Logic>();
		for (ResourceReference resourceReference : logicReferences) {
			try {
				Resource res = ResourceReaderFactory.getInstance(resourceReference).read();
				LogicFactory logicReader = new LogicFactory(res);
				Logic logic = logicReader.getLogic();		
				allLogic.put(resourceReference.getEntryNumber(),logic);
			} catch (Exception e) {
				System.err.println("Logic " + resourceReference.getEntryNumber() + " failed to load. TODO: Handle incorrect logic better!");
			}
		}
	}

	public Logic getLogic(Integer logicNo) {
		Logic requestedLogic = allLogic.get(logicNo);
		requestedLogic.reset();
		return requestedLogic;
	}

}
