package agijava.main.impl;

import java.util.Collection;

import agijava.main.IGameState;
import agijava.main.IGuiDrawer;
import agijava.main.IRunningGame;

public class RunningGame implements IRunningGame {

	private IGuiDrawer drawer;
	private ObjectUpdater updater;
	private IGameState gameState;

	public RunningGame(IGuiDrawer drawer, ObjectUpdater updater,
			IGameState gameState) {
		this.drawer = drawer;
		this.updater = updater;
		this.gameState = gameState;
	}

	public void refreshGui() {
		drawer.clearScreen();
		drawer.drawCurrentPictureToGui();
		drawer.drawBackgroundViews();
		drawer.drawAnimatedObjects();
		drawer.drawStatusLine();
		drawer.drawCurrentInputLine();
		drawer.drawDisplayedTexts();
		drawer.drawCurrentMessage();
		drawer.updateToScreen();
	}

	public void updateAnimatedObjects() {
		Collection<AnimatedObject> animatedObjects = gameState
				.getAnimatedObjects();
		for (AnimatedObject animatedObject : animatedObjects) {
			if (animatedObject.shouldBeUpdated()) {
				updater.updateLoopAndCel(animatedObject);
				updater.updateSingleLoop(animatedObject);
				if (animatedObject.isMoving()) {
					updater.updatePosition(animatedObject);
					updater.stopIfDestinationIsReached(animatedObject);
				}
			}
		}
	}
}
