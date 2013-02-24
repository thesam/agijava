package agijava.gui;

public class TextDialogGenerator {

	private static final int DEFAULT_MAX_LINE_LENGTH = 32;
	private int maxLineLength;
	
	public TextDialogGenerator() {
		this.maxLineLength = DEFAULT_MAX_LINE_LENGTH;
	}
	
	public TextDialogGenerator(int maxLineLength) {
		if (maxLineLength == 0) {
			throw new IllegalArgumentException();
		}
		this.maxLineLength = maxLineLength;
	}
	
	public TextDialog createFromString(String string) {
		if (string.length() > maxLineLength) {
			String[] words = string.split(" ");
			String output = "";
			String thisLine = "";
			for (int word = 0; word < words.length; word++) {
				// add space to each word except the last
				String newWord = words[word];
				if (word != words.length -1) {
					newWord += " ";
				}
				if (nextWordFitsOnThisLine(thisLine, newWord)) {
					thisLine += newWord;
				} else {
					word--;
					output += thisLine + "\n";
					thisLine = "";
				}
			}
			if (!thisLine.isEmpty()) {
				output += thisLine;
			}
			if (string.endsWith(" ")) {
				output += " ";
			}
			string = output;
		}
		return new TextDialog(string, 0);
	}

	private boolean nextWordFitsOnThisLine(String thisLine, String newWord) {
		return thisLine.length() + newWord.length() <= maxLineLength;
	}

}
