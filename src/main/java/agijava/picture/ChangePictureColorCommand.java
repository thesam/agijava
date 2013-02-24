package agijava.picture;




public class ChangePictureColorCommand implements PictureCommand {

	@Override
	public void run(Picture picture, int currentByte) {
		picture.setPictureColor(currentByte);
		picture.setPictureDrawingEnabled(true);
	}
	
	@Override
	public boolean needsArguments() {
		return true;
	}
}
