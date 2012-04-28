package agijava.picture.impl;

import agijava.picture.IPicture;
import agijava.picture.IPictureCommand;



public class DrawYCornerCommand implements IPictureCommand {

	@Override
	public void run(IPicture picture, int currentByte) {
//		System.err.println("Draw Y Corner");
		System.exit(1);
		
	}
	@Override
	public boolean needsArguments() {
		return true;
	}
}
