package agijava.logic;

import agijava.main.ILogicCommand;

public interface ILogic {

	ILogicCommand getNextCommand();

	String getMessage(int messageNo);

	void reset();

	int getEntryNumber();

	void increaseOffset(int blockSize);

	int getCurrentOffset();

	void setOffset(int scanStart);

}
