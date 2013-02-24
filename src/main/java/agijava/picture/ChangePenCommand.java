package agijava.picture;




public class ChangePenCommand implements PictureCommand {

	@Override
	public void run(Picture picture, int currentByte) {
	}
	
	@Override
	public boolean needsArguments() {
		return true;
	}
}
