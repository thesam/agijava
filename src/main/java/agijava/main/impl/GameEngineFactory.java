package agijava.main.impl;

import java.io.IOException;

import agijava.gui.impl.GameGui;
import agijava.gui.impl.PrioBuffer;
import agijava.gui.impl.SwingGraphicsDeviceFactory;
import agijava.gui.impl.PrioCalculator;
import agijava.gui.impl.SwingGraphicsFrame;
import agijava.main.IGameState;
import agijava.main.IGuiDrawer;
import agijava.main.IMovementCalculator;

public class GameEngineFactory {

	public static GameEngine createInstance(String gameDir) throws IOException {
			
			SwingGraphicsDeviceFactory graphicsDeviceFactory = new SwingGraphicsDeviceFactory();
			IGameState gameState = GameStateFactory.createInstance(gameDir);
			SwingGraphicsFrame guiView = graphicsDeviceFactory.createGraphicsDevice();
			GameGui gui = new GameGui(new PrioCalculator(), gameState,guiView,new PrioBuffer(guiView.getHeight(),guiView.getWidth()));
			guiView.setController(gui);
			
			IGuiDrawer guiDrawer = new GuiDrawer(gameState, gui);
			IMovementCalculator calculator = new MovementCalculator();
			ObjectUpdater updater = new ObjectUpdater(gameState, calculator);
			RunningGame runningGame = new RunningGame(guiDrawer,updater,gameState);
//			IInputListener listener = new InputListener(gui,gameState);
//			gui.addKeyListener(listener);
			return new GameEngine(gameState,runningGame,calculator,gui);
			
	}
}
