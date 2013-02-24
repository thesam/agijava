package agijava.picture;




public class ChangePictureColorCommand implements IPictureCommand {

	@Override
	public void run(IPicture picture, int currentByte) {
		picture.setPictureColor(currentByte);
		picture.setPictureDrawingEnabled(true);
	}
	
	@Override
	public boolean needsArguments() {
		return true;
	}
}
