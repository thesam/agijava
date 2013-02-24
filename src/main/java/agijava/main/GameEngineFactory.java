package agijava.main;

import java.io.IOException;

import agijava.gui.GameGui;
import agijava.gui.PrioBuffer;
import agijava.gui.PrioCalculator;
import agijava.gui.SwingGraphicsDeviceFactory;
import agijava.gui.SwingGraphicsFrame;

public class GameEngineFactory {

	public static GameEngine createInstance(String gameDir) throws IOException {
			
			SwingGraphicsDeviceFactory graphicsDeviceFactory = new SwingGraphicsDeviceFactory();
			IGameState gameState = GameStateFactory.createInstance(gameDir);
			SwingGraphicsFrame guiView = graphicsDeviceFactory.createGraphicsDevice();
			GameGui gui = new GameGui(new PrioCalculator(), gameState,guiView,new PrioBuffer(guiView.getHeight(),guiView.getWidth()));
			guiView.setController(gui);
			
			GuiDrawer guiDrawer = new GuiDrawer(gameState, gui);
			MovementCalculator calculator = new MovementCalculator();
			ObjectUpdater updater = new ObjectUpdater(gameState, calculator);
			RunningGame runningGame = new RunningGame(guiDrawer,updater,gameState);
//			IInputListener listener = new InputListener(gui,gameState);
//			gui.addKeyListener(listener);
			return new GameEngine(gameState,runningGame,calculator,gui);
			
	}
}
