package agijava.picture.impl;

import agijava.picture.IPicture;
import agijava.picture.IPictureCommand;



public class ChangePictureColorCommand implements IPictureCommand {

	@Override
	public void run(IPicture picture, int currentByte) {
		picture.setPictureColor(currentByte);
		picture.setPictureDrawingEnabled(true);
//		System.err.println("Change picture color to: " + currentByte);

	}
	@Override
	public boolean needsArguments() {
		return true;
	}
}
