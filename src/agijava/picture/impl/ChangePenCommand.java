package agijava.picture.impl;

import agijava.picture.IPicture;
import agijava.picture.IPictureCommand;



public class ChangePenCommand implements IPictureCommand {

	@Override
	public void run(IPicture picture, int currentByte) {
//		System.err.println("Change Pen");

	}
	@Override
	public boolean needsArguments() {
		return true;
	}
}
