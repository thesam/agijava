package agijava.logic.commands;

import agijava.main.GameState;

public class CallCommand extends AbstractLogicCommand {

	@Override
	public void execute(GameState gameState) {
		callNewLogic(gameState, getRoomNumber(gameState));

	}

	protected int getRoomNumber(GameState gameState) {
		return args.get(0);
	}

	@Override
	public int getArgsSizeInBytes() {
		return 1;
	}
	
	private void callNewLogic(GameState gameState, int logicNo) {
		if (gameState.currentLogic != null) {
			gameState.logicStack.push(gameState.currentLogic);
		}
		gameState.currentLogic = gameState.logicRepository.getLogic(logicNo);
		int scanStart = getScanStart(gameState,logicNo);
		if (scanStart > 0) {
			gameState.currentLogic.setOffset(scanStart);
		}
	}
	
	private int getScanStart(GameState gameState, Integer logicNo) {
		if (gameState.scanStarts.containsKey(logicNo)) {
			return gameState.scanStarts.get(logicNo);
		} else {
			return 0;
		}
	}

}
