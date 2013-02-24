package agijava.logic.commands;

import agijava.main.GameState;
import agijava.main.MessageParser;

public class DisplayCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int row = args.get(0);
		int col = args.get(1);
		int messageNo = args.get(2);
		
		String message = gameState.getCurrentLogic().getMessage(messageNo);
		message = parseMessage(message,gameState);
		gameState.addText(row,col,message);

	}

	@Override
	public int getArgsSizeInBytes() {
		return 3;
	}

	private String parseMessage(String message, GameState gameState) {
		MessageParser messageParser = new MessageParser(gameState);
		return messageParser.parse(message);
	}
	
}
