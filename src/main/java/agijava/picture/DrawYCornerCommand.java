package agijava.picture;




public class DrawYCornerCommand implements PictureCommand {

	@Override
	public void run(Picture picture, int currentByte) {
		System.exit(1);
	}
	
	@Override
	public boolean needsArguments() {
		return true;
	}
}
