package agijava.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordsTokFactory {

	private static final int LETTERS_OF_THE_ALPHABET = 26;

	public static WordsTok createInstance(String path) throws IOException {
		// path = "C:\\Users\\Samuel\\agitemp\\words.tok";
		WordsTok wordsTok = new WordsTok();
		FileInputStream inputStream = new FileInputStream(path);
		List<Integer> offsets = new ArrayList<Integer>();
		for (int i = 0; i < LETTERS_OF_THE_ALPHABET; i++) {
			int offsetHi = inputStream.read() & 0xff;
			int offsetLow = inputStream.read() & 0xff;
			int offset = offsetHi << 8 | offsetLow;
			offsets.add(offset);
		}
		int nextByte = 0;
		String lastWord = "";
		while (nextByte != -1) {
			String currentWord = "";
			nextByte = inputStream.read();
			int charsFromLastWord = nextByte;
			boolean lastChar = false;
			while (!lastChar) {
				nextByte = inputStream.read();
				if ((nextByte & 0x80) > 0) {
					lastChar = true;
				}
				int key = 0x7f;
				if (lastChar) {
					key = 0xff;
				}
				int decrypted = nextByte ^ key;
				if (decrypted > 0) {
					char foo = (char) ((char) decrypted);
					currentWord += foo;
				}
				if (lastChar) {
					int wordnumHi = inputStream.read();
					int wordnumLo = inputStream.read();
					int wordnum = wordnumHi << 8 | wordnumLo;
					String prefix = "";
					if (charsFromLastWord > 0) {
						prefix = lastWord.substring(0,charsFromLastWord);
					}
					wordsTok.addWord(wordnum, prefix+currentWord);
					lastWord = prefix+currentWord;
				}
			}
		}
		inputStream.close();
		return wordsTok;
	}

}
