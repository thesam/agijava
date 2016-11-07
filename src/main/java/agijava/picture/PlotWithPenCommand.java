package agijava.picture;



//TODO: Create full implementation of brush command, this assumes that the size is one pixel

public class PlotWithPenCommand implements PictureCommand {

	private boolean lastWasX = false;
	private int x;
	private int y;
	
	@Override
	public void run(Picture picture, int currentByte) {
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
