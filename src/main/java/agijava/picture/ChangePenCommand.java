package agijava.picture;




public class ChangePenCommand implements IPictureCommand {

	@Override
	public void run(IPicture picture, int currentByte) {
	}
	
	@Override
	public boolean needsArguments() {
		return true;
	}
}
