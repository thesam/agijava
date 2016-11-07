package agijava;

import static org.mockito.Mockito.*;

import org.junit.Test;

import agijava.main.IGuiDrawer;
import agijava.main.impl.RunningGame;

public class RunningGameTest {
	private RunningGame runningGame;
	private IGuiDrawer drawer;

	@Test
	public void canBeCreated() throws Exception {
		aRunningGame();
	}

	@Test
	public void drawsCurrentPictureWithGuiDrawer() throws Exception {
		aRunningGame();

		runningGame.refreshGui();

		verify(drawer).drawCurrentPictureToGui();
	}

	@Test
	public void drawsCurrentMessageWithGuiDrawer() throws Exception {
		aRunningGame();
		runningGame.refreshGui();
		verify(drawer).drawCurrentMessage();
	}

	@Test
	public void drawsDisplayedTextsWithGuiDrawer() throws Exception {
		aRunningGame();
		runningGame.refreshGui();
		verify(drawer).drawDisplayedTexts();
	}

	@Test
	public void drawsCurrentInputLineWithGuiDrawer() throws Exception {
		aRunningGame();
		runningGame.refreshGui();
		verify(drawer).drawCurrentInputLine();
	}

	@Test
	public void drawsStatusLineWithGuiDrawer() throws Exception {
		aRunningGame();
		runningGame.refreshGui();
		verify(drawer).drawStatusLine();
	}

	@Test
	public void drawsBackgroundViewsWithGuiDrawer() throws Exception {
		aRunningGame();
		runningGame.refreshGui();
		verify(drawer).drawBackgroundViews();
	}

	@Test
	public void drawsAnimatedObjectsWithGuiDrawer() throws Exception {
		aRunningGame();
		runningGame.refreshGui();
		verify(drawer).drawAnimatedObjects();
	}

	private void aRunningGame() {
		drawer = mock(IGuiDrawer.class);
		runningGame = new RunningGame(drawer, null, null);
	}

}
