package agijava.picture;




public class DrawXCornerCommand implements IPictureCommand {

	private int startX = -1;
	private int startY = -1;
	private int nextX;
	private int nextY;
	private boolean writeX = true;
	
	@Override
	public void run(IPicture picture, int currentByte) {
		System.exit(1);
		if (startX == -1) {
			startX = currentByte;
			return;
		}
		if (startY == -1) {
			startY = currentByte;
			return;
		}
		if (writeX == true) {
			nextX = currentByte;
		} else {
			nextY = currentByte;
			picture.drawLine(startX, startY, nextX, nextY);
			startX = nextX;
			startY = nextY;
		}
		
	}

	@Override
	public boolean needsArguments() {
		return true;
	}
}
