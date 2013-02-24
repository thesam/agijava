package agijava.picture;




public class FillCommand implements PictureCommand {

	private int nextX;
	private int nextY;
	private boolean writeX = true;
	
	@Override
	public void run(Picture picture, int currentByte) {

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
