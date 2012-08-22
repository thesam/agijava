package agijava.gui;

import static org.junit.Assert.*;

import org.junit.Test;

import agijava.gui.impl.TextDialog;

public class TextDialogTest {
	@Test
	public void growsInWidthWithText() throws Exception {
		TextDialog textDialog = new TextDialog("A", 0);
		assertEquals(8,textDialog.getWidth());

		TextDialog textDialog2 = new TextDialog("AA", 0);
		assertEquals(16,textDialog2.getWidth());
	}
	
	@Test
	public void growsInWidthWithMargin() throws Exception {
		TextDialog textDialog = new TextDialog("A", 1);
		assertEquals(8+2*1,textDialog.getWidth());
	}
	
	@Test
	public void usesLongestLineForBoxWidth() throws Exception {
		TextDialog textDialog2 = new TextDialog("A\nAA", 0);
		assertEquals(16,textDialog2.getWidth());
	}
	
	@Test
	public void growsInHeightWithMargin() throws Exception {
		TextDialog textDialog = new TextDialog("A",0);
		assertEquals(8,textDialog.getHeight());
		
		TextDialog textDialog2 = new TextDialog("A",1);
		assertEquals(10,textDialog2.getHeight());
	}
	
	@Test
	public void growsInHeightForEachNewlineInMessage() throws Exception {
		TextDialog textDialog = new TextDialog("A\nA",0);
		assertEquals(16,textDialog.getHeight());
	}
}
