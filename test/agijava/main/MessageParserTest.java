package agijava.main;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import agijava.main.IGameState;
import agijava.main.impl.MessageParser;

public class MessageParserTest {
	private IGameState gameState;
	private MessageParser messageParser;

	@Test
	public void canReplaceAStringReferenceWithStringContentFromGameState()
			throws Exception {
		aMessageParser();
		
		when(gameState.getString(0)).thenReturn("hello");
		
		assertEquals("hello",messageParser.parse("%s0"));

	}
	
	@Test
	public void doesNotTouchStringWithoutStringReference() throws Exception {
		aMessageParser();

		assertEquals("foo",messageParser.parse("foo"));
	}
	
	@Test
	public void canReplaceStringReferenceButKeepRestOfString() throws Exception {
		aMessageParser();
		when(gameState.getString(0)).thenReturn("hello");
		assertEquals("foohellofoo",messageParser.parse("foo%s0foo"));
		
	}

	private void aMessageParser() {
		gameState = mock(IGameState.class);
		messageParser = new MessageParser(gameState);
	}

}
