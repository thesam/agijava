package agijava.picture;




public class DisablePriorityDrawCommand implements PictureCommand {

	@Override
	public void run(Picture picture, int currentByte) {
		picture.setPriorityDrawingEnabled(false);
	}

	@Override
	public boolean needsArguments() {
		return false;
	}

}
