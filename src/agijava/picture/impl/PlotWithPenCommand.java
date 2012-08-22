package agijava.picture.impl;

import agijava.picture.IPicture;
import agijava.picture.IPictureCommand;


//TODO: Create full implementation of brush command, this assumes that the size is one pixel

public class PlotWithPenCommand implements IPictureCommand {

	private boolean lastWasX = false;
	private int x;
	private int y;
	
	@Override
	public void run(IPicture picture, int currentByte) {
		if (!lastWasX) {
			x = currentByte;
			lastWasX = true;
		} else {
			y = currentByte;
			picture.drawPixel(x, y);
			lastWasX = false;
		}

	}
	@Override
	public boolean needsArguments() {
		return true;
	}
}
