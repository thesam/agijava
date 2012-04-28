package agijava.main;

import java.util.List;

public interface ILogicCommand {

	public void execute(IGameState gameState);

	public int getArgsSizeInBytes();

	public void setArgs(List<Integer> args);

	public boolean hasAllNeededArgs();

	public void reset();

}
