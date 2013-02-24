package agijava.gui.impl;

public class TextDialog {

	private String message;
	private final int outerMargin;

	public TextDialog(String message, int outerMargin) {
		this.message = message;
		this.outerMargin = outerMargin;
	}

	public String getMessage() {
		return message;
	}

	public int getWidth() {
		return longestLineLengthInMessage()*8+2*outerMargin;
	}

	private int longestLineLengthInMessage() {
		String[] lines = message.split("\\n");
		int maxLength = 0;
		for (String line : lines) {
			if (line.length() > maxLength) {
				maxLength = line.length();
			}
		}
		return maxLength;
	}

	public int getHeight() {
		int numberOfLines = message.split("\\n").length;
		return numberOfLines *8+2*outerMargin;
	}
	
}
