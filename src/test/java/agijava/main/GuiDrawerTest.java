package agijava.main;

import org.junit.Test;

import agijava.gui.GameGui;
import agijava.main.GameState;
import agijava.picture.Picture;
import static org.mockito.Mockito.*;

public class GuiDrawerTest {
	private GuiDrawer drawer;
	private GameState gameState;
	private GameGui gui;

	@Test
	public void drawsCurrentPictureFromGameStateToGuiBuffer() throws Exception {
		aGuiDrawer();

		Picture pic = mock(Picture.class);
//		when(gameState.getCurrentPicture()).thenReturn(pic);

		drawer.drawCurrentPictureToGui();

//		verify(gameState).getCurrentPicture();
		verify(gui).drawPicture(pic);
	}

	@Test
	public void drawsCurrentMessageToGuiIfShowing() throws Exception {
		aGuiDrawer();
//		when(gameState.isMessageShowing()).thenReturn(true);
		String message = "foo";
		gameState.currentMessage = message;

		drawer.drawCurrentMessage();

//		verify(gameState).isMessageShowing();
		verify(gui).drawTextDialog(message);
	}

	@Test
	public void drawsCurrentInputLineWithGuiIfPlayerControl() throws Exception {
		aGuiDrawer();
		gameState.playerControl = true;
		drawer.drawCurrentInputLine();

		verify(gui).drawCurrentInputLine();
	}

	@Test
	public void canUpdateGuiToScreen() throws Exception {
		aGuiDrawer();
		drawer.updateToScreen();
		verify(gui).updateToScreen();
	}

	private void aGuiDrawer() {
		gameState = mock(GameState.class);
		gui = mock(GameGui.class);
		this.drawer = new GuiDrawer(gameState, gui);
	}
}
