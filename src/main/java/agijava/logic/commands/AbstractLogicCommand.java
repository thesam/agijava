package agijava.logic.commands;

import java.util.ArrayList;
import java.util.List;

import agijava.main.GameState;
import agijava.main.LogicCommand;

public abstract class AbstractLogicCommand implements LogicCommand {

	protected List<Integer> args = new ArrayList<Integer>();

	@Override
	public abstract void execute(GameState gameState);

	@Override
	public abstract int getArgsSizeInBytes();

	@Override
	public void setArgs(List<Integer> args) {
		this.args = args;
	}

	@Override
	public boolean hasAllNeededArgs() {
		int argsSizeInBytes = getArgsSizeInBytes();
		return args.size() == argsSizeInBytes;
	}

	@Override
	public void reset() {
		args.clear();
	}

	public List<Integer> getArgs() {
		return args;
	}

}
