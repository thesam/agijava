package agijava.main;

import static org.junit.Assert.*;

import org.junit.Test;

import agijava.main.impl.WordsTok;

public class WordsTokTest {
	private WordsTok wordsTok;

	@Test
	public void canStoreWords() throws Exception {
		aWordsTok();
		
		wordsTok.addWord(100, "hello");
		
		int numberFor = wordsTok.getNumberFor("hello");
		assertEquals(100,numberFor);
	}
	
	private void aWordsTok() throws Exception {
		wordsTok = new WordsTok();
	}
	
	
}
