package agijava.picture.impl;

import agijava.picture.IPicture;
import agijava.picture.IPictureCommand;



public class FillCommand implements IPictureCommand {

	private int nextX;
	private int nextY;
	private boolean writeX = true;
	
	@Override
	public void run(IPicture picture, int currentByte) {

		if (writeX == true) {
			nextX = currentByte;
			writeX = false;
		} else {
			nextY = currentByte;
			picture.fillFrom(nextX,nextY);
			writeX = true;
		}
	}
	@Override
	public boolean needsArguments() {
		return true;
	}
}
