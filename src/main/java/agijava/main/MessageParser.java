package agijava.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageParser {

	private final GameState gameState;

	public MessageParser(GameState gameState) {
		this.gameState = gameState;
	}

	public String parse(String message) {
		if (message == null) {
			return null;
		}
		Pattern pattern = Pattern.compile("%s([0-9])+");
		Matcher matcher = pattern.matcher(message);
		while(matcher.find()) {
			String stringNo = matcher.group(1);
			int parseInt = Integer.parseInt(stringNo);
			String string = gameState.strings[parseInt];
			message = matcher.replaceFirst(string);
		}
		return message;
	}

}
