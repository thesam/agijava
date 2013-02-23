package agijava.picture.impl;

import agijava.picture.IPicture;
import agijava.picture.IPictureCommand;



public class AbsoluteLineCommand implements IPictureCommand {

	private int currentX = -1;
	private int currentY = -1;
	private int nextX;
	private int nextY;
	private boolean writeX = true;
	
	@Override
	public void run(IPicture picture, int currentByte) {
		if (currentX == -1) {
			currentX = currentByte;
			return;
		}
		if (currentY == -1) {
			currentY = currentByte;
			return;
		}
		if (writeX) {
			nextX = currentByte;
		} else {
			nextY = currentByte;
			picture.drawLine(currentX,currentY,nextX,nextY);
			currentX = nextX;
			currentY = nextY;
		}
		writeX = !writeX;
	}

	@Override
	public boolean needsArguments() {
		return true;
	}

}
