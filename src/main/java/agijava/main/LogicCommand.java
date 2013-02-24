package agijava.main;

import java.util.List;

public interface LogicCommand {

	public void execute(GameState gameState);

	public int getArgsSizeInBytes();

	public void setArgs(List<Integer> args);

	public boolean hasAllNeededArgs();

	public void reset();

}
