package agijava.main;

import java.io.IOException;
import java.util.List;

import agijava.io.ResourceDir;
import agijava.io.ResourceReference;

public class GameStateFactory {

	public GameState createInstance(String gameDir) throws IOException {
		ResourceDir picDir = new ResourceDir(gameDir + "picdir");
		List<ResourceReference> pictureReferences = picDir.getResourceReferences();
		
		PictureRepository pictureRepository = new PictureRepository(pictureReferences);

		ResourceDir viewDir = new ResourceDir(gameDir + "viewdir");
		List<ResourceReference> viewReferences = viewDir.getResourceReferences();
		ViewRepository viewRepository = new ViewRepository(viewReferences);
		
		ResourceDir logicDir = new ResourceDir(gameDir + "logdir");
		List<ResourceReference> logicReferences = logicDir.getResourceReferences();
		
		LogicRepository logicRepository = new LogicRepository(logicReferences);

		WordsTok wordsTok = new WordsTokFactory().createInstance(gameDir + "words.tok");
		
		InventoryObjects inventory = InventoryObjectsFactory.createFromFile(gameDir + "object");
		GameState gameState = new GameState(logicRepository,pictureRepository,viewRepository,wordsTok,inventory);
		return gameState;
	}

}
