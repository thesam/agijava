package agijava.picture;




public class ChangePriorityColorCommand implements PictureCommand {

	@Override
	public void run(Picture picture, int currentByte) {
		picture.setPriorityColor(currentByte);
		picture.setPriorityDrawingEnabled(true);
	}
	
	@Override
	public boolean needsArguments() {
		return true;
	}
}
