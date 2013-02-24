package agijava.logic.commands;

import agijava.main.GameState;
import agijava.main.MessageParser;

public class PrintCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		int messageNo = getMessageNo(gameState);
		String message = gameState.getCurrentLogic().getMessage(messageNo);
		message = parseMessage(message,gameState);
		gameState.showMessage(message);
//		JOptionPane.showMessageDialog(null, message);
	}

	private String parseMessage(String message, GameState gameState) {
		MessageParser messageParser = new MessageParser(gameState);
		return messageParser.parse(message);
	}

	protected int getMessageNo(GameState GameState) {
		return args.get(0);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}

}
