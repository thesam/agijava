package agijava.picture;




public class ChangePriorityColorCommand implements IPictureCommand {

	@Override
	public void run(IPicture picture, int currentByte) {
		picture.setPriorityColor(currentByte);
		picture.setPriorityDrawingEnabled(true);
	}
	
	@Override
	public boolean needsArguments() {
		return true;
	}
}
