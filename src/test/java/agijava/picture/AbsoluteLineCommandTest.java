package agijava.picture;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class AbsoluteLineCommandTest {
	private AbsoluteLineCommand cmd;

	@Test
	public void needsArgumentsWhenCreated() throws Exception {
		anAbsoluteLineCommand();
		assertTrue(cmd.needsArguments());
	}

	private void anAbsoluteLineCommand() {
		cmd = new AbsoluteLineCommand();
	}
	
	@Test
	public void drawsALineInPictureOnTheFourthRun() throws Exception {
		anAbsoluteLineCommand();
		Picture pic = mock(Picture.class);
		int x0 = 0;
		int y0 = 0;
		int x1 = 1;
		int y1 = 1;
		cmd.run(pic, x0);
		cmd.run(pic, y0);
		cmd.run(pic, x1);
		cmd.run(pic, y1);
		verify(pic).drawLine(x0, y0, x1, y1);
	}
}
