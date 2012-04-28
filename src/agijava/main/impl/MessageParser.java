package agijava.main.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import agijava.main.IGameState;

public class MessageParser {

	private final IGameState gameState;

	public MessageParser(IGameState gameState) {
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
			String string = gameState.getString(parseInt);
			message = matcher.replaceFirst(string);
		}
		return message;
	}

}
