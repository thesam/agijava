package agijava.main.impl;

import java.io.IOException;
import java.util.List;

import agijava.io.ResourceDir;
import agijava.io.ResourceReference;
import agijava.main.IGameState;
import agijava.main.IViewRepository;

public class GameStateFactory {

	public static IGameState createInstance(String gameDir) throws IOException {
		ResourceDir picDir = new ResourceDir(gameDir + "picdir");
		List<ResourceReference> pictureReferences = picDir.getResourceReferences();
		
		PictureRepository pictureRepository = new PictureRepository(pictureReferences);

		ResourceDir viewDir = new ResourceDir(gameDir + "viewdir");
		List<ResourceReference> viewReferences = viewDir.getResourceReferences();
		IViewRepository viewRepository = new ViewRepository(viewReferences);
		
		ResourceDir logicDir = new ResourceDir(gameDir + "logdir");
		List<ResourceReference> logicReferences = logicDir.getResourceReferences();
		
		LogicRepository logicRepository = new LogicRepository(logicReferences);

		WordsTok wordsTok = WordsTokFactory.createInstance(gameDir + "words.tok");
		
		InventoryObjects inventory = InventoryObjectsFactory.createInstance(gameDir + "object");
		IGameState gameState = new GameState(logicRepository,pictureRepository,viewRepository,wordsTok,inventory);
		return gameState;
	}

}
