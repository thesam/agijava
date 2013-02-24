package agijava.main.impl;

import java.util.HashMap;
import java.util.Map;

public class WordsTok {

	public static final int WORD_NOT_FOUND = -1;
	
	private Map<String,Integer> words = new HashMap<String,Integer>();
	
	public int getNumberFor(String word) {
		if (words.containsKey(word)) {
			return words.get(word);
		} else {
			return WORD_NOT_FOUND;
		}
	}

	public void addWord(int wordnum, String word) {
		words.put(word,wordnum);
	}


}
