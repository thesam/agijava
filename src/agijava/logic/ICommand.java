package agijava.logic;

import java.util.List;

public interface ICommand {

	String getName();

	int getArgsSizeInBytes();

	String parse(List<Integer> args);

}
