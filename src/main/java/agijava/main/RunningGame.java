package agijava.main;

import java.util.Collection;

public class RunningGame {

	private GuiDrawer drawer;
	private ObjectUpdater updater;
	private GameState gameState;

	public RunningGame(GuiDrawer drawer, ObjectUpdater updater,
			GameState gameState) {
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
