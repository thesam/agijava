package agijava.picture;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class ChangePictureColorCommandTest {
	private static final int NEW_COLOR = 100;
	private ChangePictureColorCommand cmd;
	private IPicture pic;

	@Test
	public void canChangePictureColor() throws Exception {
		aPicture();
		aChangePictureColorCommand();

		commandIsRun();

		verify(pic).setPictureColor(NEW_COLOR);
	}

	@Test
	public void enablesPictureDrawingWhenSettingColor() throws Exception {
		aPicture();
		aChangePictureColorCommand();

		commandIsRun();

		verify(pic).setPictureDrawingEnabled(true);
	}

	@Test
	public void needsArguments() throws Exception {
		aChangePictureColorCommand();
		assertTrue(cmd.needsArguments());
	}

	private void commandIsRun() {
		cmd.run(pic, NEW_COLOR);
	}

	private void aPicture() {
		pic = mock(IPicture.class);
	}

	private void aChangePictureColorCommand() {
		cmd = new ChangePictureColorCommand();
	}
}
