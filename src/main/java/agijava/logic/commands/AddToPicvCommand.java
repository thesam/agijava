package agijava.logic.commands;

import agijava.main.GameState;

public class AddToPicvCommand extends AddToPicCommand {
	protected Integer getMargin(GameState gameState) {
		return gameState.getVar(args.get(6));
	}

	protected Integer getPrio(GameState gameState) {
		return gameState.getVar(args.get(5));
	}

	protected Integer getY(GameState gameState) {
		return gameState.getVar(args.get(4));
	}

	protected Integer getX(GameState gameState) {
		return gameState.getVar(args.get(3));
	}

	protected Integer getCelNo(GameState gameState) {
		return gameState.getVar(args.get(2));
	}

	protected Integer getLoopNo(GameState gameState) {
		return gameState.getVar(args.get(1));
	}

	protected Integer getViewNo(GameState gameState) {
		return gameState.getVar(args.get(0));
	}
}
