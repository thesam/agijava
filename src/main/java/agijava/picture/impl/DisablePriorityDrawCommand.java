package agijava.picture.impl;

import agijava.picture.IPicture;
import agijava.picture.IPictureCommand;



public class DisablePriorityDrawCommand implements IPictureCommand {

	@Override
	public void run(IPicture picture, int currentByte) {
		picture.setPriorityDrawingEnabled(false);
	}

	@Override
	public boolean needsArguments() {
		return false;
	}

}
