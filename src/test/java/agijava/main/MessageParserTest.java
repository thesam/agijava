package agijava.main;

import static org.junit.Assert.*;

import org.junit.Test;

import agijava.main.GameState;

public class MessageParserTest {
	private GameState gameState;
	private MessageParser messageParser;

	@Test
	public void canReplaceStringReferenceButKeepRestOfString() throws Exception {
		aMessageParser();
		gameState.strings[0] = "hello";
		assertEquals("foohellofoo",messageParser.parse("foo%s0foo"));
	}

	@Test
	public void doesNotTouchStringWithoutStringReference() throws Exception {
		aMessageParser();
		assertEquals("foo",messageParser.parse("foo"));
	}
	
	private void aMessageParser() {
		gameState = new GameState(null, null, null, null, null);
		messageParser = new MessageParser(gameState);
	}

}
