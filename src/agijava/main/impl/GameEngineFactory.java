package agijava.main.impl;

import java.io.IOException;

import agijava.gui.IGameGui;
import agijava.gui.IGraphicsDevice;
import agijava.gui.impl.GameGui;
import agijava.gui.impl.GraphicsDeviceFactory;
import agijava.gui.impl.PrioBuffer;
import agijava.gui.impl.PrioCalculator;
import agijava.main.IGameState;
import agijava.main.IGuiDrawer;
import agijava.main.IInputListener;
import agijava.main.IMovementCalculator;
import agijava.main.IObjectUpdater;

public class GameEngineFactory {

	public static GameEngine createInstance(String gameDir) throws IOException {
			IGraphicsDevice graphics = GraphicsDeviceFactory.createSwingGraphicsDevice();
			IGameGui gui = new GameGui(graphics,new PrioCalculator(), new PrioBuffer(graphics.getHeight(), graphics.getWidth()));
			
			IGameState gameState = GameStateFactory.createInstance(gameDir);
			
			IGuiDrawer guiDrawer = new GuiDrawer(gameState, gui);
			IMovementCalculator calculator = new MovementCalculator();
			IObjectUpdater updater = new ObjectUpdater(gameState, calculator);
			RunningGame runningGame = new RunningGame(guiDrawer,updater,gameState);
			IInputListener listener = new InputListener(gui,gameState);
			gui.addKeyListener(listener);
			return new GameEngine(gameState,runningGame,listener,calculator);
			
	}
}
