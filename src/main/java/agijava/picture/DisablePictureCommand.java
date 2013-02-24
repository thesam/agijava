package agijava.picture;




public class DisablePictureCommand implements PictureCommand {

	@Override
	public void run(Picture picture, int currentByte) {
		picture.setPictureDrawingEnabled(false);
	}

	@Override
	public boolean needsArguments() {
		return false;
	}

}
