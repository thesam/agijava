package agijava.logic.commands;

import java.util.List;

import agijava.main.IGameState;
import agijava.main.ILogicCommand;

public abstract class AbstractLogicCommand implements ILogicCommand {

	protected List<Integer> args;

	public abstract void execute(IGameState gameState);

	@Override
	public abstract int getArgsSizeInBytes();

	@Override
	public void setArgs(List<Integer> args) {
		this.args = args;
	}

	@Override
	public boolean hasAllNeededArgs() {
		int argsSizeInBytes = getArgsSizeInBytes();
		if (argsSizeInBytes > 0) {
			if (args != null) {
				return args.size() == argsSizeInBytes;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
	
	@Override
	public void reset() {
		if (args != null) {
			args.clear();
		}
	}

}
