package agijava.picture.impl;

import agijava.picture.IPicture;
import agijava.picture.IPictureCommand;



public class ChangePenCommand implements IPictureCommand {

	@Override
	public void run(IPicture picture, int currentByte) {
	}
	
	@Override
	public boolean needsArguments() {
		return true;
	}
}
