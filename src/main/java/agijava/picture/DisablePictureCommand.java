package agijava.picture;




public class DisablePictureCommand implements IPictureCommand {

	@Override
	public void run(IPicture picture, int currentByte) {
		picture.setPictureDrawingEnabled(false);
	}

	@Override
	public boolean needsArguments() {
		return false;
	}

}
