package agijava.picture.impl;

import agijava.picture.IPicture;
import agijava.picture.IPictureCommand;



public class ChangePriorityColorCommand implements IPictureCommand {

	@Override
	public void run(IPicture picture, int currentByte) {
		picture.setPriorityColor(currentByte);
		picture.setPriorityDrawingEnabled(true);
//		System.err.println("Change Priority Color");

	}
	@Override
	public boolean needsArguments() {
		return true;
	}
}
