package agijava.picture;




public class RelativeLineCommand implements PictureCommand {

	private int startX = -1;
	private int startY = -1;
	
	@Override
	public void run(Picture picture, int currentByte) {
		if (startX == -1) {
			startX = currentByte;
			return;
		}
		if (startY == -1) {
			startY = currentByte;
			return;
		}
		int offsetX = ((currentByte & 0xf0) >> 4);
		if ((offsetX & 0x08) == (1<<3)) {
			offsetX = offsetX - (1<<3);
			offsetX *= -1;
		}
		int offsetY = (currentByte & 0x0f);
		if ((offsetY & 0x08) == (1<<3)) {
			offsetY = offsetY - (1<<3);
			offsetY *= -1;
		}
		picture.drawLine(startX, startY, startX + offsetX, startY + offsetY);
		startX = startX + offsetX;
		startY = startY + offsetY;
	}
	
	@Override
	public boolean needsArguments() {
		return true;
	}
}
