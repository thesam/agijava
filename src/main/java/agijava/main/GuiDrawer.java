package agijava.main;

import java.util.Collection;
import java.util.List;

import agijava.gui.GameGui;
import agijava.picture.Picture;
import agijava.view.Cel;
import agijava.view.Loop;
import agijava.view.View;

public class GuiDrawer {

	private GameState gameState;
	private GameGui gui;

	public GuiDrawer(GameState gameState, GameGui gui) {
		this.gameState = gameState;
		this.gui = gui;
	}

	public void drawCurrentPictureToGui() {
		Picture currentPicture = gameState.getCurrentPicture();
		if (currentPicture != null) {
			gui.drawPicture(currentPicture);
		}
	}

	public void drawBackgroundViews() {
		for (AnimatedObject obj : gameState.displayedBackgroundViews) {
			drawAnimatedObject(obj);
		}

	}

	public void drawAnimatedObjects() {
		Collection<AnimatedObject> objects = gameState.animatedObjects.values();
		for (AnimatedObject animatedObject : objects) {
			if (animatedObject.isVisible()) {
				drawAnimatedObject(animatedObject);
			}
		}
	}

	public void drawCurrentMessage() {
		if (gameState.isMessageShowing()) {
			gui.drawTextDialog(gameState.currentMessage);
		}
	}

	public void drawDisplayedTexts() {
		List<Text> texts = gameState.getDisplayedTexts();
		for (Text text : texts) {
			gui.printText(text);
		}
	}

	public void drawCurrentInputLine() {
		if (gameState.playerControl) {
			gui.drawCurrentInputLine();
		}		
	}

	public void drawStatusLine() {
		if (gameState.isStatusLineOn()) {
			gui.drawStatusBar();
		}		
	}

	private void drawAnimatedObject(AnimatedObject animatedObject) {
		int loop = animatedObject.getCurrentViewLoop();
		int cel = animatedObject.getCurrentViewCel();
		View view = animatedObject.getView();
		Loop loops = view.getLoops().get(loop);
		List<Cel> cels = loops.getCels();
		Cel celToDraw = cels.get(cel);
		gui.drawCel(celToDraw, animatedObject.getCurrentPosition().getX(), animatedObject.getCurrentPosition().getY(),
				animatedObject.getPrio());
	}

	public void updateToScreen() {
		gui.updateToScreen();
	}

	public void clearScreen() {
		gui.clearScreen();
	}

}
