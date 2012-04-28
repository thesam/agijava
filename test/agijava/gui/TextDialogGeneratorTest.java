package agijava.gui;

import static org.junit.Assert.*;

import org.junit.Test;

import agijava.gui.impl.TextDialog;
import agijava.gui.impl.TextDialogGenerator;

public class TextDialogGeneratorTest {

	private TextDialogGenerator textDialogGenerator;
	private TextDialog dialog;

	@Test
	public void createsDialogsWithAMaximumOf32CharactersPerLineByDefault()
			throws Exception {
		aTextDialogGeneratorWithDefaultLineLength();

		aTextDialogIsCreatedFromString("A A A A A A A A A A A A A A A A A A A A");

		assertEquals("A A A A A A A A A A A A A A A A \nA A A A",
				dialog.getMessage());
	}

	@Test
	public void triesToNotSplitWordsOnLongLines() throws Exception {
		aTextDialogGeneratorWithMaxLineLength(4);

		aTextDialogIsCreatedFromString("A AAA A");

		assertEquals("A \nAAA \nA", dialog.getMessage());
	}

	@Test
	public void preservesLastSpaceInInputString() throws Exception {
		aTextDialogGeneratorWithMaxLineLength(4);
		aTextDialogIsCreatedFromString("A A ");
		assertEquals("A A ", dialog.getMessage());

		aTextDialogGeneratorWithMaxLineLength(4);
		aTextDialogIsCreatedFromString("A A A ");
		assertEquals("A A \nA ", dialog.getMessage());
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void throwsExceptionIfMaxLineLengthIsZero() throws Exception {
		aTextDialogGeneratorWithMaxLineLength(0);
	}

	private void aTextDialogGeneratorWithMaxLineLength(int i) {
		textDialogGenerator = new TextDialogGenerator(i);

	}

	private void aTextDialogIsCreatedFromString(String string) {
		dialog = textDialogGenerator.createFromString(string);
	}

	@Test
	public void canCreateTextDialogFromString() throws Exception {
		aTextDialogGeneratorWithDefaultLineLength();

		aTextDialogIsCreatedFromString("Hej");

		assertEquals("Hej", dialog.getMessage());
	}

	private void aTextDialogGeneratorWithDefaultLineLength() {
		textDialogGenerator = new TextDialogGenerator();
	}

}
