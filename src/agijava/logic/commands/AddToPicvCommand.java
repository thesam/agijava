package agijava.logic.commands;

import agijava.main.IGameState;

public class AddToPicvCommand extends AddToPicCommand {
	protected Integer getMargin(IGameState gameState) {
		return gameState.getVar(args.get(6));
	}

	protected Integer getPrio(IGameState gameState) {
		return gameState.getVar(args.get(5));
	}

	protected Integer getY(IGameState gameState) {
		return gameState.getVar(args.get(4));
	}

	protected Integer getX(IGameState gameState) {
		return gameState.getVar(args.get(3));
	}

	protected Integer getCelNo(IGameState gameState) {
		return gameState.getVar(args.get(2));
	}

	protected Integer getLoopNo(IGameState gameState) {
		return gameState.getVar(args.get(1));
	}

	protected Integer getViewNo(IGameState gameState) {
		return gameState.getVar(args.get(0));
	}
}
