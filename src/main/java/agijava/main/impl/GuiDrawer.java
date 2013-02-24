package agijava.main.impl;

import java.util.Collection;
import java.util.List;

import agijava.gui.impl.GameGui;
import agijava.main.IGameState;
import agijava.main.IGuiDrawer;
import agijava.picture.IPicture;
import agijava.view.ICel;
import agijava.view.ILoop;
import agijava.view.IView;

public class GuiDrawer implements IGuiDrawer {

	private IGameState gameState;
	private GameGui gui;

	public GuiDrawer(IGameState gameState, GameGui gui) {
		this.gameState = gameState;
		this.gui = gui;
	}

	@Override
	public void drawCurrentPictureToGui() {
		IPicture currentPicture = gameState.getCurrentPicture();
		if (currentPicture != null) {
			gui.drawPicture(currentPicture);
		}
	}

	@Override
	public void drawBackgroundViews() {
		for (AnimatedObject obj : gameState.getBackgroundViews()) {
			drawAnimatedObject(obj);
		}

	}

	@Override
	public void drawAnimatedObjects() {
		Collection<AnimatedObject> objects = gameState.getAnimatedObjects();
		for (AnimatedObject animatedObject : objects) {
			if (animatedObject.isVisible()) {
				drawAnimatedObject(animatedObject);
			}
		}
	}

	@Override
	public void drawCurrentMessage() {
		if (gameState.isMessageShowing()) {
			gui.drawTextDialog(gameState.getCurrentMessage());
		}
	}

	@Override
	public void drawDisplayedTexts() {
		List<Text> texts = gameState.getDisplayedTexts();
		for (Text text : texts) {
			gui.printText(text);
		}
	}

	@Override
	public void drawCurrentInputLine() {
		if (gameState.playerControl()) {
			gui.drawCurrentInputLine();
		}		
	}

	@Override
	public void drawStatusLine() {
		if (gameState.isStatusLineOn()) {
			gui.drawStatusBar();
		}		
	}

	private void drawAnimatedObject(AnimatedObject animatedObject) {
		int loop = animatedObject.getCurrentViewLoop();
		int cel = animatedObject.getCurrentViewCel();
		IView view = animatedObject.getView();
		ILoop loops = view.getLoops().get(loop);
		List<ICel> cels = loops.getCels();
		ICel celToDraw = cels.get(cel);
		gui.drawCel(celToDraw, animatedObject.getCurrentPosition().getX(), animatedObject.getCurrentPosition().getY(),
				animatedObject.getPrio());
	}

	@Override
	public void updateToScreen() {
		gui.updateToScreen();
	}

	@Override
	public void clearScreen() {
		gui.clearScreen();
	}

}
