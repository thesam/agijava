package agijava.main;

import org.junit.Test;

import agijava.gui.IGuiController;
import agijava.main.IGameState;
import agijava.main.IGuiDrawer;
import agijava.main.impl.GuiDrawer;
import agijava.picture.IPicture;
import static org.mockito.Mockito.*;

public class GuiDrawerTest {
	private IGuiDrawer drawer;
	private IGameState gameState;
	private IGuiController gui;

	@Test
	public void drawsCurrentPictureFromGameStateToGuiBuffer() throws Exception {
		aGuiDrawer();

		IPicture pic = mock(IPicture.class);
		when(gameState.getCurrentPicture()).thenReturn(pic);

		drawer.drawCurrentPictureToGui();

		verify(gameState).getCurrentPicture();
		verify(gui).drawPicture(pic);
	}

	@Test
	public void drawsCurrentMessageToGuiIfShowing() throws Exception {
		aGuiDrawer();
		when(gameState.messageShowing()).thenReturn(true);
		String message = "foo";
		when(gameState.getCurrentMessage()).thenReturn(message);

		drawer.drawCurrentMessage();

		verify(gameState).messageShowing();
		verify(gui).drawTextDialog(message);
	}

	@Test
	public void drawsCurrentInputLineWithGuiIfPlayerControl() throws Exception {
		aGuiDrawer();
		when(gameState.playerControl()).thenReturn(true);
		drawer.drawCurrentInputLine();

		verify(gameState).playerControl();
		verify(gui).drawCurrentInputLine();
	}

	@Test
	public void canUpdateGuiToScreen() throws Exception {
		aGuiDrawer();
		drawer.updateToScreen();
		verify(gui).updateToScreen();
	}

	private void aGuiDrawer() {
		gameState = mock(IGameState.class);
		gui = mock(IGuiController.class);
		this.drawer = new GuiDrawer(gameState, gui);
	}
}
