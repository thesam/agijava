package agijava.logic.commands;

import agijava.main.IGameState;
import agijava.main.impl.MessageParser;

public class PrintCommand extends AbstractLogicCommand {

	@Override
	public void execute(IGameState gameState) {
		int messageNo = getMessageNo(gameState);
		String message = gameState.getCurrentLogic().getMessage(messageNo);
		message = parseMessage(message,gameState);
		gameState.showMessage(message);
//		JOptionPane.showMessageDialog(null, message);
	}

	private String parseMessage(String message, IGameState gameState) {
		MessageParser messageParser = new MessageParser(gameState);
		return messageParser.parse(message);
	}

	protected int getMessageNo(IGameState GameState) {
		return args.get(0);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}

}
