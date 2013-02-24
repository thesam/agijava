package agijava.picture;



public interface PictureCommand {

	void run(Picture picture, int currentByte);

	boolean needsArguments();

}
